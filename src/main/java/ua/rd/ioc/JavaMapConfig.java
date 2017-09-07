package ua.rd.ioc;

import java.util.List;
import java.util.stream.Stream;

public class JavaMapConfig implements Config {

    private List<String> beanDescriptions;

    public JavaMapConfig(List<String> beanDescriptions) {
        this.beanDescriptions = beanDescriptions;
    }

    @Override
    public BeanDefinition[] beanDefinitions() {
        beanDescriptions.stream().map(name -> new BeanDefinition() {
            @Override
            public String getBeanName() {
                return name;
            }
        })
                .toArray(BeanDefinition::new);

        return new BeanDefinition[0];
    }
}
