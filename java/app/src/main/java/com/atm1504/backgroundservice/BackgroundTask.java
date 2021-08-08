package com.atm1504.backgroundservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundTask extends Worker {
    public BackgroundTask(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Thread thread = new Thread() {
            public void run() {
                Looper.prepare();
                @SuppressLint("HandlerLeak") Handler mHandler = new Handler() {
                    @SuppressLint("HandlerLeak")
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        RunTimer();
                    }
                };
                Message message = new Message();
                mHandler.handleMessage(message);
                Looper.loop();
            }
        };
        thread.start();

        return Result.success();
    }

    public void RunTimer() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("ANAM", "seconds remaining:  " + millisUntilFinished);
            }

            public void onFinish() {
                Log.d("ANAM", "Finished the timer");
            }

        }.start();
    }

}
