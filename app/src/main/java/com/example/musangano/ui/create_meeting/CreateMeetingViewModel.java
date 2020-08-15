package com.example.musangano.ui.create_meeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateMeetingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreateMeetingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Create Meeting");
    }

    public LiveData<String> getText() {
        return mText;
    }
}