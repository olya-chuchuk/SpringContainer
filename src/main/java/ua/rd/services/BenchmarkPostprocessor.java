package ua.rd.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;
import ua.rd.ioc.Benchmark;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by Olha_Chuchuk on 9/20/2017.
 */
public class BenchmarkPostprocessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> thisClass = o.getClass();
        Class<?> superClass = thisClass.getSuperclass();
        if(Arrays.stream(thisClass.getDeclaredMethods())
                .anyMatch(m -> m.isAnnotationPresent(Benchmark.class) &&
                        m.getAnnotation(Benchmark.class).enable()) ||
                Arrays.stream(superClass.getDeclaredMethods())
                        .anyMatch(m -> m.isAnnotationPresent(Benchmark.class) &&
                                m.getAnnotation(Benchmark.class).enable())) {
            System.out.println("creating proxy");

            return createBenchmarkProxy(o);
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }

    private Object createBenchmarkProxy(Object bean) {
            Class<?> beanType = bean.getClass();
            return Proxy.newProxyInstance(beanType.getClassLoader(),
                    ClassUtils.getAllInterfaces(bean),
                    (proxy, method, args) -> {
                        Method beanMethod = beanType.getDeclaredMethod(method.getName(),
                                method.getParameterTypes());
                        if (beanMethod.isAnnotationPresent(Benchmark.class) &&
                                beanMethod.getAnnotation(Benchmark.class).enable()) {
                            long start = System.nanoTime();
                            Object objectToReturn = method.invoke(bean, args);
                            long finish = System.nanoTime();
                            System.out.println(finish - start);
                            return objectToReturn;
                        } else {
                            return method.invoke(bean, args);
                        }
                    });
        }
}
