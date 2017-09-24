package ua.rd.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.rd.repository.InMemTweetRepository;
import ua.rd.repository.TweetRepository;
import ua.rd.services.SimpleTweetService;
import ua.rd.services.TweetService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Olha Chuchuk on 24.09.2017.
 */
public class TweetTest {
    TweetService service;
    TweetRepository repository;

    @Before
    public void init() {
        repository = new InMemTweetRepository();
        service = new SimpleTweetService(repository);
    }
    @After
    public void cleanUp() {
        service = null;
    }
    @Test
    public void createNewTweetTest() {
        String txt = "New tweet";
        User user = new User("userName");
        Tweet tweet = new Tweet(txt, user);

        assertEquals(tweet.getText(), txt);
        assertEquals(tweet.getUser(), user);
        assertEquals(tweet.getLikesCount(), new Long(0));
        assertFalse(tweet.getRepliesTo().isPresent());
        assertTrue(tweet.getMentions().isEmpty());
    }

    @Test
    public void createAndLikesTest() {
        String txt = "New tweet";
        User user = new User("userName");
        Tweet tweet = new Tweet(txt, user);
        for(int i = 0; i < 3; ++i) {
            tweet.like();
        }

        assertEquals(tweet.getLikesCount(), new Long(3));
    }

    @Test
    public void createAndRetweetTest() {
        String txt = "New tweet";
        User user = new User("userName");
        Tweet tweet = new Tweet(txt, user);
        for(int i = 0; i < 3; ++i) {
            tweet.retweet();
        }

        assertEquals(tweet.getRetweetCount(), new Long(3));
    }

    @Test
    public void addMentionsInText() {
        String name1 = "Jack";
        String name2 = "Nick";
        String txt = "Some @" + name1 + " text @" + name2;
        User user = new User("userName");
        Tweet tweet = new Tweet(txt, user);

        List<String> mentions = tweet.getMentions();

        assertEquals(mentions.size(), 2);
        System.out.println(mentions);
        assertTrue(mentions.contains(name1));
        assertTrue(mentions.contains(name2));
    }

}