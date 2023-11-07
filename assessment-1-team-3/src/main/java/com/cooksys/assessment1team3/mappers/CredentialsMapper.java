package com.cooksys.assessment1team3.mappers;

import com.cooksys.assessment1team3.dtos.CredentialsDto;
import com.cooksys.assessment1team3.entities.Credentials;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    CredentialsDto entityToDto(Credentials credentials);

    Credentials requestToEntity(CredentialsDto credentialsDto);

    //I don't think we need to return a  list of credentials ever? But here it is just in case
    List<CredentialsDto> entitiesToDtos(List<Credentials> entities);
}
