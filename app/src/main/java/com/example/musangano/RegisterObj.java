package com.example.musangano;

import android.os.Parcel;
import android.os.Parcelable;

public class RegisterObj implements Parcelable{
    public String meetingId;
    public String employeeId;
    public String date;
    public String firstName;
    public String surname;
    public String post;
    public String department;

    public RegisterObj(String meetingId, String employeeId, String date, String firstName, String surname, String post, String department) {
        this.meetingId = meetingId;
        this.employeeId = employeeId;
        this.date = date;
        this.firstName = firstName;
        this.surname = surname;
        this.post = post;
        this.department = department;
    }

    protected RegisterObj(Parcel in) {
        meetingId = in.readString();
        employeeId = in.readString();
        date = in.readString();
        firstName = in.readString();
        surname = in.readString();
        post = in.readString();
        department = in.readString();
    }

    public static final Creator<RegisterObj> CREATOR = new Creator<RegisterObj>() {
        @Override
        public RegisterObj createFromParcel(Parcel in) {
            return new RegisterObj(in);
        }

        @Override
        public RegisterObj[] newArray(int size) {
            return new RegisterObj[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(meetingId);
        dest.writeString(employeeId);
        dest.writeString(date);
        dest.writeString(firstName);
        dest.writeString(surname);
        dest.writeString(post);
        dest.writeString(department);
    }
}


