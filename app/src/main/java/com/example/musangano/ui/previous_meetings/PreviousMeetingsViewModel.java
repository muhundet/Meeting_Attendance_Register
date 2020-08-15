package com.example.musangano.ui.previous_meetings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreviousMeetingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PreviousMeetingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("V");
    }

    public LiveData<String> getText() {
        return mText;
    }
}