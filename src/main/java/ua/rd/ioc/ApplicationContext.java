package ua.rd.ioc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ApplicationContext implements Context {
    private BeanDefinition[] beanDefinitions;

    public ApplicationContext(Config config) {
        beanDefinitions = config.beanDefinitions();
    }

    public ApplicationContext() {
        beanDefinitions = Config.EMPTY_BEAN_DEfINITION;
    }

    @Override
    public Object getBean(String beanName) throws IllegalAccessException, InstantiationException {
        List<BeanDefinition> beanDefinitions =
                Arrays.asList(this.beanDefinitions);
        Optional<BeanDefinition> requestedBean =
                beanDefinitions.stream().filter((b) -> beanName.equals(b.getBeanName())).findFirst();
        if (requestedBean.isPresent()) {

            Class<?> beanType = requestedBean.get().getBeanType();
            System.out.println(beanType);
            return beanType.newInstance();
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
