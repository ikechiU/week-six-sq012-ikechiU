package com.example.week6_project.dbutil.shared;

public enum Messages {
    SUCCESSFUL_REGISTRATION("Successful registration."),
    UNSUCCESSFUL_REGISTRATION("Unsuccessful registration."),
    INCORRECT_LOGIN_DETAILS("Incorrect login details."),
    SUCCESSFUL_LOGIN("Successful login."),
    CONTACT_TAKEN("Contact taken.");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
