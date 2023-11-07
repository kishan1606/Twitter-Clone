package com.cooksys.assessment1team3.mappers;

import com.cooksys.assessment1team3.dtos.ProfileDto;
import com.cooksys.assessment1team3.entities.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile requestToEntity(ProfileDto profileDto);

    ProfileDto entityToDto(Profile entity);

    List<ProfileDto> entitiesToDtos(List<Profile> entities);

}
