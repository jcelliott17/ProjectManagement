package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jackieelliott.Oasis.controllers.GraphDisplayActivity;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by JackieElliott on 2/20/17.
 */

public class QualityReport implements Parcelable {

    private final String _reportName;
    private double _latitude;
    private double _longitude;
    private String _condition;
    private double _virus;
    private double _contaminant;
    private Date _timeAndDate;
    private int _reportNumber;

    /**
     * constructor
     * @param name name of report
     */
    public QualityReport(String name) {
        super();
        this._reportName = name;
    }


    public QualityReport(String name, double latitude, double longitude) {
        super();
        this._reportName = name;
        this._latitude = latitude;
        this._longitude = longitude;
    }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get report number
//     * @return integer report number
//     */
//
//    public int getReportNumber() { return this._reportNumber; }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

    /**
     * set report number
     * @param number integer report number
     */

    public void setReportNumber(int number) { this._reportNumber = number; }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get condition
//     * @return String condition
//     */
//    public String getCondition() {
//        return this._condition;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

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

    /**
     * Getter for contaminant
     * @return contaminant level
     */
    public Double getContaminant() { return this._contaminant; }

    /**
     * Setter for contaminant
     * @param contaminant contaminant level
     */
    //KEEP THIS VERSION - Jackie
    public void setContaminant(Double contaminant) { this._contaminant = contaminant; }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get report name
//     * @return String report name
//     */
//    public String getReportName() {
//        return this._reportName;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * set report name
//     * @param name String report name
//     */
//
//    public void setReportName(String name) { this._reportName = name; }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

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


    /**
     * constructor required for parcelable
     * @param in input
     */
    private QualityReport(Parcel in) {
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
        return "Report Number: " + this._reportNumber + " Name: " + this._reportName + " Location: "
                + this._latitude + " " + this._longitude
                + " Virus PPM: " + this._virus + " Date: " + this._timeAndDate.toString();
    }

    /**
     * describes contents
     * @return a num
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /**
     * allows addition of new instance variables to Students
     * @param dest destination
     * @param flags flag
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
        @Override
        public QualityReport createFromParcel(Parcel in) {
            return new QualityReport(in);
        }

        @Override
        public QualityReport[] newArray(int size) {
            return new QualityReport[size];
        }
    };

    /**
     * Gets the average data values for a given year and plots the points on the graph
     **/
    //dataType is virus or contaminant
    public int getAverage(int[] contaminants, int[] virus, String dataType) {
        double average = 0;
        int length;
        if (virus.length > contaminants.length) {
            length = virus.length;
        } else {
            length = contaminants.length;
        }
        for (int i = 0; i < length; i++) {
            if ("Virus".equals(dataType)) {
                average += virus[i];
            } else {
                average += contaminants[i];
            }
        }
        average = average / length;
        return (int) average;
    }
    //Returns a list of quality reports in a given year
    //Use deprecated Date code because android wouldn't support localDateTime
    /**
     * Gets the reports from a specified year and sorts them by month
     *
     *
     * @param year specified year for reports
     * @param qualityList list of reports
     * @return An array of linked lists
     */


     @SuppressWarnings("MagicNumber")
     public static LinkedList<QualityReport>[] sortReports(int year, double latitude, double longitude,
                                                           Iterable<QualityReport> qualityList) {
        LinkedList<QualityReport>[] monthlyQualityList = (LinkedList<QualityReport>[]) new LinkedList[12];
        if (qualityList == null) {
            return monthlyQualityList;
        }
        for (QualityReport report: qualityList) {
            Date timeAndDate = report.getTimeAndDate();
            if (timeAndDate.getYear() == (year - 2000 + 100) && report.getLatitude() == latitude && report.getLongitude() == longitude) {
                if (monthlyQualityList[timeAndDate.getMonth()] == null) {
                    monthlyQualityList[timeAndDate.getMonth()] = new LinkedList<>();
                }
                monthlyQualityList[timeAndDate.getMonth()].add(report);
            }
        }
        return monthlyQualityList;

    }
}
