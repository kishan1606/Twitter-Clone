package com.cooksys.assessment1team3.services;

public interface ValidateService {
    boolean isUsernameAvailable(String username);

    boolean usernameExists(String username);

    boolean tagExists(String tag);
}
