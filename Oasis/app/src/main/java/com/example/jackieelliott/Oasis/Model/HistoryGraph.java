package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JackieElliott on 3/28/17.
 */

//Overriding the toString() method
//we do not want to override the toString method in this class

public class HistoryGraph implements Parcelable {

    private int year;
    private double latitude;
    private double longitude;
    private String yAxis;

    public HistoryGraph(int year, double latitude, double longitude, String yAxis) {
        this.year = year;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yAxis = yAxis;
    }

    public final int getYear() {
        return year;
    }

    public final double getLatitude() {
        return latitude;
    }

    public final double getLongitude() {
        return longitude;
    }

    // We are fine with this get method because the
    // variable is yAxis

    public final String getYAxis() {
        return yAxis;
    }


    public HistoryGraph(Parcel in) {
        yAxis = in.readString();
        year = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
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
     * allows addition of new instance variables to Student
     * @param dest
     * @param flags
     */
    //@Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeString(yAxis);
        dest.writeInt(year);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
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
