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

    public HistoryGraph(int year, double latitude, double longitude, String yAxis) {
        super();
        this.year = year;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yAxis = yAxis;
    }


    public int getYear() {
        return this.year;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    // We are fine with this get method because the
    // variable is yAxis


    public String getYAxis() {
        return this.yAxis;
    }


    public HistoryGraph(Parcel in) {
        super();
        this.yAxis = in.readString();
        this.year = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
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
