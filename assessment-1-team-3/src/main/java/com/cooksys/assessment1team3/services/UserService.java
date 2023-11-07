package com.cooksys.assessment1team3.services;

import com.cooksys.assessment1team3.dtos.CredentialsDto;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;
import com.cooksys.assessment1team3.dtos.UserRequestDto;
import com.cooksys.assessment1team3.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUserByUsername(String username);

    List<TweetResponseDto> getTweetsByUsername(String username);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUserProfile(String username, UserRequestDto userRequestDto);

    UserResponseDto deleteUserByUsername(String username, CredentialsDto credentialsDto);

    UserResponseDto createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getUsersFollowing(String username);

    List<UserResponseDto> getUserFollowers(String username);
  
    void postUsernameToFollow(String username, CredentialsDto credentialsDto);

    void postUsernameToUnfollow(String username, CredentialsDto credentialsDto);

    List<TweetResponseDto> getFeedByUsername(String username);

    List<TweetResponseDto> getAllTweetsMentioningUser(String username);
  
}
