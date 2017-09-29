package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetService {

    User createNewUser(String userName);

    Tweet tweet(User user, String txt);

    void likeTweet(User user, Tweet tweet);

    void retweet(User user, Tweet tweet);

    Tweet reply(User user, Tweet happyTweet, String txt);

    void printTimeline(User user);

//    long tweet(String userName, String txt);
//
//    String userProfile(String userName);
//
//    void likeTweet(long tweetId);
//
//    long getLikesCount(long tweetId);
//
//    boolean doesUserExist(String userName);
}
