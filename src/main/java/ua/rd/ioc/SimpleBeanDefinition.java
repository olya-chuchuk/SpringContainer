package ua.rd.ioc;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public class SimpleBeanDefinition implements BeanDefinition {


    private final String beanName;
    private final Class<?> beanType;
    private final boolean isPrototype;

    public SimpleBeanDefinition(String beanName, Class<?> beanType, boolean isPrototype) {
        this.beanName = beanName;
        this.beanType = beanType;
        this.isPrototype = isPrototype;
    }

    @Override
    public String getBeanName() {
    return beanName;
}

    @Override
    public Class<?> getBeanType() {
        return beanType;
    }

    @Override
    public boolean isPrototype() {
        return isPrototype;
    }
}
