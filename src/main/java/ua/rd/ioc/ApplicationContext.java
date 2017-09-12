package ua.rd.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext implements Context {
    private List<BeanDefinition> beanDefinitions;
    private Map<String, Object> beans;

    public ApplicationContext(Config config) {
        beanDefinitions = Arrays.asList(config.beanDefinitions());
        beans = new HashMap<>();
    }

    public ApplicationContext() {
        beanDefinitions = Arrays.asList(Config.EMPTY_BEAN_DEfINITION);
        beans = new HashMap<>();
    }

    @Override
    public Object getBean(String beanName) {
        Object bean = beans.get(beanName);
        if(bean == null) {
            BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);
            bean = createNewBean(beanDefinition);
            if(!beanDefinition.isPrototype()) {
                beans.put(beanName, bean);
            }
        }
        return bean;
    }



    private Object createNewBean(BeanDefinition beanDefinition) {
        return createObject(beanDefinition);
    }

    private BeanDefinition getBeanDefinitionByName(String beanName) {
        return beanDefinitions.stream().filter((b) -> beanName.equals(b.getBeanName()))
                .findAny().orElseThrow(NoSuchBeanDefinitionException::new);
    }

    private Object createObject(BeanDefinition bd) {
        Class<?> type = bd.getBeanType();
        Object newBean;
        if(type.getDeclaredConstructors()[0].getParameterCount() == 0) {
            newBean =  createBeanWithDefaultConstructor(type);
        } else {
            newBean = createBeanWithParams(type);
        }
        return newBean;
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
        Object[] params = new Object[parameterTypes.length];
        for(int i = 0; i < parameterTypes.length; ++i) {
            Class<?> paramType = parameterTypes[i];
            String paramTypeName = paramType.getSimpleName();
            Object param = getBean(Character.toLowerCase(paramTypeName.charAt(0))
                    + paramTypeName.substring(1));
            params[i] = param;
        }
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
