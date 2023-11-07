package com.cooksys.assessment1team3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1904940572967091014L;

    private String message;
}