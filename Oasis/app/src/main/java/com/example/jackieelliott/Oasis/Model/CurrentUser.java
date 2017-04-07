package com.example.jackieelliott.Oasis.Model;

/**
 * Current User class
 * keeps track of the user currently using the application
 */
public class CurrentUser {

    private static final String TAG = "LoginActivity-TAG";
    private static User currentUser;

    CurrentUser(User curUser) {
        currentUser = curUser;
    }

    /**
     * Gets the user
     * @return the current user
     */
    public static User getUser() {
        return currentUser;
    }

    /**
     * Updates the current user
     * @param newUser new user to replace current user
     */
    public static void updateUser(User newUser) { currentUser = newUser; }

}