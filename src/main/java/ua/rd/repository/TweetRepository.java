package ua.rd.repository;

import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetRepository {

    Iterable<Tweet> allTweets();

    User createNewUser(String userName);

    Optional<User> getUserByName(String userName);

    User getNewUser(String userName);

    Tweet createAndRegisterNewTweet(User user, String txt);

    List<Tweet> getUserProfile(String userName);
}
