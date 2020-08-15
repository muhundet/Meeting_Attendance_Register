package com.example.musangano.ui.view_meetings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewMeetingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewMeetingsViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("List Of Meetings Organised by Currently Logged in");
    }

    public LiveData<String> getText() {
        return mText;
    }
}