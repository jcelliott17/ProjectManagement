package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Worker extends User{

    /**
     * Constructor for the worker class
     * @param userName Username for the worker
     * @param password Password for the worker
     */
    public Worker(String userName, String password) {
        super(userName, password);
    }


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    public Worker(Parcel in) {
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

    public static final Parcelable.Creator<Worker> CREATOR
            = new Parcelable.Creator<Worker>() {
        public Worker createFromParcel(Parcel in) {
            return new Worker(in);
        }

        public Worker[] newArray(int size) {
            return new Worker[size];
        }
    };
}
