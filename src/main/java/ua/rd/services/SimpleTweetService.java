package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.repository.TweetRepository;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */



public class SimpleTweetService implements TweetService {

    private TweetRepository tweetRepository;
    private Tweet tweet;

    public SimpleTweetService(TweetRepository tweetRepository, Tweet tweet) {
        this.tweet = tweet;
        this.tweetRepository = tweetRepository;
    }


    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }
}
