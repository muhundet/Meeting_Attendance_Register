package com.example.musangano;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MeetingsDataManager {
    private static MeetingsDataManager ourInstance = null;
    ArrayList<Meeting> meetings = new ArrayList<>();
    ArrayList<Meeting> prevMeetings = new ArrayList<>();
    ArrayList<RegisterObj> registerObjs = new ArrayList<>();

    public static MeetingsDataManager getInstance(){
        if(ourInstance == null){
            ourInstance = new MeetingsDataManager();
        }
        return ourInstance;
    }

    public ArrayList<Meeting> getMeetings(){
        return meetings;
    }

    public ArrayList<Meeting> getprevMeetings(){
        return prevMeetings;
    }
    public ArrayList<RegisterObj> getRegisterObjs(){
        return registerObjs;
    }

    private void  populateMeetings() {
//        meetings = new ArrayList<>();
        Meeting meeting1 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");
        Meeting meeting2 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");
        Meeting meeting3 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");
        Meeting meeting4 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");
        Meeting meeting5 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");
        Meeting meeting6 = new Meeting( "m001","Purchase of computers", "Admin01", "01/01/20", "1000hrs");

        meetings.add(meeting1);
        meetings.add(meeting2);
        meetings.add(meeting3);
        meetings.add(meeting4);


    }

    public static void readMeetings(MeetingsOpenHelper dbOpenHelper){
        Date d = new Date();

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        final String [] meetingColumns = {
                MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID,
                MeetingsDatabaseContract.MeetingsEntry.COLUMN_AGENDA,
                MeetingsDatabaseContract.MeetingsEntry.COLUMN_VENUE,
                MeetingsDatabaseContract.MeetingsEntry.COLUMN_DATE,
                MeetingsDatabaseContract.MeetingsEntry.COLUMN_START_TIME};

        final Cursor meetingsCursor = db.query(MeetingsDatabaseContract.MeetingsEntry.TABLE_NAME,
                meetingColumns,
                null, null, null, null, null);

        loadMeetingsFromDatabase(meetingsCursor);

    }

    private static void loadMeetingsFromDatabase(Cursor cursor) {
        int meetingIdPos = cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID);
        int meetingAgendaPos = cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_AGENDA);
        int meetingVenuePos = cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_VENUE);
        int meetingDatePos = cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_DATE);
        int meetingTimePos = cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_START_TIME);

        MeetingsDataManager m = getInstance();
        m.meetings.clear();
        m.prevMeetings.clear();
//        LocalDate localMeetingDate = null;
        Meeting meeting = null;
        Meeting prevMeeting = null;

        while(cursor.moveToNext()) {
            String meetingId = cursor.getString(meetingIdPos);
            String meetingAgenda = cursor.getString(meetingAgendaPos);
            String meetingVenue = cursor.getString(meetingVenuePos);
            String meetingDate = cursor.getString(meetingDatePos);
            String meetingTime = cursor.getString(meetingTimePos);

            String stringDay = meetingDate.substring(0, 2);
            String stringMonth = meetingDate.substring(3, 5);
            String stringYear = meetingDate.substring(6);

            int day = Integer.parseInt(stringDay);
            int month = Integer.parseInt(stringMonth);
            month = month -1;
            int year = Integer.parseInt(stringYear);



            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            Date d = c.getTime();


            if (d.after(new Date())) {
                meeting = new Meeting(meetingId, meetingAgenda, meetingVenue, meetingDate, meetingTime);
                m.meetings.add(meeting);
            } else {
                prevMeeting = new Meeting(meetingId, meetingAgenda, meetingVenue, meetingDate, meetingTime);
                m.prevMeetings.add(prevMeeting);
            }


//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                localMeetingDate = LocalDate.of(year, month, day);
//            }

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if(localMeetingDate.isAfter(LocalDate.now()))
//                meeting = new Meeting(meetingId, meetingAgenda, meetingVenue, meetingDate, meetingTime);
//                m.meetings.add(meeting);
//            }

        }
        cursor.close();

    }


}
