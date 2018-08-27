package com.harshalbenake.kotlinjetpack.worker;

import android.support.annotation.NonNull;

import com.harshalbenake.kotlinjetpack.utils.Helper;

import androidx.work.Worker;

/**
 * This is worker class for notification
 */
public class NotificationWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {
        printPeriodicWork();
        // Indicate success or failure with your return value:
        return Result.SUCCESS;

    }

    private void printPeriodicWork() {
        new Helper(getApplicationContext()).showNotification();

    }
}