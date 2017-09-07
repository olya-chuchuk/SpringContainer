package ua.rd.ioc;

public interface Config {

    BeanDefinition[] EMPTY_BEAN_DEfINITION = new BeanDefinition[0];

    BeanDefinition[] beanDefinitions();

}
