package ua.rd.repository;

import ua.rd.domain.Tweet;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetRepository {

    Iterable<Tweet> allTweets();
}
