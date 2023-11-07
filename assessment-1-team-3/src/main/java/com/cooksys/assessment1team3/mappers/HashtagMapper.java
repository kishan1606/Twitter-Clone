package com.cooksys.assessment1team3.mappers;

import com.cooksys.assessment1team3.dtos.HashtagDto;
import com.cooksys.assessment1team3.entities.Hashtag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    HashtagDto entityToDto(Hashtag hashtag);

    List<HashtagDto> entitiesToDtos(List<Hashtag> entities);

    Hashtag requestToEntity(HashtagDto hashtagDto);
}
