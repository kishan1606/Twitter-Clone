package com.cooksys.assessment1team3.services;

import com.cooksys.assessment1team3.dtos.*;

import java.util.List;

public interface TweetService {
    List<TweetResponseDto> getAllTweets();

    List<TweetResponseDto> getTweetRepliesById(Long id);

    ContextDto getTweetContext(Long id);

    TweetResponseDto repostTweet(Long id, CredentialsDto credentialsDto);

    List<UserResponseDto> getTweetLikes(Long id);

    TweetResponseDto getTweetById(Long id);

    TweetResponseDto deleteTweetById(Long id, CredentialsDto credentialsDto);

    TweetResponseDto postReplyTweetWithId(Long id, TweetRequestDto tweetRequestDto);

    List<HashtagDto> getHashtagsByTweet(Long id);

    void likeTweetById(Long id, CredentialsDto credentialsDto);

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    List<TweetResponseDto> getTweetRepostsById(Long id);

    List<UserResponseDto> getTweetMentions(Long id);
}
