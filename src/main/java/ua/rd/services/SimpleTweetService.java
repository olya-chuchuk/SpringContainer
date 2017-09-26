package ua.rd.services;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.ioc.Benchmark;
import ua.rd.repository.TweetRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */

@Service
public class SimpleTweetService implements TweetService {

    private TweetRepository tweetRepository;

    public SimpleTweetService(TweetRepository tweetRepository) {

        this.tweetRepository = tweetRepository;
    }

    @Override
    public String userProfile(String userName) {
        return tweetRepository.getUserProfile(userName);
    }

    @Override
    public void likeTweet(long tweetId) {
        tweetRepository.likeTweet(tweetId);
    }

    @Override
    public long getLikesCount(long tweetId) {
        return tweetRepository.getLikesCount(tweetId);
    }

    @Override
    public void createNewUser(String userName) {
        tweetRepository.createNewUser(userName);
    }

    @Override
    public long tweet(String userName, String txt) {
        return tweetRepository.tweet(userName, txt);
    }

    @Override
    public boolean doesUserExist(String userName) {
        return tweetRepository.doesUserExist(userName);
    }

}
