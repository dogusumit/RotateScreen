package com.dogusumit.rotatescreen2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.RemoteViews;


class MyNotification extends Notification {

    private Context ctx;

    MyNotification(Context ctx,int myNotificationID){
        super();
        this.ctx=ctx;
        long when = System.currentTimeMillis();
        Notification.Builder builder = new Notification.Builder(ctx);
        Notification notification=builder.getNotification();
        notification.when=when;
        notification.tickerText=tickerText;
        notification.icon= R.mipmap.ic_none;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        notification.priority = PRIORITY_MIN;

        RemoteViews contentView=new RemoteViews(ctx.getPackageName(), R.layout.notifi_layout);

        setImages(contentView,ctx);
        setListeners(contentView);

        notification.contentView = contentView;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(myNotificationID,notification);
    }


    private void setListeners(RemoteViews view){
        Intent intent=new Intent(ctx,MyService.class);
        intent.setAction("action0");
        view.setOnClickPendingIntent(R.id.notif_but_0, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action1");
        view.setOnClickPendingIntent(R.id.notif_but_1, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action2");
        view.setOnClickPendingIntent(R.id.notif_but_2, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action3");
        view.setOnClickPendingIntent(R.id.notif_but_3, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action4");
        view.setOnClickPendingIntent(R.id.notif_but_4, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action5");
        view.setOnClickPendingIntent(R.id.notif_but_5, PendingIntent.getService(ctx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    private void setImages(RemoteViews remoteViews,Context context) {

        SharedPreferences settings = context.getSharedPreferences("com.dogusumit.rotatescreen2", 0);
        int yon = settings.getInt("Orientation",0);
        boolean kilit = settings.getBoolean("Lock",false);

        if (kilit)
            remoteViews.setImageViewResource(R.id.notif_but_5,R.mipmap.kilit_mavi);
        else
            remoteViews.setImageViewResource(R.id.notif_but_5,R.mipmap.kilitle);
        switch (yon) {
            case 0:
                remoteViews.setImageViewResource(R.id.notif_but_0,R.mipmap.otomatik_mavi);
                remoteViews.setImageViewResource(R.id.notif_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_4,R.mipmap.telefon);
                break;
            case 1:
                remoteViews.setImageViewResource(R.id.notif_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.notif_but_1,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.notif_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_4,R.mipmap.telefon);
                break;
            case 2:
                remoteViews.setImageViewResource(R.id.notif_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.notif_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_2,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.notif_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_4,R.mipmap.telefon);
                break;
            case 3:
                remoteViews.setImageViewResource(R.id.notif_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.notif_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_3,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.notif_but_4,R.mipmap.telefon);
                break;
            case 4:
                remoteViews.setImageViewResource(R.id.notif_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.notif_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.notif_but_4,R.mipmap.telefon_mavi);
                break;
        }
    }
}