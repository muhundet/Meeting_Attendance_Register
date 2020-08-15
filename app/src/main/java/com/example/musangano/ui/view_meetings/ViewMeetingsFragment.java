package com.example.musangano.ui.view_meetings;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musangano.Meeting;
import com.example.musangano.MeetingsDataManager;
import com.example.musangano.MeetingsOpenHelper;
import com.example.musangano.MeetingsRecyclerAdapter;
import com.example.musangano.R;

import java.util.ArrayList;

public class ViewMeetingsFragment extends Fragment {

    private ViewMeetingsViewModel viewMeetingsViewModel;
    private RecyclerView rvMeetings;
    MeetingsRecyclerAdapter adapter;
    ArrayList<Meeting> meetings;
    MeetingsOpenHelper mDbOpenHelper;
    SQLiteDatabase mDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewMeetingsViewModel =
                ViewModelProviders.of(this).get(ViewMeetingsViewModel.class);
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

        meetings = MeetingsDataManager.getInstance().getMeetings();
        MeetingsDataManager.readMeetings(new MeetingsOpenHelper(getContext()));
        adapter = new MeetingsRecyclerAdapter(meetings);
        rvMeetings.setAdapter(adapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        meetings.clear();
        adapter = new MeetingsRecyclerAdapter(meetings);
        rvMeetings.setAdapter(adapter);
    }


}