package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alon on 2/16/17.
 */

public class Manager implements Parcelable{

    private String _username;
    private String _password;
    //private Profile _profile;

    /*
     * Getters and Setters
     */
    public String getUsername() { return _username; }

    public void setUsername(String userName) { _username = userName; }

    public String getPassword() { return _password; }

    public void setPassword(String password) { _password = password; }

    public Manager(String userName, String password) {
        _username = userName;
        _password = password;
    }


    /* *********************************
   * These methods are required by the parcelable interface
   *
   */
    private Manager(Parcel in) {
        _username = in.readString();
        _password = in.readString();
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
        dest.writeString(_username);
        dest.writeString(_password);
    }

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