package com.example.jackieelliott.Oasis.Model;


public class CurrentUser {

    private static final String TAG = "LoginActivity-TAG";
    private static User currentUser;

    CurrentUser(User curUser) {
        currentUser = curUser;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void updateUser(User newUser) { currentUser = newUser; }

}