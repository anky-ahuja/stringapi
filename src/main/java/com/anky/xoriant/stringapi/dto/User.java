package com.anky.xoriant.stringapi.dto;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String emailId;
    private String location;
    private String organization;

    public User() {
    }
    public User(int userId, String userName, String emailId, String location, String organization) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.location = location;
        this.organization = organization;
    }
}
