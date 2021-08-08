package com.atm1504.backgroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private WorkManager mWorkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWorkManager = WorkManager.getInstance(getApplication());
        applyBlur(1);
    }

    void applyBlur(int blurLevel) {
        mWorkManager.enqueue(OneTimeWorkRequest.from(BackgroundTask.class));
    }
}