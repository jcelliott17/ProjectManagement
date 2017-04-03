package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

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
        super();
        this._reportName = name;
    }

    /**
     * constructor
     * @param name
     * @param latitude
     * @param longitude
     */
    public QualityReport(String name, double latitude, double longitude) {
        super();
        this._reportName = name;
        this._latitude = latitude;
        this._longitude = longitude;
    }

    /**
     * get report number
     * @return integer report number
     */
    public int getReportNumber() { return this._reportNumber; }

    /**
     * set report number
     * @param number integer report number
     */
    public void setReportNumber(int number) { this._reportNumber = number; }

    /**
     * get condition
     * @return String condition
     */
    public String getCondition() {
        return this._condition;
    }

    /**
     * set condition
     * @param condition String condition
     */
    public void setCondition(String condition) {
        this._condition = condition;
    }

    /**
     * get water type
     * @return String type of water
     */
    public Double getVirus() {
        return this._virus;
    }

    /**
     * set water type
     * @param virus String type of water
     */
    public void setVirus(Double virus) {
        this._virus = virus;
    }

    public Double getContaminant() { return this._contaminant; }

    public void setContanminant(Double contanminant) { this._contaminant = contanminant; }

    /**
     * get report name
     * @return String report name
     */
    public String getReportName() {
        return this._reportName;
    }

    /**
     * set report name
     * @param name String report name
     */
    public void setReportName(String name) { this._reportName = name; }

    /**
     * get latitude
     * @return double latitude
     */
    public double getLatitude () { return this._latitude; }

    /**
     * set latitude
     * @param latitude double latitude
     */
    public void setLatitude(double latitude) { this._latitude = latitude; }

    /**
     * get longitude
     * @return double longitude
     */
    public double getLongitude () { return this._longitude; }

    /**
     * set longitude
     * @param longitude double longitude
     */
    public void setLongitude(double longitude) { this._longitude = longitude; }

    /**
     * get time and date
     * @return int time and date
     */
    public Date getTimeAndDate() {
        return this._timeAndDate;
    }

    /**
     * set time and date
     * @param timeAndDate integer time and date
     */
    public void setTimeAndDate(Date timeAndDate) {
        this._timeAndDate = timeAndDate;
    }


    public QualityReport(Parcel in) {
        super();
        this._reportName = in.readString();
        this._latitude = in.readDouble();
        this._longitude = in.readDouble();
        this._condition = in.readString();
        this._virus = in.readDouble();
        this._contaminant = in.readDouble();
        this._timeAndDate = new Date(in.readLong());
        this._reportNumber = in.readInt();
    }

    /**
     * output for report
     * @return String output
     */
    @Override
    public String toString() {
        return "Report Number: " + this._reportNumber + " Name: " + this._reportName + " Location: " + this._latitude + " " + this._longitude
                + " Virus PPM: " + this._virus + " Date: " + this._timeAndDate.toString();
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
        dest.writeString(this._reportName);
        dest.writeDouble(this._latitude);
        dest.writeDouble(this._longitude);
        dest.writeString(this._condition);
        dest.writeDouble(this._virus);
        dest.writeDouble(this._contaminant);
        dest.writeLong(this._timeAndDate.getTime());
        dest.writeInt(this._reportNumber);
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
