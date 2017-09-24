package ua.rd.domain;

import org.springframework.stereotype.Component;
import ua.rd.services.TweetService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Olha_Chuchuk on 9/11/2017.
 */
public final class Tweet {

    private RawTweet rawTweet;
    private Long likes;
    private Long retweets;

    public Tweet(String text, User user) {
        this(text, user, null);
    }

    public Tweet(String text, User user, User repliesTo) {
        rawTweet = new RawTweet(text, user, repliesTo);
        likes = 0L;
        retweets = 0L;
    }

    public String getText() {
        return rawTweet.getText();
    }

    public User getUser() {
        return rawTweet.getUser();
    }

    public Long getLikesCount() {
        return likes;
    }

    public Optional<User> getRepliesTo() {
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

    public Long getRetweetCount() {
        return retweets;
    }

    public Long getId() {
        return rawTweet.getId();
    }

    public void setText(String text) {
        rawTweet = new RawTweet(text, rawTweet.getUser(), rawTweet.getRepliesTo().get());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "rawTweet=" + rawTweet +
                ", likes=" + likes +
                ", retweets=" + retweets +
                '}';
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
        private final User repliesTo;

        static
        {
            tweetIds = 0L;
        }

        public RawTweet(String text, User user, User repliesTo) {
            this(tweetIds++, text, user, repliesTo);
        }

        private RawTweet(Long tweetId, String text, User user, User repliesTo) {
            this.tweetId = tweetId;
            this.text = text;
            this.user = user;
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

        public Optional<User> getRepliesTo() {
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
    }
}