package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.domain.Tweet;
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
        context.getEnvironment().setActiveProfiles("dev", "test");
        context.refresh();

        TweetService tweetService = context.getBean(TweetService.class);

        System.out.println(tweetService.newTweet() == tweetService.newTweet());
        System.out.println(context.getBean(Tweet.class));
    }
}
