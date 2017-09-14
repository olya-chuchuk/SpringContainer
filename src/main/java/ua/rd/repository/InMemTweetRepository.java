package ua.rd.repository;

import ua.rd.domain.Tweet;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public class InMemTweetRepository implements TweetRepository {

    private List<Tweet> tweets;

    public void init() {
        tweets = Arrays.asList(new Tweet(1L, "First Mesg", null));
        tweets = Arrays.asList(new Tweet(2L, "Second Mesg", null));
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }
}
