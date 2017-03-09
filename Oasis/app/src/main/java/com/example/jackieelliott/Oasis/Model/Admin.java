package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Admin extends Manager {

    /**
     * This is a constructor
     * @param userName username of the admin
     * @param password password of the admin
     */
    public Admin(String userName, String password, int permission) {
        super(userName, password, permission);
    }


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    public Admin(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
     */
    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_username);
        dest.writeString(_password);
    }
    */

    public static final Parcelable.Creator<Admin> CREATOR
            = new Parcelable.Creator<Admin>() {
        public Admin createFromParcel(Parcel in) {
            return new Admin(in);
        }

        public Admin[] newArray(int size) {
            return new Admin[size];
        }
    };
}
