package com.example.musangano.ui.previous_meetings;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musangano.Meeting;
import com.example.musangano.MeetingsDataManager;
import com.example.musangano.MeetingsOpenHelper;
import com.example.musangano.MeetingsRecyclerAdapter;
import com.example.musangano.R;
import com.example.musangano.ui.view_meetings.ViewMeetingsViewModel;

import java.util.ArrayList;

public class PreviousMeetingsFragment extends Fragment {

    private PreviousMeetingsViewModel previousMeetingsViewModel;

    private RecyclerView rvMeetings;
    MeetingsOpenHelper mDbOpenHelper;
    SQLiteDatabase mDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        previousMeetingsViewModel =
                ViewModelProviders.of(this).get(PreviousMeetingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_meetings, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);

//        viewMeetingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//
//            }
//        });
        rvMeetings = (RecyclerView) root.findViewById(R.id.rv_meetings);
        LinearLayoutManager meetingsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMeetings.setLayoutManager(meetingsLayoutManager);
        MeetingsDataManager m = new MeetingsDataManager();
        ArrayList<Meeting> prevMeetings = MeetingsDataManager.getInstance().getprevMeetings();


        MeetingsDataManager.readMeetings(new MeetingsOpenHelper(getContext()));
        MeetingsRecyclerAdapter adapter = new MeetingsRecyclerAdapter(prevMeetings);
        rvMeetings.setAdapter(adapter);

        return root;
    }
}