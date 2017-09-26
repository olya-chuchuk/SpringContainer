package ua.rd.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.RepositoryConfig;
import ua.rd.ServiceConfig;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.ioc.ApplicationContext;
import ua.rd.repository.InMemTweetRepository;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olha Chuchuk on 24.09.2017.
 */
public class SimpleTweetServiceTest {
    TweetService service;

    @Before
    public void init() {
        AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(RepositoryConfig.class, ServiceConfig.class);
        service = context.getBean(TweetService.class);
    }

    @After
    public void cleanUp() {
        service = null;
    }

    @Test
    public void tweetTest() {
        String userName = "Test user name";
        String txt = "Test text";
        service.createNewUser(userName);
        service.tweet(userName, txt);

        String tweets = service.userProfile(userName);

        String expected = "Tweets: " +
                "[Tweet{rawTweet=RawTweet{tweetId=0, text='Test text', user=Test user name, " +
                "mentions=[], repliesTo=null}, " +
                "likes=0, retweets=0}] " +
                "Retweets: []";
        assertEquals(expected, tweets);
    }

    @Test
    public void likeTweetTest() {
        String userName = "Test username";
        service.createNewUser(userName);
        long tweetId = service.tweet(userName, "Test text");
        service.likeTweet(tweetId);
    }

}