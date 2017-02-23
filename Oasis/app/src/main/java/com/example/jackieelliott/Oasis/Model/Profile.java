package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Profile implements Parcelable {

    private String _homeAddress;
    private String _emailAddress;
    private String _accountType;

    public Profile () {

    }

    public String getHomeAddress() {
        return _homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        _homeAddress = homeAddress;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;
    }

    /* *********************************
  * These methods are required by the parcelable interface
  *
  */
    public Profile(Parcel in) {
        _homeAddress = in.readString();
        _emailAddress = in.readString();
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
