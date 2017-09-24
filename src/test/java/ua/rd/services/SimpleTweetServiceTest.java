package ua.rd.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
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
        service = new SimpleTweetService(new InMemTweetRepository());
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
        User user = service.getUserByName(userName).get();
        service.tweet(user, txt);

        List<Tweet> tweets = service.userProfile(userName);

        assertEquals(tweets.size(), 1);
        assertEquals(tweets.get(0).getText(), txt);
        assertTrue(tweets.get(0).getUser() == user);
    }

}