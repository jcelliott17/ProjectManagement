package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

//Overriding the toString() method
//we do not want to override the toString method in this class


@SuppressWarnings("ClassNamingConvention")
public final class Profile implements Parcelable {

    /*
    We are currently not using this class
     */

    private final String _homeAddress;
    private final String _emailAddress;
    // --Commented out by Inspection (4/2/17, 11:10 PM):private String _accountType;

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * constructor
//     */
//    public Profile () {
//        super();
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * gets address
//     * @return String home address
//     */
//    public String getHomeAddress() {
//        return this._homeAddress;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * sets address
//     * @param homeAddress String home address
//     */
//    public void setHomeAddress(String homeAddress) {
//        this._homeAddress = homeAddress;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * gets email address
//     * @return String email address
//     */
//
//    public String getEmailAddress() {
//        return this._emailAddress;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * sets email address
//     * @param emailAddress String email Address
//     */
//    public void setEmailAddress(String emailAddress) {
//        this._emailAddress = emailAddress;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

    /**
     * constructor for parcelable interface
     * @param in Parcel
     */
    private Profile(Parcel in) {
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
