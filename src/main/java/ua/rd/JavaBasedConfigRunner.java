package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

/**
 * Created by Olha_Chuchuk on 9/22/2017.
 */
public class JavaBasedConfigRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new AnnotationConfigApplicationContext(RepositoryConfig.class);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.setParent(repoContext);
        context.register(ServiceConfig.class);
        context.refresh();

        TweetService service = context.getBean(TweetService.class);

        User user1 = service.createNewUser("Nick");
        User user2 = service.createNewUser("Jack");

        Tweet tweet1 = service.createTweet(user1, "Nick's first tweet");
        Tweet tweet2 = service.createTweet(user1, "Some interesting tweet");
        Tweet tweet3 = service.createTweet(user2, "Jack's first tweet");

        service.likeTweet(user2, tweet1);

        service.retweet(user2, tweet2);

        Tweet happyTweet = service.createTweet(user2, "Happy B-day, @Nick!");
        service.reply(user1, happyTweet, "Thank you!");

        service.printTimeline(user1);
        service.printTimeline(user2);

        context.close();
        repoContext.close();
    }
}
