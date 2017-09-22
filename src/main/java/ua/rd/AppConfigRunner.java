package ua.rd;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.Arrays;

/**
 * Created by Olha_Chuchuk on 9/22/2017.
 */
@Configuration
public class AppConfigRunner {

//    @Autowired
//    private Environment env;

    @Bean("tweet")
    @Lazy
   // @Scope("prototype")
    public Tweet tweet() {
        System.out.println("Method tweet() called");
        return new Tweet();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfigRunner.class);

        BeanDefinition bd = context.getBeanFactory().getBeanDefinition("tweet");
        System.out.println(bd);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        Tweet tweet = (Tweet) context.getBean("tweet");
        User user = (User) context.getBean("user");
        System.out.println(tweet);
        System.out.println(user);
        System.out.println(context.getBean(AppConfigRunner.class).getClass());

        context.getEnvironment().setActiveProfiles("dev");
        System.out.println(Arrays.toString(context.getEnvironment().getActiveProfiles()));
    }

    @Bean
    @Lazy
    public User user(Tweet tweet) {
        User user = new User("Olya");
        user.setTweet(tweet());
        return user;
    }
}
