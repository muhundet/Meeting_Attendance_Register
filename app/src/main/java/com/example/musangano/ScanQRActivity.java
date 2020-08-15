package com.example.musangano;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class ScanQRActivity extends AppCompatActivity {

    TextView tvMeetingId;
    TextView tvAgenda;
    TextView tvVenue;
    TextView tvDate;
    TextView tvTime;
    Button btnScan;
    Button btnEditMeeting;
    Button btnViewRegister;
    Button btnRemoveMeeting;

    private String mMeetingId;
    private String mEmployeeId;
    private String mDate;
    private String mFirstName;
    private String mSurname;
    private String mPost;
    private String mDepartment;

    SQLiteDatabase mDb;

    private IntentIntegrator qrScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);

        tvMeetingId = (TextView) findViewById(R.id.tvMeetingId);
        tvAgenda = findViewById(R.id.tvAgenda);
        tvVenue = findViewById(R.id.tvVenue);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        btnScan = findViewById(R.id.btnScan);
        btnEditMeeting = findViewById(R.id.btnEditMeeting);
        btnRemoveMeeting = findViewById(R.id.btnRemoveMeeting);
        btnViewRegister = findViewById(R.id.btnViewRegister);
        qrScan = new IntentIntegrator(this);
//        qrScan.initiateScan();

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initiating the qr code scan
                qrScan.initiateScan();
            }
        });

        btnEditMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ScanQRActivity.this, "Edit meeting", Toast.LENGTH_LONG).show();
            }
        });

        btnRemoveMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeMeeting(new MeetingsOpenHelper(ScanQRActivity.this));
                finish();

            }
        });

        btnViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanQRActivity.this, RegisterActivity.class);
                intent.putExtra("meeting_id", tvMeetingId.getText());
                intent.putExtra("agenda", tvAgenda.getText());
                intent.putExtra("venue", tvVenue.getText());
                intent.putExtra("date", tvDate.getText());
                intent.putExtra("time", tvTime.getText());
                startActivity(intent);
                Toast.makeText(ScanQRActivity.this, " Register", Toast.LENGTH_LONG).show();
            }
        });

        initialiseViews();

    }

    private void initialiseViews() {
        Intent intent = getIntent();
        Meeting meeting = intent.getParcelableExtra("meeting");
        tvMeetingId.setText(meeting.meetingId);
        tvAgenda.setText(meeting.agenda);
        tvVenue.setText(meeting.venue);
        tvDate.setText(meeting.meetingDate);
        tvTime.setText(meeting.meetingTime);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject employeeJson = new JSONObject(result.getContents());
                    //setting values to textviews  tvScanning.setText(result.getContents()); textViewAddress.setText(obj.getString("address"));
                    mMeetingId = tvMeetingId.getText().toString();
                    mEmployeeId = employeeJson.getString("id");
                    mFirstName = employeeJson.getString("name");
                    mSurname = employeeJson.getString("surname");
                    mPost = employeeJson.getString("post");
                    mDepartment = employeeJson.getString("Department");
                    mDate = tvDate.getText().toString();

                    insertRegisterEntries(new MeetingsOpenHelper(this));

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here that means the encoded format not matches in this case you can display whatever data is available on the qrcode to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void insertRegisterEntries(MeetingsOpenHelper dbHelper) {
        mDb = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_MEETING_ID, mMeetingId);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_EMPLOYEE_ID, mEmployeeId);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_DATE, mDate);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_FIRST_NAME, mFirstName);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_SURNAME, mSurname);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_POST, mPost);
        values.put(MeetingsDatabaseContract.RegisterEntry.COLUMN_DEPARTMENT, mDepartment);


        long newRow =  mDb.insert(MeetingsDatabaseContract.RegisterEntry.TABLE_NAME, null, values);
        Toast.makeText(this, "Register Entry " + newRow, Toast.LENGTH_LONG).show();
    }

    private void removeMeeting(MeetingsOpenHelper dbHelper){
        mDb = dbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = MeetingsDatabaseContract.MeetingsEntry.COLUMN_MEETING_ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {(String) tvMeetingId.getText()};
// Issue SQL statement.
        int deletedRows = mDb.delete(MeetingsDatabaseContract.MeetingsEntry.TABLE_NAME, selection, selectionArgs);
        Toast.makeText(this, deletedRows +"Meeting(s) deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

