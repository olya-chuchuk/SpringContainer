package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

/**
 * Created by Olha_Chuchuk on 9/15/2017.
 */
public class SpringXMLConfigRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(
                new String[] {"serviceContext.xml"}, repoContext);

        TweetService tweetService = serviceContext.getBean(SimpleTweetService.class);
        System.out.println(tweetService.newTweet() == tweetService.newTweet());

        serviceContext.close();
        repoContext.close();
    }
}
