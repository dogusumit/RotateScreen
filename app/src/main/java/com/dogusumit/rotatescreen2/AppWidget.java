package com.dogusumit.rotatescreen2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context, AppWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews = klikEkle(context);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);
        }
    }

    RemoteViews klikEkle(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        Intent intent=new Intent(context,MyService.class);
        intent.setAction("action0");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_0, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action1");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_1, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action2");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_2, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action3");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_3, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action4");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_4, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        intent.setAction("action5");
        remoteViews.setOnClickPendingIntent(R.id.wid_but_5, PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));

        SharedPreferences settings = context.getSharedPreferences("com.dogusumit.rotatescreen2", 0);
        int yon = settings.getInt("Orientation",0);
        boolean kilit = settings.getBoolean("Lock",false);

        if (kilit)
            remoteViews.setImageViewResource(R.id.wid_but_5,R.mipmap.kilit_mavi);
        else
            remoteViews.setImageViewResource(R.id.wid_but_5,R.mipmap.kilitle);
        switch (yon) {
            case 0:
                remoteViews.setImageViewResource(R.id.wid_but_0,R.mipmap.otomatik_mavi);
                remoteViews.setImageViewResource(R.id.wid_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_4,R.mipmap.telefon);
                break;
            case 1:
                remoteViews.setImageViewResource(R.id.wid_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.wid_but_1,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.wid_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_4,R.mipmap.telefon);
                break;
            case 2:
                remoteViews.setImageViewResource(R.id.wid_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.wid_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_2,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.wid_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_4,R.mipmap.telefon);
                break;
            case 3:
                remoteViews.setImageViewResource(R.id.wid_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.wid_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_3,R.mipmap.telefon_mavi);
                remoteViews.setImageViewResource(R.id.wid_but_4,R.mipmap.telefon);
                break;
            case 4:
                remoteViews.setImageViewResource(R.id.wid_but_0,R.mipmap.otomatik);
                remoteViews.setImageViewResource(R.id.wid_but_1,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_2,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_3,R.mipmap.telefon);
                remoteViews.setImageViewResource(R.id.wid_but_4,R.mipmap.telefon_mavi);
                break;
        }
        return remoteViews;
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        SharedPreferences settings = context.getSharedPreferences("com.dogusumit.rotatescreen2", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("Widget",true);
        editor.apply();
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        SharedPreferences settings = context.getSharedPreferences("com.dogusumit.rotatescreen2", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("Widget",false);
        editor.apply();
    }
}