package com.atm1504.backgroundservicetesting

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val workManager by lazy {
        WorkManager.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createPeriodicWorkRequest()
    }

    private fun createPeriodicWorkRequest() {
        val imageWorker = PeriodicWorkRequestBuilder<BackgroundTask>(
            15, TimeUnit.MINUTES
        )
            .addTag("imageWork")
            .build()
        workManager.enqueueUniquePeriodicWork(
            "periodicImageDownload1",
            ExistingPeriodicWorkPolicy.KEEP,
            imageWorker
        )
        observeWork(imageWorker.id)
    }

    private fun observeWork(id: UUID) {
        workManager.getWorkInfoByIdLiveData(id)
            .observe(this, { info ->
                if (info != null && info.state.isFinished) {
                    Toast.makeText(this, "It has been observed", Toast.LENGTH_SHORT).show()
                    Log.d("Krishna", "Work done!!")
                }
            })
    }
}