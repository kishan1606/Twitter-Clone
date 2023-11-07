package com.cooksys.assessment1team3.mappers;

import com.cooksys.assessment1team3.dtos.TweetRequestDto;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;
import com.cooksys.assessment1team3.entities.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    TweetResponseDto entityToDto (Tweet tweet);

    List<TweetResponseDto> entitiesToDtos (List<Tweet> entities);

    Tweet requestToEntity (TweetRequestDto tweetRequestDto);
}
