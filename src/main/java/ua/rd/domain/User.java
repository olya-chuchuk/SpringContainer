package ua.rd.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public final class User {

    private final String name;
    private final List<Tweet> tweets;


    public User(String name) {
        this.name = name;
        tweets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tweets=" + tweets +
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

    public List<Tweet> getAllTweets() {
        return Collections.unmodifiableList(tweets);
    }
}
