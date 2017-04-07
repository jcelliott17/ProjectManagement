package com.example.jackieelliott.Oasis.Model;


public class CurrentUser {

    private static final String TAG = "LoginActivity-TAG";
    private User currentUser;

    CurrentUser(User curUser) {
        currentUser = curUser;
    }

    public User getUser() {
        return currentUser;
    }

    public void updateUser(User newUser) { currentUser = newUser; }

}