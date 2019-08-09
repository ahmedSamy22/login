package com.example.login;

public class UsersData {

    String userId;
    String userName;
    String userUniv;
    String userTerm;
    String email;
    String level;

    UsersData(){}

    public UsersData(String userId, String userName, String userUniv, String userTerm, String email, String level) {
        this.userId = userId;
        this.userName = userName;
        this.userUniv = userUniv;
        this.userTerm = userTerm;
        this.email = email;
        this.level = level;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserUniv() {
        return userUniv;
    }

    public String getUserTerm() {
        return userTerm;
    }

    public String getEmail() {
        return email;
    }

    public String getLevel() {
        return level;
    }
}
