package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class


@SuppressWarnings("ClassNamingConvention")
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
        super();
    }

    /**
     * gets address
     * @return String home address
     */
    public String getHomeAddress() {
        return this._homeAddress;
    }

    /**
     * sets address
     * @param homeAddress String home address
     */
    public void setHomeAddress(String homeAddress) {
        this._homeAddress = homeAddress;
    }

    /**
     * gets email address
     * @return String email address
     */

    public String getEmailAddress() {
        return this._emailAddress;
    }

    /**
     * sets email address
     * @param emailAddress String email Address
     */
    public void setEmailAddress(String emailAddress) {
        this._emailAddress = emailAddress;
    }

    /**
     * constructor for parcelable interface
     * @param in Parcel
     */
    public Profile(Parcel in) {
        super();
        this._homeAddress = in.readString();
        this._emailAddress = in.readString();
    }

    /**
     * describes contents
     * @return a num for parcelable
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /**
     * allows addition of new instance variables to Student
     * @param dest destination
     * @param flags flag
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._homeAddress);
        dest.writeString(this._emailAddress);
    }

    public static final Parcelable.Creator<Profile> CREATOR
            = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
