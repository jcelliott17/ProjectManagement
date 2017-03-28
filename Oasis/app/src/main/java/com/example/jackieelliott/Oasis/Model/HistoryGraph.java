package com.example.jackieelliott.Oasis.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JackieElliott on 3/28/17.
 */

public class HistoryGraph implements Parcelable {

    private int year;
    private int latitude;
    private int longitude;
    private String yAxis;

    public HistoryGraph(int year, int latitude, int longitude, String yAxis) {
        this.year = year;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yAxis = yAxis;
    }

    public int getYear() {
        return year;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public String getyAxis() {
        return yAxis;
    }


    public HistoryGraph(Parcel in) {
        year = in.readInt();
        yAxis = in.readString();
        latitude = in.readInt();
        longitude = in.readInt();
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
     * allows addition of new instance variables to Student
     * @param dest
     * @param flags
     */
    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(yAxis);
        dest.writeInt(year);
        dest.writeInt(latitude);
        dest.writeInt(longitude);
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
