package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

@SuppressWarnings("ClassNamingConvention")
public class User implements Parcelable {

    /**
     * Attributes of the User class
     */
    private final String _username;
    private final String _password;
    //private Profile _profile;
    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;
    private final int _permission;


    /**
     * Constructor for User
     * @param userName username of user
     * @param password password of user
     * @param permission user's permission level
     */
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

    /**
     * Getter
     * @return permission level
     */
    public int getPermission() { return this._permission; }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * Setter
//     * @param permission permission
//     */
//    public void setPermission(int permission) { this._permission = permission; }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

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

    /**
     * Constructor for user required by parcelable
     * @param in input
     */
    private User(Parcel in) {
        super();
        this._username = in.readString();
        this._password = in.readString();
        this._homeAddress = in.readString();
        this._emailAddress = in.readString();
        this._accountType = in.readString();
        this._permission = in.readInt();
        //_profile = in.readParcelable( Profile.class.getClassLoader());
    }

    /**
     * Required by Parcelable
     * @return int
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */

    /**
     * Output method for parcelable
     * @param dest destination
     * @param flags flag
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._username);
        dest.writeString(this._password);
        dest.writeString(this._homeAddress);
        dest.writeString(this._emailAddress);
        dest.writeString(this._accountType);
        dest.writeInt(this._permission);
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
