package com.example.integrated;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;


public class MyJobIntentService extends JobIntentService {

    static final int JOB_ID = 1000;
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(Intent intent) {
        int NotiID = 1;
        while (true) {
            sendNoti(getApplicationContext(), NotiID);
            Log.wtf("JobSheduler", "send notification");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        toast("All work complete");
    }
    final Handler mHandler = new Handler();
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyJobIntentService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void sendNoti(Context context, int notiID) {

        String info = "error";
        Random myRandom = new Random();
        NotificationManager mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        switch (myRandom.nextInt(4)) {
            case 0:
                info = "Adam : I'm downstairs come soon ";

                break;
            case 1:
                info = "Jagdeep : Hey call me it's urgent" ;

                break;
            case 2:
                info = " Venkat : I have reached where are you?";

                break;
            case 3:
                info = " Monica : I'm on my way";

                break;

        }
        notificationIntent.putExtra("mText", info);
        PendingIntent contentIntent = PendingIntent.getActivity(context, notiID, notificationIntent, 0);
        Notification notif = new NotificationCompat.Builder(context, MainActivity.id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("You got a notification")
                .setContentText(info)
                .setContentIntent(contentIntent)
                .setChannelId(MainActivity.id)
                .setAutoCancel(true)
                .build();
        mManager.notify(1, notif);
    }
}