package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JackieElliott on 3/28/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class HistoryGraph implements Parcelable {

    private final int year;
    private final double latitude;
    private final double longitude;
    private final String yAxis;

    /**
     * Constructor for a History Graph
     * @param year year of graph
     * @param latitude location info
     * @param longitude location info
     * @param yAxis label
     */
    public HistoryGraph(int year, double latitude, double longitude, String yAxis) {
        super();
        this.year = year;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yAxis = yAxis;
    }


    /**
     * Getter for year
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Getter for latitude
     * @return latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Getter for longitude
     * @return longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    // We are fine with this get method because the
    // variable is yAxis

    /**
     * Getter for yAxis
     * @return yAxis
     */
    public String getYAxis() {
        return this.yAxis;
    }

    /**
     * Method required by parcelable
     * @param in parable requirement
     */
    public HistoryGraph(Parcel in) {
        super();
        this.yAxis = in.readString();
        this.year = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
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
     * allows addition of new instance variables to Student
     * @param dest destination
     * @param flags required parcelable interface
     */
    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.yAxis);
        dest.writeInt(this.year);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public static final Parcelable.Creator<HistoryGraph> CREATOR
            = new Parcelable.Creator<HistoryGraph>() {
        public HistoryGraph createFromParcel(Parcel in) {
            return new HistoryGraph(in);
        }

        public HistoryGraph[] newArray(int size) {
            return new HistoryGraph[size];
        }
    };
}
