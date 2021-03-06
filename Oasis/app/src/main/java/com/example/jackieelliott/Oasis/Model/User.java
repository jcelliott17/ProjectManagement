package com.example.jackieelliott.Oasis.Model;

import java.util.List;

/**
 * User class
 */

@SuppressWarnings("ClassNamingConvention")

public class User {

    /**
     * Attributes of the User class
     */
    private String _username;
    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;
    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private String _password;
    private String _userID;
    private int _permission;

    /**
     * Default constructor required to store custom
     * Java objects in FireBase
     */
    public User() {
        this("default", "default", "default");
    }

    /**
     * Constructor for User
     * @param userID id of the user
     * @param userName username of user
     * @param password password of user
     */
    public User(String userName, String password, String userID) {
        super();
        this._username = userName;
        this._password = password;
        this._permission = 1;
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

    /**
     * Getter
     * @return homeAddress
     */
    public CharSequence getHomeAddress() { return this._homeAddress; }

    /**
     * Setter
     * @param homeAddress homeAddress
     */
    public void setHomeAddress(String homeAddress) { this._homeAddress = homeAddress; }

    /**
     * Getter
     * @return emailAddress
     */
    public CharSequence getEmailAddress() { return this._emailAddress; }

    /**
     * Setter
     * @param emailAddress emailAddress
     */
    public void setEmailAddress(String emailAddress) { this._emailAddress = emailAddress; }

    /**
     * Getter
     * @return user ID
     */
    public String getUserID() { return _userID; }


    /**
     * Getter
     * @return accountType
     */
    public CharSequence getAccountType() { return this._accountType; }

    /**
     * Setter
     * @param accountType new account type
     */
    public void setAccountType(String accountType) { this._accountType = accountType; }

    /**
     * Returns a list of the userNames from array of Users
     * @param userList List of Users
     * @return returnList
     */
    public static String[] findUserNames(List<User> userList) {
        String[] returnList = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            returnList[i] = u.getUsername();
        }
        return returnList;
    }

    // Firebase's necessary setters
    public void setUsername(String username) { _username = username; }
    public void setPassword(String password) {_password = password; }
    public void setUserID (String uID) { _userID = uID; }
    
}
