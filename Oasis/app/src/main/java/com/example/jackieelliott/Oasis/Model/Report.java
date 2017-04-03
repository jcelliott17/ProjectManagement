package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by JackieElliott on 2/20/17.
 */

@SuppressWarnings("ClassNamingConvention")
public class Report implements Parcelable {

    private final String _reportName;
    private double _latitude;
    private double _longitude;
    private String _condition;
    private String _typeOfWater;
    private int _timeAndDate;
    private int _reportNumber;

    /**
     * constructor
     * @param name name of report
     */
    public Report (String name) {
        super();
        this._reportName = name;
    }

    /**
     * constructor
     * @param latitude location of report
     * @param longitude location of report
     */
    public Report(double latitude, double longitude) {
        super();
        this._reportName = "newly added";
        this._latitude = latitude;
        this._longitude = longitude;
    }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get report number
//     * @return integer report number
//     */
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

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get water type
//     * @return String type of water
//     */
//    public String getTypeOfWater() {
//        return this._typeOfWater;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

    /**
     * set water type
     * @param typeOfWater String type of water
     */
    public void setTypeOfWater(String typeOfWater) {
        this._typeOfWater = typeOfWater;
    }

    /**
     * get report name
     * @return String report name
     */
    public String getReportName() {
        return this._reportName;
    }

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * set report name
//     * @param name String report name
//     */
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

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * get time and date
//     * @return int time and date
//     */
//    public int getTimeAndDate() {
//        return this._timeAndDate;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)

// --Commented out by Inspection START (4/2/17, 11:11 PM):
//    /**
//     * set time and date
//     * @param timeAndDate integer time and date
//     */
//    public void setTimeAndDate(int timeAndDate) {
//        this._timeAndDate = timeAndDate;
//    }
// --Commented out by Inspection STOP (4/2/17, 11:11 PM)


    /**
     * Constructor required for parcelable
     * @param in input
     */
    private Report(Parcel in) {
        super();
        this._reportName = in.readString();
        this._latitude = in.readDouble();
        this._longitude = in.readDouble();
        this._condition = in.readString();
        this._typeOfWater = in.readString();
        this._timeAndDate = in.readInt();
        this._reportNumber = in.readInt();
    }

    /**
     * output for report
     * @return String output
     */
    @Override
    public String toString() {
        return "Report Number: " + this._reportNumber + " Name: " + this._reportName + " Location: " + this._latitude + " " + this._longitude;
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
        dest.writeString(this._typeOfWater);
        dest.writeInt(this._timeAndDate);
        dest.writeInt(this._reportNumber);
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
