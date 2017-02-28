package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JackieElliott on 2/20/17.
 */

public class Report implements Parcelable {

    private String _reportName;
    private String _location;
    private String _condition;
    private String _typeOfWater;
    private int _timeAndDate;

    public Report (String name, String location) {
        _reportName = name;
        _location = location;
    }
    public String getCondition() {
        return _condition;
    }

    public void setCondition(String condition) {
        _condition = condition;
    }

    public String getTypeOfWater() {
        return _typeOfWater;
    }

    public void setTypeOfWater(String typeOfWater) {
        _typeOfWater = typeOfWater;
    }

    public String getReportName() {
        return _reportName;
    }

    public int getTimeAndDate() {
        return _timeAndDate;
    }

    public void setTimeAndDate(int timeAndDate) {
        _timeAndDate = timeAndDate;
    }

    public Report(Parcel in) {
        _reportName = in.readString();
        _location = in.readString();
        _condition = in.readString();
        _typeOfWater = in.readString();
        _timeAndDate = in.readInt();
    }

    public String toString() {
        return "Name: " + _reportName + "Location: " + _location;
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
        dest.writeString(_reportName);
        dest.writeString(_location);
        dest.writeString(_condition);
        dest.writeString(_typeOfWater);
        dest.writeInt(_timeAndDate);
    }

    public static final Parcelable.Creator<Report> CREATOR
            = new Parcelable.Creator<Report>() {
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}
