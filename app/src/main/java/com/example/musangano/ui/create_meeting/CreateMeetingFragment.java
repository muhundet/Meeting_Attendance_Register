package com.example.musangano.ui.create_meeting;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.musangano.DatePickerFragment;
import com.example.musangano.MainActivity;
import com.example.musangano.MeetingsOpenHelper;
import com.example.musangano.MeetingsDatabaseContract;
import com.example.musangano.R;
import com.example.musangano.TimePickerFragment;
//import com.example.musangano.TimePickerFragment;

import java.text.BreakIterator;
import java.util.Calendar;

import static com.example.musangano.TimePickerFragment.textTime;

public class CreateMeetingFragment extends Fragment {

    private CreateMeetingViewModel createMeetingViewModel;
    private SQLiteDatabase mDb;
    private String mMeetingId;
    private String mAgenda;
    private String mVenue;
    private String mMeetingDate;
    private String mMeetingTime;
    EditText textMeetingTime;
    EditText textMeetingId;

    Calendar calendar = Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener timeSetListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createMeetingViewModel = ViewModelProviders.of(this).get(CreateMeetingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_meeting, container, false);
//        final TextView textView = root.findViewById(R.id.create);
    //    textMeetingId = root.findViewById(R.id.text_meeting_id);
        final EditText textAgenda = root.findViewById(R.id.text_agenda);
        final EditText textVenue = root.findViewById(R.id.text_venue);
        final EditText textMeetingDate =  root.findViewById(R.id.text_meeting_date);
         textMeetingTime = root.findViewById(R.id.text_meeting_time);
        Button btnCreateMeeting = root.findViewById(R.id.button_create_meeting);

        textMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        textMeetingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }

        });

        btnCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String idSuffix = getLastMeetingID(new MeetingsOpenHelper(getContext()));
//                int suffix = Integer.parseInt(idSuffix.substring(21,22));
//                suffix = suffix +1;
                if(textAgenda == null || textVenue == null || textMeetingDate == null || textMeetingTime == null ){
                    //Dialog
                    Toast.makeText(getContext(), "Make sure you have entered all meeting details ", Toast.LENGTH_LONG).show();
                }else {
                    mMeetingId = "MEETINGID" + textMeetingDate.getText().toString() + textMeetingTime.getText().toString();
                    mAgenda = textAgenda.getText().toString();
                    mVenue = textVenue.getText().toString();
                    mMeetingDate = textMeetingDate.getText().toString();
                    mMeetingTime = textMeetingTime.getText().toString();

                    insertMeetings(new MeetingsOpenHelper(getContext()));
//                    textMeetingId.setText(null);
                    textAgenda.setText(null);
                    textVenue.setText(null);
                    textMeetingDate.setText(null);
                    textMeetingTime.setText(null);
                }

            }
        });
        createMeetingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        return root;
    }

    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
       newFragment.show(getFragmentManager(), "timePicker");

        textMeetingTime.setText(textTime);
    }


    public void insertMeetings(MeetingsOpenHelper dbHelper){
//         = new MeetingsOpenHelper(getContext());
        mDb = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID, mMeetingId);
        values.put(MeetingsDatabaseContract.MeetingsEntry.COLUMN_AGENDA, mAgenda);
        values.put(MeetingsDatabaseContract.MeetingsEntry.COLUMN_VENUE, mVenue);
        values.put(MeetingsDatabaseContract.MeetingsEntry.COLUMN_DATE, mMeetingDate);
        values.put(MeetingsDatabaseContract.MeetingsEntry.COLUMN_START_TIME, mMeetingTime);

        long newRow =  mDb.insert(MeetingsDatabaseContract.MeetingsEntry.TABLE_NAME, null, values);
        Toast.makeText(getContext(), "Meeting created @ " + newRow, Toast.LENGTH_LONG).show();

    }

    public String getLastMeetingID(MeetingsOpenHelper dbHelper){
        mDb = dbHelper.getReadableDatabase();
        String [] projection ={MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID};

        Cursor cursor = mDb.query(
                MeetingsDatabaseContract.MeetingsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToLast();
        return cursor.getString(cursor.getColumnIndex(MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID));
    }

}




