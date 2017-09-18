package ua.rd.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.stream.Stream;

public class ApplicationContext implements Context {
    private List<BeanDefinition> beanDefinitions;
    private Map<String, Object> beans;

    public ApplicationContext(Config config) {
        beanDefinitions = Arrays.asList(config.beanDefinitions());
        beans = new HashMap<>();
        initContext(beanDefinitions);
    }

    private void initContext(List<BeanDefinition> beanDefinitions) {
        beanDefinitions.stream().forEach(bd -> getBean(bd.getBeanName()));
    }

    public ApplicationContext() {
        beanDefinitions = Arrays.asList(Config.EMPTY_BEAN_DEfINITION);
        beans = new HashMap<>();
    }

    @Override
    public Object getBean(String beanName) {
        return Optional.ofNullable(beans.get(beanName))
                .orElseGet(() -> createBeanByBeanName(beanName));
    }

    private Object createBeanByBeanName(String beanName) {
        BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);
        Object bean = createNewBean(beanDefinition);
        if(!beanDefinition.isPrototype()) {
            beans.put(beanName, bean);
        }
        return bean;
    }


    private Object createNewBean(BeanDefinition beanDefinition) {
        BeanBuilder  beanBuilder = new BeanBuilder(beanDefinition);
        beanBuilder.createNewBeanInstance();
        beanBuilder.callPostConstructAnnotatedMethod();
        beanBuilder.callInitMethod();
        beanBuilder.createBenchmarkProxy();

        Object bean = beanBuilder.build();

        return bean;
    }

    class BeanBuilder {

        private BeanDefinition beanDefinition;
        private Object bean;

        private BeanBuilder(BeanDefinition beanDefinition) {
            this.beanDefinition = beanDefinition;
        }

        private void createNewBeanInstance() {
            Class<?> type = beanDefinition.getBeanType();
            if(type.getDeclaredConstructors()[0].getParameterCount() == 0) {
                bean =  createBeanWithDefaultConstructor(type);
            } else {
                bean = createBeanWithParams(type);
            }
        }

        private void callPostConstructAnnotatedMethod() {
            Class<?> beanType = bean.getClass();
            Stream.of(beanType.getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(MyPostConstruct.class))
                    .forEach(m -> {
                        try {
                            m.invoke(bean);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        private void callInitMethod() {
            Class<?> beanType = bean.getClass();
            try {
                Method intiMethod = beanType.getMethod("init");
                intiMethod.invoke(bean);
            } catch (NoSuchMethodException e) {}
            catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


        private void createBenchmarkProxy() {
            Object originalBean = bean;
            if(Stream.of(bean.getClass().getDeclaredMethods())
                    .anyMatch(m -> m.isAnnotationPresent(Benchmark.class) &&
                            m.getAnnotation(Benchmark.class).enable())) {
                Class<?> beanType = bean.getClass();
                bean = Proxy.newProxyInstance(beanType.getClassLoader(),
                        beanType.getInterfaces(),
                        (proxy, method, args) -> {
                            Method beanMethod = beanType.getDeclaredMethod(method.getName(),
                                    method.getParameterTypes());
                            if (beanMethod.isAnnotationPresent(Benchmark.class) &&
                                    beanMethod.getAnnotation(Benchmark.class).enable()) {
                                long start = System.nanoTime();
                                Object objectToReturn = method.invoke(originalBean, args);
                                long finish = System.nanoTime();
                                System.out.println(finish - start);
                                return objectToReturn;
                            } else {
                                return method.invoke(originalBean, args);
                            }
                        });
            }
        }

        private Object build() {
            return bean;
        }
    }

    private BeanDefinition getBeanDefinitionByName(String beanName) {
        return beanDefinitions.stream().filter((b) -> beanName.equals(b.getBeanName()))
                .findAny().orElseThrow(NoSuchBeanDefinitionException::new);
    }

    private Object createBeanWithDefaultConstructor(Class<?> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private Object createBeanWithParams(Class<?> type) {
        Constructor<?> constructor = type.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] params = Stream.of(parameterTypes)
                .map(Class::getSimpleName)
                .map(s -> Character.toLowerCase(s.charAt(0)) + s.substring(1))
                .map(this::getBean).toArray();
        try {
            return constructor.newInstance(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitions.stream().map(BeanDefinition::getBeanName).toArray(String[]::new);
    }
}
