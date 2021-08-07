package com.atm1504.backgroundservicetesting

import android.app.IntentService
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log

class RSSPullService : IntentService(RSSPullService::class.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("Krishna", "testing in background")
        var counter = 0
        object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("Krishna", "Time is ${counter}")
                counter++
            }

            override fun onFinish() {
                Log.d("Krishna", "Timer  is up at ${counter}")
            }
        }.start()
    }

}