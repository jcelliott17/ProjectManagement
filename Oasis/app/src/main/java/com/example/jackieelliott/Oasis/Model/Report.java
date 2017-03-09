package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JackieElliott on 2/20/17.
 */

public class Report implements Parcelable {

    private String _reportName;
    private double _latitude;
    private double _longitude;
    private String _condition;
    private String _typeOfWater;
    private int _timeAndDate;
    private int _reportNumber;

    public Report (String name) {
        _reportName = name;
    }

    public Report(String name, double latitude, double longitude) {
        _reportName = name;
        _latitude = latitude;
        _longitude = longitude;
    }

    public int getReportNumber() { return _reportNumber; }

    public void setReportNumber(int number) { _reportNumber = number; }

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

    public void setReportName(String name) { _reportName = name; }

    public double getLatitude () { return _latitude; }

    public void setLatitude(double latitude) { _latitude = latitude; }

    public double getLongitude () { return _longitude; }

    public void setLongitude(double longitude) { _longitude = longitude; }

    public int getTimeAndDate() {
        return _timeAndDate;
    }

    public void setTimeAndDate(int timeAndDate) {
        _timeAndDate = timeAndDate;
    }

    public Report(Parcel in) {
        _reportName = in.readString();
        _latitude = in.readDouble();
        _longitude = in.readDouble();
        _condition = in.readString();
        _typeOfWater = in.readString();
        _timeAndDate = in.readInt();
        _reportNumber = in.readInt();
    }

    @Override
    public String toString() {
        return "Report Number: " + _reportNumber + " Name: " + _reportName + " Location: " + _latitude + " " + _longitude;
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
        dest.writeDouble(_latitude);
        dest.writeDouble(_longitude);
        dest.writeString(_condition);
        dest.writeString(_typeOfWater);
        dest.writeInt(_timeAndDate);
        dest.writeInt(_reportNumber);
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
