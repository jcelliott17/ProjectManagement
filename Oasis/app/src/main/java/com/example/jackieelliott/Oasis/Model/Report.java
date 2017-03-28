package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * constructor
     * @param name
     */
    public Report (String name) {
        _reportName = name;
    }

    /**
     * constructor
     * @param name
     * @param latitude
     * @param longitude
     */
    public Report(String name, double latitude, double longitude) {
        _reportName = name;
        _latitude = latitude;
        _longitude = longitude;
    }

    /**
     * get report number
     * @return integer report number
     */
    public int getReportNumber() { return _reportNumber; }

    /**
     * set report number
     * @param number integer report number
     */
    public void setReportNumber(int number) { _reportNumber = number; }

    /**
     * get condition
     * @return String condition
     */
    public String getCondition() {
        return _condition;
    }

    /**
     * set condition
     * @param condition String condition
     */
    public void setCondition(String condition) {
        _condition = condition;
    }

    /**
     * get water type
     * @return String type of water
     */
    public String getTypeOfWater() {
        return _typeOfWater;
    }

    /**
     * set water type
     * @param typeOfWater String type of water
     */
    public void setTypeOfWater(String typeOfWater) {
        _typeOfWater = typeOfWater;
    }

    /**
     * get report name
     * @return String report name
     */
    public String getReportName() {
        return _reportName;
    }

    /**
     * set report name
     * @param name String report name
     */
    public void setReportName(String name) { _reportName = name; }

    /**
     * get latitude
     * @return double latitude
     */
    public double getLatitude () { return _latitude; }

    /**
     * set latitude
     * @param latitude double latitude
     */
    public void setLatitude(double latitude) { _latitude = latitude; }

    /**
     * get longitude
     * @return double longitude
     */
    public double getLongitude () { return _longitude; }

    /**
     * set longitude
     * @param longitude double longitude
     */
    public void setLongitude(double longitude) { _longitude = longitude; }

    /**
     * get time and date
     * @return int time and date
     */
    public int getTimeAndDate() {
        return _timeAndDate;
    }

    /**
     * set time and date
     * @param timeAndDate integer time and date
     */
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

    /**
     * output for report
     * @return String output
     */
    @Override
    public String toString() {
        return "Report Number: " + _reportNumber + " Name: " + _reportName + " Location: " + _latitude + " " + _longitude;
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
     * allows addition of new instance variables to Students
     * @param dest
     * @param flags
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
