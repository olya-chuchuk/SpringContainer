package ua.rd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.rd.repository.InMemTweetRepository;
import ua.rd.repository.TweetRepository;

/**
 * Created by Olha_Chuchuk on 9/22/2017.
 */
@Configuration
public class RepositoryConfig {

    @Bean(initMethod = "init")
    public TweetRepository tweetRepository() {
        return new InMemTweetRepository();
    }

}
