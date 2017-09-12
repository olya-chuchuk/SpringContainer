package ua.rd.ioc;

public interface Context {
    public Object getBean(String beanName);
    String[] getBeanDefinitionNames();
}
