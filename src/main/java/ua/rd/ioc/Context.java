package ua.rd.ioc;

public interface Context {
    public Object getBean(String beanName) throws IllegalAccessException, InstantiationException;
    String[] getBeanDefinitionNames();
}
