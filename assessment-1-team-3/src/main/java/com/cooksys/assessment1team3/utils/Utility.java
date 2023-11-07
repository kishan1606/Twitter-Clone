package com.cooksys.assessment1team3.utils;

import com.cooksys.assessment1team3.dtos.TweetRequestDto;
import com.cooksys.assessment1team3.dtos.UserRequestDto;
import com.cooksys.assessment1team3.entities.Credentials;
import com.cooksys.assessment1team3.entities.Hashtag;
import com.cooksys.assessment1team3.entities.Tweet;
import com.cooksys.assessment1team3.entities.User;
import com.cooksys.assessment1team3.exceptions.BadRequestException;
import com.cooksys.assessment1team3.exceptions.NotAuthorizedException;
import com.cooksys.assessment1team3.exceptions.NotFoundException;
import com.cooksys.assessment1team3.repositories.HashtagRepository;
import com.cooksys.assessment1team3.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Data
@Component
public class Utility {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public void validateUserEmail(String email) {
        if (email == null) {
            throw new BadRequestException("An email is required to update user profile.");
        }
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new BadRequestException("You must pass a valid email to update the user profile.");
        }
    }

    public void validateCredentials(User user, Credentials credentials) {
        if (user == null || user.getCredentials() == null) {
            throw new BadRequestException("Credentials are required.");
        }
        if (!user.getCredentials().getUsername().equals(credentials.getUsername())
                || !user.getCredentials().getPassword().equals(credentials.getPassword())) {
            // we could pass in a specific string here for each case, but we'd need another parameter
            throw new NotAuthorizedException("You are not authorized to perform this action.");
        }
    }

    private List<String> filterByStartingCharacters(String filterBy, String toFilter) {
        ArrayList<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(filterBy + "\\w+");

        Matcher matcher = pattern.matcher(toFilter);
        while (matcher.find())
        {
            results.add(matcher.group().replace(filterBy,""));
        }
        return results;
    }

    public List<User> getMentionedUsers(String content, UserRepository userRepository) {
        ArrayList<User> mentioned = new ArrayList<>();
        List<String> potentialUsers = filterContentForUsers(content);
        for (String username: potentialUsers) {
            User user = userRepository.findByCredentialsUsername(username);
            if (user != null) {
                mentioned.add(user);
            }
        }
        return mentioned;
    }

    public void validateCreateUser(UserRequestDto userRequestDto) {
        if (userRequestDto == null) {
            throw new BadRequestException("Profile and Credentials are required.");
        }
        if (userRequestDto.getCredentials() == null) {
            throw new BadRequestException("Credentials are required.");
        }
        if (userRequestDto.getProfile() == null) {
            throw new BadRequestException("Profile is required.");
        }
        if (userRequestDto.getProfile().getEmail() == null || userRequestDto.getProfile().getEmail().isBlank()) {
            throw new BadRequestException("Email is required.");
        }
    }

    public void validateCreateTweet(TweetRequestDto tweetRequestDto) {
        if (tweetRequestDto.getCredentials() == null) {
            throw new BadRequestException("Credentials are required.");
        }
    }

    public List<Hashtag> getMentionedHashtags(String content, HashtagRepository hashtagRepository) {
        ArrayList<Hashtag> mentioned = new ArrayList<>();
        List<String> hashtags = filterContentForHashtags(content);
        for (String label: hashtags) {
            Hashtag hashtag = hashtagRepository.findByLabel(label);
            if (hashtag == null) {
                hashtag = new Hashtag();
                hashtag.setLabel(label);
                hashtagRepository.saveAndFlush(hashtag);
            }
            mentioned.add(hashtag);
        }
        return mentioned;
    }

    private List<String> filterContentForUsers(String content) {
        return filterByStartingCharacters("@", content);
    }

    private List<String> filterContentForHashtags(String content) {
        return filterByStartingCharacters("#", content);
    }

    public void validateUserRequest(UserRequestDto userRequestDto) {
        String username = userRequestDto.getCredentials().getUsername();
        if (username == null || username.isBlank()) {
            throw new BadRequestException("Username is required.");
        }
        String password = userRequestDto.getCredentials().getPassword();
        if (password == null || password.isBlank()) {
            throw new BadRequestException("Password is required.");
        }
        String email = userRequestDto.getProfile().getEmail();
        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email is required.");
        }
    }

    public void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BadRequestException("Content is required.");
        }
    }

    public void validateUserExists(User user, String username) {
        if (user == null || user.isDeleted()) {
            throw new NotFoundException("No user found with username: " + username);
        }
    }

    public void validateTweetExists(Tweet tweet, Long id) {
        if (tweet == null || tweet.isDeleted()) {
            throw new NotFoundException("No tweet found with id: " + id);
        }
    }

    public void validateUserFollower(User user, User userToCheck){
        if(user.getFollowers().contains(userToCheck)){
            throw new NotAuthorizedException("You are Already following "+userToCheck.getCredentials().getUsername()+ ".");
        }
    }
}
