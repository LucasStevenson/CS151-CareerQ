package com.example.careerq.exceptions;

import com.google.gson.JsonObject;

public class ExceptionHandler {
	private void checkPassword(String password) throws PasswordException {
	    if (password.length() < 8) {
	    	throw new Minimum8CharactersRequired();
	    }
	    boolean hasUpperCaseChar = false, hasLowerCaseChar = false, hasSpecialChar = false, hasNumber = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCaseChar = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCaseChar = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else {
                // at this point, it's not a lowercase letter, uppercase letter, or number
                // so it must be a special char
                hasSpecialChar = true;
            }
        }
        if (!hasUpperCaseChar) throw new UpperCaseCharacterMissing();
        if (!hasLowerCaseChar) throw new LowerCaseCharacterMissing();
        if (!hasSpecialChar) throw new SpecialCharacterMissing();
        if (!hasNumber) throw new NumberCharacterMissing();
    }
	
	public String registerValidation(JsonObject obj) {
		// first check if all the required fields are there
		if (!(obj.has("email") && obj.has("password") && obj.has("name") && obj.has("userType"))) {
			return "Missing required regitration fields";
    	}
		// then check that the email and password are valid
		String password = obj.get("password").getAsString();
		String email = obj.get("email").getAsString();
		if (email.length() < 6 || !email.contains("@")) {
			return "Invalid email";
		}
		try {
			this.checkPassword(password);
		} catch (PasswordException pE) {
			return pE.getMessage();
		}
		// check name field
		String name = obj.get("name").getAsString();
		if (name.length() < 2) {
			return "Name field must be at least 2 characters long";
		}
		// check that the userType is present and valid. Must be one of (student, company, school)
		String userType = obj.get("userType").getAsString();
		if (!(userType.equals("student") || userType.equals("company") || userType.equals("school"))) {
			return "Invalid user type";
		}
		// if we get to this point, then the registration body is all good
		return "Success";
	}
}
