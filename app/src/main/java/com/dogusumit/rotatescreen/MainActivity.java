package com.dogusumit.rotatescreen;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences settings = context.getSharedPreferences("com.dogusumit.rotatescreen", 0);
        final SharedPreferences.Editor editor = settings.edit();
        int yon = settings.getInt("Orientation",0);
        boolean kilit = settings.getBoolean("Lock", false);
        boolean bildirim = settings.getBoolean("Notification", false);


        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setSelection(yon);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor.putInt("Orientation", position);
                editor.apply();
                if ( izinleriKontrolEt() )
                    servisBaslat();
                kilitIptal(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox1.setChecked(kilit);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("Lock", isChecked);
                editor.apply();
                if ( izinleriKontrolEt() )
                    servisBaslat();
            }
        });

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox2.setChecked(bildirim);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("Notification", isChecked);
                editor.apply();
                if ( izinleriKontrolEt() )
                    servisBaslat();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && isChecked)
                    toastla(getString(R.string.jellybean));
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    boolean izinleriKontrolEt() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(context)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    return false;
                }
                if (!Settings.System.canWrite(context)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                            Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    return false;
                }
                return true;
            }
            else
                return true;
        } catch (Exception e) {
            toastla(e.getLocalizedMessage());
            return false;
        }
    }

    private void servisBaslat() {
        try {
                startService(new Intent(this, MyService.class));
        } catch (Exception e) {
            toastla(e.getLocalizedMessage());
        }
    }

    void kilitIptal(int a){
        if (a == 0) {
            findViewById(R.id.checkbox1).setEnabled(false);
            ((CheckBox) findViewById(R.id.checkbox1)).setChecked(false);
        } else
            findViewById(R.id.checkbox1).setEnabled(true);
    }

    private void uygulamayiOyla() {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
            } catch (Exception ane) {
                toastla(e.getMessage());
            }
        }
    }

    private void marketiAc() {
        try {
            Uri uri = Uri.parse("market://developer?id=" + getString(R.string.play_store_id));
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=" + getString(R.string.play_store_id))));
            } catch (Exception ane) {
                toastla(e.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.oyla:
                uygulamayiOyla();
                return true;
            case R.id.market:
                marketiAc();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void toastla(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}