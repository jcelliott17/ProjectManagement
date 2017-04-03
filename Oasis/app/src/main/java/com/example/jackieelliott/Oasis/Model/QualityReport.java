package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JackieElliott on 2/20/17.
 */

public class QualityReport implements Parcelable {

    private String _reportName;
    private double _latitude;
    private double _longitude;
    private String _condition;
    private double _virus;
    private double _contaminant;
    private Date _timeAndDate;
    private int _reportNumber;

    /**
     * constructor
     * @param name
     */
    public QualityReport(String name) {
        _reportName = name;
    }

    /**
     * constructor
     * @param name
     * @param latitude
     * @param longitude
     */
    public QualityReport(String name, double latitude, double longitude) {
        _reportName = name;
        _latitude = latitude;
        _longitude = longitude;
    }

    /**
     * get report number
     * @return integer report number
     */
    public final int getReportNumber() { return _reportNumber; }

    /**
     * set report number
     * @param number integer report number
     */
    public final void setReportNumber(int number) { _reportNumber = number; }

    /**
     * get condition
     * @return String condition
     */
    public final String getCondition() {
        return _condition;
    }

    /**
     * set condition
     * @param condition String condition
     */
    public final void setCondition(String condition) {
        _condition = condition;
    }

    /**
     * get water type
     * @return String type of water
     */
    public final Double getVirus() {
        return _virus;
    }

    /**
     * set water type
     * @param virus String type of water
     */
    public final void setVirus(Double virus) {
        _virus = virus;
    }

    public final Double getContaminant() { return _contaminant; }

    public final void setContaminant(Double contaminant) { _contaminant = contaminant; }

    /**
     * get report name
     * @return String report name
     */
    public final String getReportName() {
        return _reportName;
    }

    /**
     * set report name
     * @param name String report name
     */
    public final void setReportName(String name) { _reportName = name; }

    /**
     * get latitude
     * @return double latitude
     */
    public final double getLatitude() { return _latitude; }

    /**
     * set latitude
     * @param latitude double latitude
     */
    public final void setLatitude(double latitude) { _latitude = latitude; }

    /**
     * get longitude
     * @return double longitude
     */
    public final double getLongitude() { return _longitude; }

    /**
     * set longitude
     * @param longitude double longitude
     */
    public final void setLongitude(double longitude) { _longitude = longitude; }

    /**
     * get time and date
     * @return int time and date
     */
    public final Date getTimeAndDate() {
        return _timeAndDate;
    }

    /**
     * set time and date
     * @param timeAndDate integer time and date
     */
    public final void setTimeAndDate(Date timeAndDate) {
        _timeAndDate = timeAndDate;
    }


    public QualityReport(Parcel in) {
        _reportName = in.readString();
        _latitude = in.readDouble();
        _longitude = in.readDouble();
        _condition = in.readString();
        _virus = in.readDouble();
        _contaminant = in.readDouble();
        _timeAndDate = new Date(in.readLong());
        _reportNumber = in.readInt();
    }

    /**
     * output for report
     * @return String output
     */
    @Override
    public final String toString() {
        return "Report Number: " + _reportNumber + " Name: " + _reportName + " Location: " + _latitude + " " + _longitude
                + " Virus PPM: " + _virus + " Date: " + _timeAndDate.toString();
    }

    /**
     * describes contents
     * @return
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /**
     * allows addition of new instance variables to Students
     * @param dest
     * @param flags
     */
    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_reportName);
        dest.writeDouble(_latitude);
        dest.writeDouble(_longitude);
        dest.writeString(_condition);
        dest.writeDouble(_virus);
        dest.writeDouble(_contaminant);
        dest.writeLong(_timeAndDate.getTime());
        dest.writeInt(_reportNumber);
    }

    public static final Creator<QualityReport> CREATOR
            = new Creator<QualityReport>() {
        public QualityReport createFromParcel(Parcel in) {
            return new QualityReport(in);
        }

        public QualityReport[] newArray(int size) {
            return new QualityReport[size];
        }
    };
}
