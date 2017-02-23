package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Manager extends Worker {

    /**
     *
     * @param userName
     * @param password
     */
    public Manager(String userName, String password) {
        super(userName, password);
    }


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    public Manager(Parcel in) {
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

    public static final Parcelable.Creator<Manager> CREATOR
            = new Parcelable.Creator<Manager>() {
        public Manager createFromParcel(Parcel in) {
            return new Manager(in);
        }

        public Manager[] newArray(int size) {
            return new Manager[size];
        }
    };
}
