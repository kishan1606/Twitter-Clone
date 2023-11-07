package com.cooksys.assessment1team3.mappers;

import com.cooksys.assessment1team3.dtos.UserRequestDto;
import com.cooksys.assessment1team3.dtos.UserResponseDto;
import com.cooksys.assessment1team3.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, CredentialsMapper.class})
public interface UserMapper {

    // @ Mapping throws error: java: Unknown property "username" in result type UserResponseDto. Did you mean "user"?
    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToDto(User user);

    List<UserResponseDto> entitiesToDtos(List<User> entities);

    User requestToEntity(UserRequestDto userRequestDto);
}
