package com.example.musangano;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Meeting implements Parcelable {
    public String meetingId;
    public String agenda;
    public String venue;
    public String meetingDate;
    public String meetingTime;

    public Meeting( String meetingId, String agenda, String venue, String meetingDate, String meetingTime) {
        this.meetingId = meetingId;
        this.agenda = agenda;
        this.venue = venue;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
    }

    protected Meeting(Parcel in) {
        meetingId = in.readString();
        agenda = in.readString();
        venue = in.readString();
        meetingDate = in.readString();
        meetingTime = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(meetingId);
        dest.writeString(agenda);
        dest.writeString(venue);
        dest.writeString(meetingDate);
        dest.writeString(meetingTime);
    }

//    public String getMeetingId() {
//        return meetingId;
//    }
//
//    public void setMeetingId(String meetingId) {
//        this.meetingId = meetingId;
//    }
//
//    public String getAgenda() {
//        return agenda;
//    }
//
//    public void setAgenda(String agenda) {
//        this.agenda = agenda;
//    }
//
//    public String getVenue() {
//        return venue;
//    }
//
//    public void setVenue(String venue) {
//        this.venue = venue;
//    }
//
//    public String getMeetingDate() {
//        return meetingDate;
//    }
//
//    public void setMeetingDate(String meetingDate) {
//        this.meetingDate = meetingDate;
//    }
//
//    public String getMeetingTime() {
//        return meetingTime;
//    }
//
//    public void setMeetingTime(String meetingTime) {
//        this.meetingTime = meetingTime;
//    }
}


