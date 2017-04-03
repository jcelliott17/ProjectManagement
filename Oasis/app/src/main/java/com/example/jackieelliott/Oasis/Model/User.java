package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class User implements Parcelable {

    /**
     * Attributes of the User class
     */
    private String _username;
    private String _password;
    //private Profile _profile;
    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;
    private int _permission;


    public User(String userName, String password, int permission) {
        _username = userName;
        _password = password;
        _accountType = "";
        _emailAddress = "";
        _homeAddress = "";
        _permission = permission;

    }

    /*
     * Getters and Setters
     */

    public final int getPermission() { return _permission; }

    public final void setPermission(int permission) { _permission = permission; }

    public final String getUsername() { return _username; }

    public final void setUsername(String userName) { _username = userName; }

    public final String getPassword() { return _password; }

    public final void setPassword(String password) { _password = password; }

    public final String getHomeAddress() { return _homeAddress; }

    public final void setHomeAddress(String homeAddress) { _homeAddress = homeAddress; }

    public final String getEmailAddress() { return _emailAddress; }

    public final void setEmailAddress(String emailAddress) { _emailAddress = emailAddress; }

    public final String getAccountType() { return _accountType; }

    public final void setAccountType(String accountType) { _accountType = accountType; }

    //public Profile getProfile() {
       // return _profile;
    //}


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    public User(Parcel in) {
        _username = in.readString();
        _password = in.readString();
        _homeAddress = in.readString();
        _emailAddress = in.readString();
        _accountType = in.readString();
        _permission = in.readInt();
        //_profile = in.readParcelable( Profile.class.getClassLoader());
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeString(_homeAddress);
        dest.writeString(_emailAddress);
        dest.writeString(_accountType);
        dest.writeInt(_permission);
        //dest.writeParcelable(_profile, flags);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
