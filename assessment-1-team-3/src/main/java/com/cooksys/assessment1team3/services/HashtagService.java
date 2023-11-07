package com.cooksys.assessment1team3.services;

import com.cooksys.assessment1team3.dtos.HashtagDto;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;

import java.util.List;

public interface HashtagService {
    List<HashtagDto> getAllHashtags();
    List<TweetResponseDto> getTagsByLabel(String label);
}
