package ua.rd.ioc;

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
    public Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
        BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);
        Object bean = beans.get(beanName);
        if(bean == null) {
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
        try {
            return bd.getBeanType().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitions.stream().map(BeanDefinition::getBeanName).toArray(String[]::new);
    }
}
