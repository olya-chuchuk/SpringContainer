package ua.rd.repository;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetRepository {

    User createNewUser(String userName);

    User getUserByName(String userName);

    Tweet tweet(User user, String txt);

    Tweet getTweetById(long tweetId);

    long getTweetLikes(Tweet tweet);

    long getTweetRetweets(Tweet tweet);

    List<Tweet> getUserTweets(User user);

    List<Tweet> getUserRetweets(User user);

    Tweet reply(User user, Tweet tweet, String txt);

    void retweet(User user, Tweet tweet);

    boolean doesUserExist(String userName);

    void likeTweet(Tweet tweet);

    Iterator<Tweet> getUserTimeline(User user);

    List<Tweet> getAllTweets();
}
