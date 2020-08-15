package com.example.musangano;

import android.provider.BaseColumns;

public final class MeetingsDatabaseContract {
    private MeetingsDatabaseContract(){}

    public static final class MeetingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "meetings_entry";
        public static final String COLUMN_MEETING_ID = "meeting_id";
        public static final String COLUMN_AGENDA = "agenda";
        public static final String COLUMN_VENUE = "venue";
        public static final String COLUMN_START_TIME = "start_time";
        public static final String COLUMN_DATE = "date";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " +  TABLE_NAME + " (" +
                COLUMN_MEETING_ID + " " +
                COLUMN_AGENDA + " TEXT NOT NULL, " +
                COLUMN_AGENDA + " TEXT NOT NULL, " +
                COLUMN_VENUE + " TEXT NOT NULL, " +
                COLUMN_DATE + " TEXT NOT NULL, " +
                COLUMN_START_TIME + " TEXT NOT NULL)";
    }

    public static final class EmployeesEntry implements BaseColumns{
        public static final String TABLE_NAME = "employees_entry";
        public static final String COLUMN_EMPLOYEE_ID = "employee_id";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_POST = "post";
        public static final String COLUMN_DEPARTMENT = "department";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" +
                _ID + "INTEGER PRIMARY KEY" +
                COLUMN_EMPLOYEE_ID + "TEXT UNIQUE NOT NULL, " +
                COLUMN_FIRST_NAME + "TEXT NOT NULL, "+
                COLUMN_SURNAME + "TEXT NOT NULL, " +
                COLUMN_POST + "TEXT NOT NULL, " +
                COLUMN_DEPARTMENT + "TEXT NOT NULL)";


    }

    public static final class RegisterEntry implements BaseColumns{
        public static final String TABLE_NAME = "register_entry";
        public static final String COLUMN_MEETING_ID = "meeting_id";
        public static final String COLUMN_EMPLOYEE_ID = "employee_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_POST = "post";
        public static final String COLUMN_DEPARTMENT = "department";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_MEETING_ID + " TEXT NOT NULL, " +
                COLUMN_EMPLOYEE_ID + " TEXT NOT NULL, " +
                COLUMN_DATE + " TEXT NOT NULL, " +
                COLUMN_FIRST_NAME + " TEXT NOT NULL, "+
                COLUMN_SURNAME + " TEXT NOT NULL, " +
                COLUMN_POST + " TEXT NOT NULL, " +
                COLUMN_DEPARTMENT + " TEXT NOT NULL)";

    }
}
