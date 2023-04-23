package com.example.careerq.exceptions;

public class PasswordException extends Exception {
    public PasswordException(String errMsg) {
        super(errMsg);
    }
}


// Minimum8CharactersRequired - Raised if the password is less than 8 characters.
class Minimum8CharactersRequired extends PasswordException {
    public Minimum8CharactersRequired() {
        super("Password length must be at least 8 characters");
    }
}


// NumberCharacterMissing - Raised if the password doesn't have any numbers in it.
class NumberCharacterMissing extends PasswordException {
    public NumberCharacterMissing() {
        super("Password requires some numbers");
    }
}


// SpecialCharacterMissing - Raised if a special character is missing in the password.
class SpecialCharacterMissing extends PasswordException {
    public SpecialCharacterMissing() {
        super("Password requires at least one special character");
    }
}

//UpperCaseCharacterMissing - Raised if upper case character is missing in the password.
class UpperCaseCharacterMissing extends PasswordException {
	public UpperCaseCharacterMissing() {
		super("Password requires at least one upper case character");
	}
}

//LowerCaseCharacterMissing - Raised if a lower case character is missing in the password.
class LowerCaseCharacterMissing extends PasswordException {
	public LowerCaseCharacterMissing() {
		super("Password requires at least one lower case character");
	}
}