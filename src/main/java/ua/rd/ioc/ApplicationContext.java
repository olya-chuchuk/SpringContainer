package ua.rd.ioc;

public class ApplicationContext implements Context {
    private BeanDefinition[] beanDefinitions;

    public ApplicationContext(Config config) {
        beanDefinitions = config.beanDefinitions();
    }

    public ApplicationContext() {
        beanDefinitions = Config.EMPTY_BEAN_DEfINITION;
    }

    public Object getBean(String beanName) {
        throw new NoSuchBeanDefinitionException();
    }

    public String[] getBeanDefinitionNames() {
        String[] beanDefinitionNames = new String[beanDefinitions.length];
        for(int i = 0; i < beanDefinitions.length; i++) {
            beanDefinitionNames[i] = beanDefinitions[i].getBeanName();
        }
        return beanDefinitionNames;
    }
}
