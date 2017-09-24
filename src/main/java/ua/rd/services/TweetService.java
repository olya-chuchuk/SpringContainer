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

    Tweet newTweet();

    User createNewUser(String userName);

    Optional<User> getUserByName(String userName);

    void init();

    void tweet(User user, String txt);

    List<Tweet> userProfile(String userName);
}
