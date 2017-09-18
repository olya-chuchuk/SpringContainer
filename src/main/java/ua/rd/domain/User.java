package ua.rd.domain;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public class User {

    private Tweet tweet;

    public User(Tweet tweet) {
        this.tweet = tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
