package ua.rd.repository;

import org.springframework.beans.factory.annotation.Lookup;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetRepository {

    void createNewUser(String userName);

    String getUserProfile(String userName);

    long getLikesCount(long tweetId);

    long tweet(String userName, String txt);

    void retweet(String userName, long tweetId);

    boolean doesUserExist(String userName);

    void likeTweet(long tweetId);
}
