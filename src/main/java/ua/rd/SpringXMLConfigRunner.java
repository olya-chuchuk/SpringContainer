package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Olha_Chuchuk on 9/15/2017.
 */
public class SpringXMLConfigRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(
                new String[] {"serviceContext.xml"}, repoContext);

        System.out.println(repoContext.getBean("tweet"));
        System.out.println(serviceContext.getBean("tweet"));
        System.out.println(serviceContext.getBean("tweetParentgti"));

        serviceContext.close();
        repoContext.close();
    }
}
