package com.cooksys.assessment1team3.controllers;
import com.cooksys.assessment1team3.services.TweetService;

import com.cooksys.assessment1team3.dtos.ContextDto;
import com.cooksys.assessment1team3.dtos.CredentialsDto;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;
import com.cooksys.assessment1team3.dtos.HashtagDto;
import com.cooksys.assessment1team3.dtos.UserResponseDto;


import com.cooksys.assessment1team3.dtos.*;
import com.cooksys.assessment1team3.services.TweetService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/{id}/replies")
    public List<TweetResponseDto> getTweetRepliesById(@PathVariable Long id) {
        return tweetService.getTweetRepliesById(id);
    }


    @GetMapping("/{id}/context")
    public ContextDto getTweetContext(@PathVariable Long id) {
        return tweetService.getTweetContext(id);
    }

    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getTweetRepostsById(@PathVariable Long id) {
        return tweetService.getTweetRepostsById(id);
    }

    @PostMapping("/{id}/repost")
    public TweetResponseDto repostTweet(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.repostTweet(id, credentialsDto);
    }

    @GetMapping("/{id}/likes")
    public List<UserResponseDto> getTweetLikes(@PathVariable Long id) {
        return tweetService.getTweetLikes(id);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getTweetMentions(@PathVariable Long id) {
        return tweetService.getTweetMentions(id);
    }

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweetById(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.deleteTweetById(id, credentialsDto);
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable Long id) {
        return tweetService.getTweetById(id);
    }

    @PostMapping("/{id}/reply")
    public TweetResponseDto postReplyTweetWithId(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.postReplyTweetWithId(id, tweetRequestDto);
    }

    @GetMapping("/{id}/tags")
    public List<HashtagDto> getHashtagsByTweet(@PathVariable Long id) {
        return tweetService.getHashtagsByTweet(id);
    }

    @PostMapping("/{id}/like")
    public void likeTweetById(@PathVariable Long id, @RequestBody CredentialsDto credentialsDto) {
        tweetService.likeTweetById(id, credentialsDto);
    }

    @PostMapping
    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }

}
