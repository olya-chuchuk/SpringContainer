package ua.rd.repository;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository {

    private Map<Long, Tweet> tweets;
    private Map<String,User> users;

    public InMemTweetRepository() {
        tweets = new HashMap<>();
        users = new HashMap<>();
    }
    @PostConstruct
    public void init() {
//        tweets = Arrays.asList(new Tweet(1L, "First Mesg", null));
//        tweets = Arrays.asList(new Tweet(2L, "Second Mesg", null));
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets.values();
    }

    @Override
    public User createNewUser(String userName) {
        if(!users.containsKey(userName)) {
            users.put(userName, getNewUser(userName));
        }
        return users.get(userName);
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return Optional.ofNullable(users.get(userName));
    }

    @Override
    @Lookup("user")
    public User getNewUser(String userName) {
        //TODO replace by lookup method in configuration
        return null;
    }

    @Override
    public Tweet createAndRegisterNewTweet(User user, String txt) {
        Tweet newTweet = getNewTweet(txt, user);
        tweets.put(newTweet.getId(), newTweet);
        return newTweet;
    }

    @Override
    public List<Tweet> getUserProfile(String userName) {
        User user = getUserByName(userName).orElseThrow(NoUserWithSuchNameException::new);
        return user.getAllTweets();
    }

    @Override
    public Optional<Tweet> getTweetById(Long id) {
        return Optional.ofNullable(tweets.get(id));
    }

    @Override
    @Lookup()
    public Tweet getNewTweet(String txt, User user) {
        //TODO replace by lookup method in configuration
        return null;
    }

}
