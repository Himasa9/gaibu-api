package com.example.gaibuapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Tweets {
    ArrayList<SearchedTweet> tweets;

    public class SearchedTweet {
        private String screenName;
        private long id;
        private int random;

        public SearchedTweet(String screenName, long id, int favoriteCount, int retweetCount) {
            this.screenName = screenName;
            this.id = id;

            var i = new Random().nextInt(100);
            this.random = favoriteCount * retweetCount * i;
        }

        public String getUrl() {
            return String.format("https://twitter.com/%s/status/%s", screenName, id);
        }
    }

    private class SearchedTweetComp implements Comparator<SearchedTweet> {
        @Override
        public int compare(SearchedTweet s1, SearchedTweet s2) {
            return Double.compare(s1.random, s2.random);
        }
    }

    public Tweets() {
        tweets = new ArrayList<SearchedTweet>();
    }

    public void add(String screenName, long id, int favoriteCount, int retweetCount) {
        tweets.add(new SearchedTweet(screenName, id, favoriteCount, retweetCount));
    }

    public SearchedTweet select() {
        Collections.sort(tweets, new SearchedTweetComp());
        Collections.reverse(tweets);
        return tweets.get(0);
    }
}