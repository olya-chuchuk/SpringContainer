package ua.rd.domain;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
//@MyTweet("abc")
public class Tweet {

    private Long tweetId;
    private String text;
    private User user;

    public Tweet() {
    }

    public Tweet(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Tweet(Long tweetId, String text, User user) {
        this.tweetId = tweetId;
        this.text = text;
        this.user = user;
    }

    //@Value("user")
    public void setUser(User user) {
        this.user = user;
    }

    public void init() {
        user.setTweet(this);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
