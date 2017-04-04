package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class User { //implements Parcelable {

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

    public User() {
        this("default", "default", "default", 1);
    }

    //public User(String email, String password) {
    //    this(email, password, "default", 1);
    //}

    public User(String userName, String password, String userID, int permission) {
        _username = userName;
        _password = password;
        _accountType = "";
        _emailAddress = "";
        _homeAddress = "";
        _permission = permission;
        _userID = userID;
    }

    /*
     * Getters and Setters
     */

    public int getPermission() { return _permission; }

    public void setPermission(int permission) { _permission = permission; }

    public String getUsername() { return _username; }

    public void setUsername(String userName) { _username = userName; }

    public String getPassword() { return _password; }

    public void setPassword(String password) { _password = password; }

    public String getHomeAddress() { return _homeAddress; }

    public void setHomeAddress(String homeAddress) { _homeAddress = homeAddress; }

    public String getEmailAddress() { return _emailAddress; }

    public void setEmailAddress(String emailAddress) { _emailAddress = emailAddress; }

    public String getAccountType() { return _accountType; }

    public void setAccountType(String accountType) { _accountType = accountType; }

    public String getUserID() { return _userID; }

    public void setUserID(String userID) { _userID = userID; }

    //public Profile getProfile() {
       // return _profile;
    //}

    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    /*
    public User(Parcel in) {
        _username = in.readString();
        _password = in.readString();
        _homeAddress = in.readString();
        _emailAddress = in.readString();
        _accountType = in.readString();
        _permission = in.readInt();
        //_profile = in.readParcelable( Profile.class.getClassLoader());
    }

    /*
    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    /*
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeString(_homeAddress);
        dest.writeString(_emailAddress);
        dest.writeString(_accountType);
        dest.writeInt(_permission);
        //dest.writeParcelable(_profile, flags);
    }
    */

    /*
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };*/
}
