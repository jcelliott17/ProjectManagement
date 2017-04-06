package com.example.jackieelliott.Oasis.Model;

/**
 * Created by JackieElliott on 2/8/17.
 */

public class CurrentUser {

    private static final String TAG = "LoginActivity-TAG";
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private DatabaseReference mDatabase;
    private static User currentUser;

    CurrentUser(User curUser) {
        currentUser = curUser;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void updateUser(User newUser) { currentUser = newUser; }

}