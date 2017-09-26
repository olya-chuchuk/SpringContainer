package ua.rd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

import java.util.Arrays;

/**
 * Created by Olha_Chuchuk on 9/22/2017.
 */
@Configuration
public class ServiceConfig {

//    @Autowired
//    private Environment env;
//
//
//
//    @Bean("tweet")
//    @Scope("prototype")
//    @Profile("dev")
//    public Tweet tweetDev() {
//        Tweet tweet = new Tweet(null, null);
//        if(Arrays.stream(env.getActiveProfiles()).anyMatch("test"::equals)) {
//            tweet.setText("Dev + Text text");
//        } else {
//            tweet.setText("Dev text");
//        }
//        return tweet;
//    }

    @Bean
    public TweetService tweetService(TweetRepository tweetRepository) {
        return new SimpleTweetService(tweetRepository) {
            @Override
            public Tweet newTweet(String text, User user) {
                return new Tweet(text, user);
            }
        };
    }
}
