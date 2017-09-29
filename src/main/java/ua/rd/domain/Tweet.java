package ua.rd.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.services.TweetService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
@Component
@Scope(value="prototype")
public final class Tweet {

    private RawTweet rawTweet;
    private long likes;
    private long retweets;
    private List<Tweet> replies;

    public Tweet(String text, User user) {
        this(text, user, null);
    }

    public Tweet(String text, User user, Tweet repliesTo) {
        rawTweet = new RawTweet(text, user, repliesTo);
        likes = 0L;
        retweets = 0L;
        replies = new LinkedList<>();
    }

    public String getText() {
        return rawTweet.getText();
    }

    public User getUser() {
        return rawTweet.getUser();
    }

    public long getLikesCount() {
        return likes;
    }

    public Optional<Tweet> getRepliesTo() {
        return rawTweet.getRepliesTo();
    }

    public List<String> getMentions() {
        return rawTweet.getMentions();
    }

    public void like() {
        likes++;
    }

    public void retweet() {
        retweets++;
    }

    public long getRetweetCount() {
        return retweets;
    }

    public long getId() {
        return rawTweet.getId();
    }

    public Date getDate() {
        return rawTweet.getDate();
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "rawTweet=" + rawTweet +
                ", likes=" + likes +
                ", retweets=" + retweets +
                '}';
    }

    public void addReply(Tweet newTweet) {
        replies.add(newTweet);
    }

    /**
     * fully immutable tweet
     */
    private static class RawTweet {

        private static Long tweetIds;

        private final Long tweetId;
        private final String text;
        private final User user;
        private final List<String> mentions;
        private final Tweet repliesTo;
        private final Date date;

        static
        {
            tweetIds = 0L;
        }

        public RawTweet(String text, User user, Tweet repliesTo) {
            this(tweetIds++, text, user, repliesTo);
        }

        private RawTweet(Long tweetId, String text, User user, Tweet repliesTo) {
            this.tweetId = tweetId;
            this.text = text;
            this.user = user;
            this.date = new Date();
            mentions = new LinkedList<>();
            Pattern mentionPattern = Pattern.compile("@([a-zA-Z]+)");
            Matcher matcher = mentionPattern.matcher(text);
            while (matcher.find()) {
                String userName = matcher.group(1);
                mentions.add(userName);
            }
            this.repliesTo = repliesTo;
        }

        public String getText() {
            return text;
        }

        public User getUser() {
            return user;
        }

        public Optional<Tweet> getRepliesTo() {
            return Optional.ofNullable(repliesTo);
        }

        public List<String> getMentions() {
            return Collections.unmodifiableList(mentions);
        }

        public Long getId() {
            return tweetId;
        }

        @Override
        public String toString() {
            return "RawTweet{" +
                    "tweetId=" + tweetId +
                    ", text='" + text + '\'' +
                    ", user=" + user.getName() +
                    ", mentions=" + mentions +
                    ", repliesTo=" + repliesTo +
                    '}';
        }

        public Date getDate() {
            return date;
        }
    }
}