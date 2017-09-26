package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetService {

    void createNewUser(String userName);

    long tweet(String userName, String txt);

    String userProfile(String userName);

    void likeTweet(long tweetId);

    long getLikesCount(long tweetId);

    boolean doesUserExist(String userName);
}
