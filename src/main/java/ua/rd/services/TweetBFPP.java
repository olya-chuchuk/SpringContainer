package ua.rd.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import ua.rd.domain.User;

/**
 * Created by Olha_Chuchuk on 9/20/2017.
 */
public class TweetBFPP implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("tweet");
        bd.setScope("singleton");
        MutablePropertyValues values = bd.getPropertyValues();
        //System.out.println(values);
        PropertyValue user = values.getPropertyValue("user");
        user.setConvertedValue(new User(user.getValue().toString()));
    }
}
