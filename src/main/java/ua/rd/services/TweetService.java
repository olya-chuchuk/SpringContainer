package ua.rd.services;

import ua.rd.domain.Tweet;

/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public interface TweetService {

    Iterable<Tweet> allTweets();

    Tweet newTweet();
}
