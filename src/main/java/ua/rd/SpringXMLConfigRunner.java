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
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"serviceContext.xml"}, repoContext);

        TweetService service = context.getBean(TweetService.class);

        User user1 = service.createNewUser("Nick");
        User user2 = service.createNewUser("Jack");

        Tweet tweet1 = service.tweet(user1, "Nick's first tweet");
        Tweet tweet2 = service.tweet(user1, "Some interesting tweet");
        Tweet tweet3 = service.tweet(user2, "Jack's first tweet");

        service.likeTweet(user2, tweet1);

        service.retweet(user2, tweet2);

        Tweet happyTweet = service.tweet(user2, "Happy B-day, @Nick!");
        service.reply(user1, happyTweet, "Thank you!");

        service.printTimeline(user1);
        service.printTimeline(user2);

        context.close();
        repoContext.close();
    }
}
