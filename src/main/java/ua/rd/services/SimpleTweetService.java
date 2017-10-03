package ua.rd.services;

import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.repository.TweetRepository;

import java.util.List;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */

@Service
public class SimpleTweetService implements TweetService {

    private TweetRepository tweetRepository;

    @Override
    public User createNewUser(String userName) {
        User user = tweetRepository.createNewUser(userName);
        return user;
    }

    @Override
    public Tweet createTweet(User user, String txt) {
        Tweet tweet = tweetRepository.tweet(user, txt);
        return tweet;
    }

    @Override
    public void likeTweet(User user, Tweet tweet) {
        tweetRepository.likeTweet(tweet);
    }

    @Override
    public void retweet(User user, Tweet tweet) {
        tweetRepository.retweet(user, tweet);
        System.out.println("User \"" + user.getName() + "\" retweeted: \"" + tweet.getText() + "\"");
    }

    @Override
    public Tweet reply(User user, Tweet tweet, String txt) {
        Tweet newTweet = tweetRepository.reply(user, tweet, txt);
        System.out.println("User \"" + user.getName() + "\" replied to \"" +
                tweet.getUser().getName() + "\": \"" + txt + "\"");
        return newTweet;
    }

    @Override
    public void printTimeline(User user) {
        System.out.println(user.getName() + "'s timeline:");
        System.out.println("TWEETS:");
        user.getAllTweets().stream().forEach(System.out::println);
        System.out.println("RETWEETS:");
        user.getAllRetweets().stream().forEach(System.out::println);
        System.out.println();
    }

    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.getAllTweets();
    }

    public SimpleTweetService(TweetRepository tweetRepository) {

        this.tweetRepository = tweetRepository;
    }

}
