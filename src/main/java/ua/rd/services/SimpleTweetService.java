package ua.rd.services;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.ioc.Benchmark;
import ua.rd.repository.TweetRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */





@Service
public class SimpleTweetService implements TweetService {

//    @Autowired
    private TweetRepository tweetRepository;
    private Tweet tweet;

    //@Autowired
    public void fill(TweetRepository tweetRepository, Tweet tweet) {
        this.tweetRepository = tweetRepository;
        this.tweet = tweet;
    }

    //@Required
    //@Autowired
    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void init() {
    }

    @Override
    public Tweet tweet(User user, String txt) {
        Tweet tweet = tweetRepository.createAndRegisterNewTweet(user, txt);
        user.addTweet(tweet);
        return tweet;
    }

    @Override
    public List<Tweet> userProfile(String userName) {
        return tweetRepository.getUserProfile(userName);
    }

    @Override
    public void likeTweet(Tweet tweet) {
        tweet.like();
    }

    @Override
    public long getLikesCount(Long id) {
        Tweet tweet = tweetRepository.getTweetById(id).orElseThrow(NoSuchTweetException::new);
        return tweet.getLikesCount();
    }


    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }

    @Override
    @Lookup
    //@Benchmark
    public Tweet newTweet(String text, User user) {
        return tweet;
    }

    @Override
    public User createNewUser(String userName) {
        return tweetRepository.createNewUser(userName);
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return tweetRepository.getUserByName(userName);
    }
}
