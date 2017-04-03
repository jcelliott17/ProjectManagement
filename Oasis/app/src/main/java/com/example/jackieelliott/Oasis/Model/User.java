package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

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
        super();
        this._username = userName;
        this._password = password;
        this._accountType = "";
        this._emailAddress = "";
        this._homeAddress = "";
        this._permission = permission;

    }

    /*
     * Getters and Setters
     */

    public int getPermission() { return this._permission; }

    public void setPermission(int permission) { this._permission = permission; }

    public String getUsername() { return this._username; }

    public void setUsername(String userName) { this._username = userName; }

    public String getPassword() { return this._password; }

    public void setPassword(String password) { this._password = password; }

    public String getHomeAddress() { return this._homeAddress; }

    public void setHomeAddress(String homeAddress) { this._homeAddress = homeAddress; }

    public String getEmailAddress() { return this._emailAddress; }

    public void setEmailAddress(String emailAddress) { this._emailAddress = emailAddress; }

    public String getAccountType() { return this._accountType; }

    public void setAccountType(String accountType) { this._accountType = accountType; }

    //public Profile getProfile() {
       // return _profile;
    //}


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    public User(Parcel in) {
        super();
        this._username = in.readString();
        this._password = in.readString();
        this._homeAddress = in.readString();
        this._emailAddress = in.readString();
        this._accountType = in.readString();
        this._permission = in.readInt();
        //_profile = in.readParcelable( Profile.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._username);
        dest.writeString(this._password);
        dest.writeString(this._homeAddress);
        dest.writeString(this._emailAddress);
        dest.writeString(this._accountType);
        dest.writeInt(this._permission);
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
