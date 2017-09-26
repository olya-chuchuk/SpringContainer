package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Olha_Chuchuk on 9/15/2017.
 */
public class SpringXMLConfigRunner {
    public static void main(String[] args) throws NoSuchMethodException {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(
                new String[] {"serviceContext.xml"}, repoContext);
        TweetService tweetService = serviceContext.getBean(TweetService.class);
        TweetService service = serviceContext.getBean(TweetService.class);


        String userName = "Test user name";
        String txt = "Test text";
        service.createNewUser(userName);
        User user = service.getUserByName(userName).get();
        service.tweet(user, txt);

        List<Tweet> tweets = service.userProfile(userName);

        System.out.println(tweets);

        serviceContext.close();
        repoContext.close();
    }
}
