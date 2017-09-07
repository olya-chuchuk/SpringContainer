package ua.rd.ioc;

public class NoSuchBeanDefinitionException extends RuntimeException {
    public NoSuchBeanDefinitionException() {
        super("No such bean");
    }
}
