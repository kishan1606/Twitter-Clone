package com.cooksys.assessment1team3.services.impl;

import com.cooksys.assessment1team3.dtos.HashtagDto;
import com.cooksys.assessment1team3.mappers.HashtagMapper;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;
import com.cooksys.assessment1team3.entities.Hashtag;
import com.cooksys.assessment1team3.entities.Tweet;
import com.cooksys.assessment1team3.exceptions.NotFoundException;
import com.cooksys.assessment1team3.mappers.TweetMapper;
import com.cooksys.assessment1team3.repositories.HashtagRepository;
import com.cooksys.assessment1team3.repositories.TweetRepository;
import com.cooksys.assessment1team3.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
  
    public List<HashtagDto> getAllHashtags() {
        return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
    }

    @Override
    public List<TweetResponseDto> getTagsByLabel(String label) {
        String newLabel = label;
        Hashtag hashtag = hashtagRepository.findByLabel(newLabel);
        if (hashtag == null) {
            throw new NotFoundException("No Hashtags found with tags: " + newLabel);
        }
        List<Tweet> tweets = tweetRepository.findAllByDeletedFalseAndHashtagsOrderByPostedDesc(hashtag);
        List<TweetResponseDto> tweetResponseDtos = tweetMapper.entitiesToDtos(tweets);
        return tweetResponseDtos;
    }
}
