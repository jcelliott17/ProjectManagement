package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

@SuppressWarnings("ClassNamingConvention")
public class User {

    /**
     * Attributes of the User class
     */
    private final String _username;
    private final String _password;
    //private Profile _profile;
    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;
    private String _userID;
    private int _permission;

    public User() {
        this("default", "default", "default", 1);
    }

    /**
     * Constructor for User
     * @param userName username of user
     * @param password password of user
     * @param permission user's permission level
     */
    public User(String userName, String password, String userID, int permission) {
        super();
        this._username = userName;
        this._password = password;
        this._permission = permission;
        this._userID = userID;
        this._accountType = "";
        this._emailAddress = "";
        this._homeAddress = "";
    }

    /*
     * Getters and Setters
     */

    /**
     * Getter
     * @return permission level
     */
    public int getPermission() { return this._permission; }

    /**
     * Setter
     * @param permission permission
     */
    public void setPermission(int permission) { this._permission = permission; }

    /**
     * Getter
     * @return username
     */
    public String getUsername() { return this._username; }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * Setter
//     * @param userName username
//     */
//    public void setUsername(String userName) { this._username = userName; }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

    /**
     * Getter
     * @return password
     */
    public String getPassword() { return this._password; }

// --Commented out by Inspection START (4/2/17, 11:12 PM):
//    /**
//     * Setter
//     * @param password password
//     */
//    public void setPassword(String password) { this._password = password; }
// --Commented out by Inspection STOP (4/2/17, 11:12 PM)

    /**
     * Getter
     * @return homeAddress
     */
    public String getHomeAddress() { return this._homeAddress; }

    /**
     * Setter
     * @param homeAddress homeAddress
     */
    public void setHomeAddress(String homeAddress) { this._homeAddress = homeAddress; }

    /**
     * Getter
     * @return emailAddress
     */
    public String getEmailAddress() { return this._emailAddress; }

    /**
     * Setter
     * @param emailAddress emailAddress
     */
    public void setEmailAddress(String emailAddress) { this._emailAddress = emailAddress; }

    public String getUserID() { return _userID; }

    public void setUserID(String userID) { _userID = userID; }


    /**
     * Getter
     * @return accountType
     */
    public String getAccountType() { return this._accountType; }

    /**
     * Setter
     * @param accountType new account type
     */
    public void setAccountType(String accountType) { this._accountType = accountType; }

}
