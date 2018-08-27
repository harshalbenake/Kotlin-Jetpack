package com.harshalbenake.kotlinjetpack.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.harshalbenake.kotlinjetpack.R;
import com.harshalbenake.kotlinjetpack.worker.NotificationWorker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkStatus;

/**
 * This is helper class for all modules
 */
public class Helper {
    Context mContext;

    public Helper(Context context) {
        this.mContext = context;
    }

    /**
     * welcome Notification
     */
    public String notificationWork() {
        final String[] strRetunValue = {"Notification Called"};
        PeriodicWorkRequest.Builder periodicWorkRequestBuilder = new PeriodicWorkRequest.Builder(NotificationWorker.class,PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS);
        PeriodicWorkRequest periodicWorkRequest = periodicWorkRequestBuilder.build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
        WorkManager.getInstance().getStatusById(periodicWorkRequest.getId())
                .observe((LifecycleOwner) mContext, new Observer<WorkStatus>() {
                    @Override
                    public void onChanged(@Nullable WorkStatus workStatus) {
                        strRetunValue[0] =workStatus.getState().toString();
                        System.out.println("workStatus: "+strRetunValue[0]);

                    }
                });
        return strRetunValue[0];
    }


    /**
     * shows Notification
     */
    public void showNotification() {
        try {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher))
                    .setContentTitle("Welcome")
                    .setContentText("Welcome")
//                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setPriority(Notification.PRIORITY_HIGH);
            NotificationManager mNotificationManager =(NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(new Random(1).nextInt(), mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
