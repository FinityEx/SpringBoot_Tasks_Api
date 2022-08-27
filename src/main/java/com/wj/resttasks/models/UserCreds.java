package com.wj.resttasks.models;

// POJO to store credentials
public class UserCreds {

    private String username;
    private String password;

    public UserCreds() {
    }

    public UserCreds(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
