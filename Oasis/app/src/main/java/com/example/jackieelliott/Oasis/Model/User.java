package com.example.jackieelliott.Oasis.Model;

@SuppressWarnings("ClassNamingConvention")
public class User {

    /**
     * Attributes of the User class
     */
    private String _username;
    private String _password;
    //private Profile _profile;
    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;
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
    public CharSequence getUsername() { return this._username; }

    /**
     * Setter
     * @param userName username
     */
    public void setUsername(String userName) { this._username = userName; }

    /**
     * Getter
     * @return password
     */
    public String getPassword() { return this._password; }

    /**
     * Setter
     * @param password password
     */
    public void setPassword(String password) { this._password = password; }

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

    public String getUserID() { return _userID; }

    public void setUserID(String userID) { _userID = userID; }


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

}
