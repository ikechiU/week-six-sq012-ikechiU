package com.example.week6_project.dao.shared;

public enum Messages {
    SUCCESSFUL_REGISTRATION("Successful registration."),
    SUCCESSFUL_UPDATE("Account update successful."),
    UNSUCCESSFUL_REGISTRATION("Unsuccessful registration."),
    UNSUCCESSFUL_UPDATE("Unsuccessful update."),
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
