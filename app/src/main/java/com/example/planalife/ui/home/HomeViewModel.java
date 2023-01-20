package com.example.planalife.ui.home;

import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planalife.ui.CalendarAdapter;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}