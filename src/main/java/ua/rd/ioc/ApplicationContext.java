package ua.rd.ioc;

import java.util.Arrays;
import java.util.List;

public class ApplicationContext implements Context {
    private BeanDefinition[] beanDefinitions;

    public ApplicationContext(Config config) {
        beanDefinitions = config.beanDefinitions();
    }

    public ApplicationContext() {
        beanDefinitions = Config.EMPTY_BEAN_DEfINITION;
    }

    @Override
    public Object getBean(String beanName) {
        List<BeanDefinition> beanDefinitions =
                Arrays.asList(this.beanDefinitions);
        if (beanDefinitions.stream().map(BeanDefinition::getBeanName).anyMatch(n -> n.equals(beanName))) {

            //TODO найти бин с именем и для него через рефлекшн создать бин с таким имененем
            return new Object();
            //BeanDefinition beanDefinition;
            //return beanDefinition.getBeanType().newInstance();
        } else {
            throw new NoSuchBeanDefinitionException();
        }
    }

    public String[] getBeanDefinitionNames() {
        String[] beanDefinitionNames = new String[beanDefinitions.length];
        for(int i = 0; i < beanDefinitions.length; i++) {
            beanDefinitionNames[i] = beanDefinitions[i].getBeanName();
        }
        return beanDefinitionNames;
    }
}
