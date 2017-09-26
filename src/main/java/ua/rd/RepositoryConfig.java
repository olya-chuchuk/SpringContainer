package ua.rd;

import org.springframework.context.annotation.*;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.InMemTweetRepository;
import ua.rd.repository.TweetRepository;

/**
 * Created by Olha_Chuchuk on 9/22/2017.
 */
@Configuration
public class RepositoryConfig {

    @Bean
    @Scope("prototype")
    //@Profile("default")
    public Tweet tweet(String text, User user) {
        return new Tweet(text, user);
    }

    @Bean
    @Scope("prototype")
    public User user(String name) {
        return new User(name);
    }

    @Bean(initMethod = "init")
    public TweetRepository tweetRepository() {
        return new InMemTweetRepository() {
            @Override
            protected Tweet getNewTweet(User user, String txt) {
                return tweet(txt, user);
            }
            @Override
            public User getNewUser(String userName) {
                return user(userName);
            }
        };
    }

}
