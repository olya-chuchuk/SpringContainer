package ua.rd.services;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetService {

    Iterable<Tweet> allTweets();

    Tweet newTweet(String text, User user);

    User createNewUser(String userName);

    Optional<User> getUserByName(String userName);

    void init();

    Tweet tweet(User user, String txt);

    List<Tweet> userProfile(String userName);

    void likeTweet(Tweet tweet);

    long getLikesCount(Long id);
}
