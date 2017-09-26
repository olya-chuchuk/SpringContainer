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

    Iterable<Tweet> allTweets();

    User createNewUser(String userName);

    Optional<User> getUserByName(String userName);

    User getNewUser(String userName);

    Tweet createAndRegisterNewTweet(User user, String txt);

    List<Tweet> getUserProfile(String userName);

    Optional<Tweet> getTweetById(Long id);

    Tweet getNewTweet(String txt, User user);
}
