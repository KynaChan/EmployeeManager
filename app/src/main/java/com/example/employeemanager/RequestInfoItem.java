package com.example.employeemanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class RequestInfoItem implements Parcelable, Serializable {
    String requestType, status, dateFrom, dateTo, timeFrom, timeTo, createTime, createUser;

    public RequestInfoItem(String requestType,String status, String dateFrom, String dateTo, String timeFrom, String timeTo, String createTime, String createUser){
        this.requestType = requestType;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.createTime = createTime;
        this.createUser = createUser;
    }

    protected RequestInfoItem(Parcel in) {
        requestType = in.readString();
        status = in.readString();
        dateFrom = in.readString();
        dateTo = in.readString();
        timeFrom = in.readString();
        timeTo = in.readString();
        createTime = in.readString();
        createUser = in.readString();
    }

    public static final Creator<RequestInfoItem> CREATOR = new Creator<RequestInfoItem>() {
        @Override
        public RequestInfoItem createFromParcel(Parcel in) {
            return new RequestInfoItem(in);
        }

        @Override
        public RequestInfoItem[] newArray(int size) {
            return new RequestInfoItem[size];
        }
    };

    public void updateRequest(String newStatus){
        this.status = newStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(requestType);
        parcel.writeString(status);
        parcel.writeString(dateFrom);
        parcel.writeString(dateTo);
        parcel.writeString(timeFrom);
        parcel.writeString(timeTo);
        parcel.writeString(createTime);
        parcel.writeString(createUser);
    }
}