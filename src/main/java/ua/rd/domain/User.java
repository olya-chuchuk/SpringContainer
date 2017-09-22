package ua.rd.domain;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public class User {

    private Tweet tweet;
    private String name;

    public User(Tweet tweet) {
        this.tweet = tweet;
    }

    public User(String name) {
        this.name = name;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                "tweet=" + tweet +
                '}';
    }
}
