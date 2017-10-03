package ua.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.services.TweetService;

import java.util.List;

/**
 * Created by Olha_Chuchuk on 10/3/2017.
 */
@Controller
public class TweetController {

    @Autowired
    TweetService service;
//
//    @GetMapping("/tweet")
//    public String tweet(Model model) {
//        User user = service.createNewUser("Nick");
//        Tweet tweet = service.createTweet(user, "Some txt");
//        model.addAttribute(tweet);
//        return "tweet";
//    }

    @PostMapping("/tweet")
    public String tweet(@RequestParam("tweetText") String tweetText,
                        @RequestParam("userName") String username,
                        Model model) {
        User user = service.createNewUser(username);
        Tweet tweet = service.createTweet(user, tweetText);
        model.addAttribute(tweet);
        return "tweet";
    }

    @GetMapping("/create_tweet")
    public String createTweet() {
        return "create_tweet";
    }

    @GetMapping("/start")
    public String start(Model model) {
        return "start";
    }

    @GetMapping("/all_tweets")
    public String allTweets(Model model) {
        List<Tweet> tweetList = service.getAllTweets();
        System.out.println(tweetList);
        model.addAttribute(tweetList);
        return "all_tweets";
    }

    @PostMapping("/all_tweets")
    public String allTweets(@RequestParam("tweetId") int tweetId,
                            @RequestParam("text") String text,
                            Model model) {
        List<Tweet> tweetList = service.getAllTweets();
        System.out.println(tweetList);
        model.addAttribute(tweetList);
        return "all_tweets";
    }

}
