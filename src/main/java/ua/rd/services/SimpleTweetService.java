package ua.rd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import ua.rd.domain.Tweet;
import ua.rd.repository.TweetRepository;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */



public class SimpleTweetService implements TweetService {

//    @Autowired
    private TweetRepository tweetRepository;
    private Tweet tweet;

    @Autowired
    public void fill(TweetRepository tweetRepository, Tweet tweet) {
        this.tweetRepository = tweetRepository;
        this.tweet = tweet;
    }

    @Required
    @Autowired
    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }


    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }

    @Override
    @Lookup
    public Tweet newTweet() {
        return new Tweet();
    }
}
