package ua.rd.repository;

import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets;

    @PostConstruct
    public void init() {
        tweets = Arrays.asList(new Tweet(1L, "First Mesg", null));
        tweets = Arrays.asList(new Tweet(2L, "Second Mesg", null));
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }
}
