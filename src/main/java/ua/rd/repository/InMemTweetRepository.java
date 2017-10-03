package ua.rd.repository;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Repository;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;

import java.util.*;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
@Repository
public class InMemTweetRepository implements TweetRepository {

    private Map<Long, Tweet> tweets;
    private Map<String,User> users;

    public InMemTweetRepository() {
        tweets = new HashMap<>();
        users = new HashMap<>();
    }

    @Override
    public User createNewUser(String userName) {
        if(!users.containsKey(userName)) {
            users.put(userName, getNewUser(userName));
            System.out.println("Created user with name \"" + userName + "\"");
        }
        return users.get(userName);
    }

    @Override
    public User getUserByName(String userName) {
        if(!users.containsKey(userName)) {
            throw new NoSuchUserException();
        }
        return users.get(userName);
    }

    @Override
    public Tweet tweet(User user, String txt) {
        Tweet tweet = new Tweet(txt, user);
        user.addTweet(tweet);
        tweets.put(tweet.getId(), tweet);
        System.out.println("User \"" + user.getName() + "\" just tweeted \"" + txt + "\"");
        return tweet;
    }

    @Override
    public Tweet getTweetById(long tweetId) {
        if(!tweets.containsKey(tweetId)) {
            throw new NoSuchTweetException();
        }
        return tweets.get(tweetId);
    }

    @Override
    public long getTweetLikes(Tweet tweet) {
        return tweet.getLikesCount();
    }

    @Override
    public long getTweetRetweets(Tweet tweet) {
        return tweet.getRetweetCount();
    }

    @Override
    public List<Tweet> getUserTweets(User user) {
        return user.getAllTweets();
    }

    @Override
    public List<Tweet> getUserRetweets(User user) {
        return user.getAllRetweets();
    }

    @Override
    public Tweet reply(User user, Tweet tweet, String txt) {
        Tweet newTweet = new Tweet(txt, user, tweet);
        user.addRetweet(newTweet);
        tweet.addReply(newTweet);
        tweets.put(newTweet.getId(), newTweet);
        return newTweet;
    }

    @Override
    public void retweet(User user, Tweet tweet) {
        user.addRetweet(tweet);
        tweet.retweet();
    }

    @Override
    public boolean doesUserExist(String userName) {
        return users.containsKey(userName);
    }

    @Override
    public void likeTweet(Tweet tweet) {
        tweet.like();
    }

    @Override
    public Iterator<Tweet> getUserTimeline(User user) {
        SortedSet timeline = new TreeSet<>(Comparator.comparing(Tweet::getDate));
        timeline.addAll(user.getAllTweets());
        timeline.addAll(user.getAllRetweets());
        return timeline.iterator();
    }

    @Override
    public List<Tweet> getAllTweets() {
        return new LinkedList<>(tweets.values());
    }

    @Lookup
    protected Tweet getNewTweet(String txt, User user) {
        return null;
    }

    @Lookup
    protected User getNewUser(String userName) {
        return null;
    }
}
