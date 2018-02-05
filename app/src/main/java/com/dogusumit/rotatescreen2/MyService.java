package com.dogusumit.rotatescreen2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;


public class MyService extends Service {
    private final static int myNotificationID = new AtomicInteger(0).intValue();
    LinearLayout oriChng;
    WindowManager.LayoutParams oriPrm;
    WindowManager wm;
    boolean isAdded;
    SharedPreferences sharedPreferences;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if (intent != null)
                if (intent.getAction() != null)
                    actionYorumla(intent.getAction());
            ekranDondur();
            updateWidget(getApplicationContext());
            bildirimGoster();
        } catch (Exception e) {
            Log.e("onStartCommand : ",e.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        oriChng = new LinearLayout(getBaseContext());
        oriChng.setClickable(false);
        oriChng.setFocusable(false);
        oriChng.setFocusableInTouchMode(false);
        oriChng.setLongClickable(false);
        oriChng.setVisibility(View.VISIBLE);
        oriPrm = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888);
        wm = (WindowManager) getSystemService(Service.WINDOW_SERVICE);

        isAdded = false;

        sharedPreferences = getApplicationContext().getSharedPreferences("com.dogusumit.rotatescreen2", 0);
    }

    void ekranDondur() {
        try {
            boolean kilit = sharedPreferences.getBoolean("Lock", false);
            int yon = sharedPreferences.getInt("Orientation", 0);
            switch (yon) {
                default:
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
                    sharedPreferences.edit().putBoolean("Lock",false).apply();
                    if (isAdded) {
                        wm.removeView(oriChng);
                        isAdded = false;
                    }
                    break;
                case 1:
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                    Settings.System.putInt(getContentResolver(), Settings.System.USER_ROTATION, Surface.ROTATION_0);
                    if (!kilit) {
                        if (isAdded) {
                            wm.removeView(oriChng);
                            isAdded = false;
                        }
                    } else {
                        oriPrm.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                        if (isAdded)
                            wm.updateViewLayout(oriChng, oriPrm);
                        else {
                            wm.addView(oriChng, oriPrm);
                            isAdded = true;
                        }
                    }
                    break;
                case 2:
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                    Settings.System.putInt(getContentResolver(), Settings.System.USER_ROTATION, Surface.ROTATION_180);
                    if (!kilit) {
                        if (isAdded) {
                            wm.removeView(oriChng);
                            isAdded = false;
                        }
                    } else {
                        oriPrm.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                        if (isAdded)
                            wm.updateViewLayout(oriChng, oriPrm);
                        else {
                            wm.addView(oriChng, oriPrm);
                            isAdded = true;
                        }
                    }
                    break;
                case 3:
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                    Settings.System.putInt(getContentResolver(), Settings.System.USER_ROTATION, Surface.ROTATION_90);
                    if (!kilit) {
                        if (isAdded) {
                            wm.removeView(oriChng);
                            isAdded = false;
                        }
                    } else {
                        oriPrm.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                        if (isAdded)
                            wm.updateViewLayout(oriChng, oriPrm);
                        else {
                            wm.addView(oriChng, oriPrm);
                            isAdded = true;
                        }
                    }
                    break;
                case 4:
                    Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
                    Settings.System.putInt(getContentResolver(), Settings.System.USER_ROTATION, Surface.ROTATION_270);
                    if (!kilit) {
                        if (isAdded) {
                            wm.removeView(oriChng);
                            isAdded = false;
                        }
                    } else {
                        oriPrm.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                        if (isAdded)
                            wm.updateViewLayout(oriChng, oriPrm);
                        else {
                            wm.addView(oriChng, oriPrm);
                            isAdded = true;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void actionYorumla(String action) {
        try {
            if (action != null) {
                if (action.equals("action0")) {
                    sharedPreferences.edit().putInt("Orientation", 0).apply();
                } else if (action.equals("action1")) {
                    sharedPreferences.edit().putInt("Orientation", 1).apply();
                } else if (action.equals("action2")) {
                    sharedPreferences.edit().putInt("Orientation", 2).apply();
                } else if (action.equals("action3")) {
                    sharedPreferences.edit().putInt("Orientation", 3).apply();
                } else if (action.equals("action4")) {
                    sharedPreferences.edit().putInt("Orientation", 4).apply();
                } else if (action.equals("action5")) {
                    boolean kilit = sharedPreferences.getBoolean("Lock", false);
                    sharedPreferences.edit().putBoolean("Lock", !kilit).apply();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void updateWidget(Context context) {
        boolean widget = sharedPreferences.getBoolean("Widget", false);
        if (widget) {
            try {
                Intent intent = new Intent(context, AppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                ComponentName thisWidget = new ComponentName(context, AppWidget.class);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
                context.sendBroadcast(intent);
            } catch (Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    void bildirimGoster() {
        boolean widget = sharedPreferences.getBoolean("Notification", false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (widget) {
                try {
                    new MyNotification(this, myNotificationID);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(myNotificationID);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if (widget) {
                try {
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(this)
                                    .setSmallIcon(R.mipmap.ic_none)
                                    .setContentTitle(getString(R.string.app_name))
                                    .setContentText(getString(R.string.str5))
                                    .setOngoing(true)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

                    Intent notificationIntent = new Intent(this, EskiVersActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);

                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(myNotificationID, builder.build());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(myNotificationID);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}