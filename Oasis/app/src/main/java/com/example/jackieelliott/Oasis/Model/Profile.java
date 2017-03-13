package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Profile implements Parcelable {

    /*
    We are currently not using this class
     */

    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;

    /**
     * constructor
     */
    public Profile () {

    }

    /**
     * gets address
     * @return String home address
     */
    public String getHomeAddress() {
        return _homeAddress;
    }

    /**
     * sets address
     * @param homeAddress String home address
     */
    public void setHomeAddress(String homeAddress) {
        _homeAddress = homeAddress;
    }

    /**
     * gets email address
     * @return String email address
     */
    public String getEmailAddress() {
        return _emailAddress;
    }

    /**
     * sets email address
     * @param emailAddress String email Address
     */
    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }

    /**
     * constructor for parcelable interface
     * @param in Parcel
     */
    public Profile(Parcel in) {
        _homeAddress = in.readString();
        _emailAddress = in.readString();
    }

    /**
     * describes contents
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * allows addition of new instance variables to Student
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_homeAddress);
        dest.writeString(_emailAddress);
    }

    public static final Parcelable.Creator<Profile> CREATOR
            = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
