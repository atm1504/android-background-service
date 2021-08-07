package com.atm1504.backgroundservicetesting

import android.content.Context

import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class BackgroundTask(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        Log.d("Krishna", "Work is done again")
        return Result.success()

    }
}