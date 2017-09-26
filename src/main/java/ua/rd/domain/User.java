package ua.rd.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
@Component
@Scope(value="prototype")
public final class User {

    private final String name;
    private final List<Tweet> tweets;
    private final List<Tweet> retweets;


    public User(String name) {
        this.name = name;
        tweets = new ArrayList<>();
        retweets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tweets=" + tweets +
                ", retweets=" + retweets +
                '}';
    }

    public void addTweet(Tweet tweet) {
        if(tweet.getUser() != this) {
            throw new DifferentUserException("Tweet's author is "
                    + tweet.getUser().getName() +
                        ". Tried to assign to " + name);
        }
        tweets.add(tweet);
    }

    public void addRetweet(Tweet retweet) {
        retweets.add(retweet);
    }

    public List<Tweet> getAllTweets() {
        return Collections.unmodifiableList(tweets);
    }

    public List<Tweet> getAllRetweets() {
        return Collections.unmodifiableList(retweets);
    }
}
