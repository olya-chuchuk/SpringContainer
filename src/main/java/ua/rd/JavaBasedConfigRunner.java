package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

import java.util.List;

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
        //context.getEnvironment().setActiveProfiles("dev", "test");
        context.refresh();

        TweetService service = context.getBean(TweetService.class);


        String userName = "Test user name";
        String txt = "Test text";
        service.createNewUser(userName);
        User user = service.getUserByName(userName).get();
        service.tweet(user, txt);

        List<Tweet> tweets = service.userProfile(userName);

        System.out.println(tweets);

        context.close();
        repoContext.close();
    }
}
