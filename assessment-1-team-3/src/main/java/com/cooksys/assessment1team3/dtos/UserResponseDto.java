package com.cooksys.assessment1team3.dtos;

import java.sql.Timestamp;

import com.cooksys.assessment1team3.entities.Profile;
import com.cooksys.assessment1team3.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    
	String username;
	ProfileDto profile;
	Timestamp joined;
}
