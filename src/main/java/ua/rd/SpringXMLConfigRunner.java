package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.domain.Tweet;

import java.util.Arrays;

/**
 * Created by Olha_Chuchuk on 9/15/2017.
 */
public class SpringXMLConfigRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(
                new String[] {"serviceContext.xml"}, repoContext);


        Arrays.stream(serviceContext.getBeanDefinitionNames()).forEach(System.out::println);

        System.out.println(serviceContext.getBean(Tweet.class));

        //System.out.println(serviceContext.getBean("abc") == serviceContext.getBean("abc"));

        serviceContext.close();
        repoContext.close();
    }
}
