package com.dogusumit.rotatescreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class EskiVersActivity extends AppCompatActivity {

    ImageButton ib0,ib1,ib2,ib3,ib4,ib5;
    boolean kilit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eskiverslayout);

        ib0 = (ImageButton) findViewById(R.id.actv_but_0);
        ib0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action0");
                    startService(intent);

                    renkGuncelle(0);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib1 = (ImageButton) findViewById(R.id.actv_but_1);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action1");
                    startService(intent);

                    renkGuncelle(1);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib2 = (ImageButton) findViewById(R.id.actv_but_2);
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action2");
                    startService(intent);

                    renkGuncelle(2);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib3 = (ImageButton) findViewById(R.id.actv_but_3);
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action3");
                    startService(intent);

                    renkGuncelle(3);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib4 = (ImageButton) findViewById(R.id.actv_but_4);
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action4");
                    startService(intent);

                    renkGuncelle(4);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        ib5 = (ImageButton) findViewById(R.id.actv_but_5);
        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent=new Intent(getApplicationContext(),MyService.class);
                    intent.setAction("action5");
                    startService(intent);

                    kilit = !kilit;
                    renkGuncelle(5);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


        SharedPreferences settings = getApplicationContext().getSharedPreferences("com.dogusumit.rotatescreen", 0);
        int yon = settings.getInt("Orientation",0);
        kilit = settings.getBoolean("Lock",false);

        if (kilit)
            ib5.setImageResource(R.mipmap.kilit_mavi);

        switch (yon) {
            case 0:
                ib0.setImageResource(R.mipmap.otomatik_mavi);
                break;
            case 1:
                ib1.setImageResource(R.mipmap.telefon_mavi);
                break;
            case 2:
                ib2.setImageResource(R.mipmap.telefon_mavi);
                break;
            case 3:
                ib3.setImageResource(R.mipmap.telefon_mavi);
                break;
            case 4:
                ib4.setImageResource(R.mipmap.telefon_mavi);
                break;
        }
    }

    void renkGuncelle(int tus) {
        switch (tus) {
            case 0:
                ib0.setImageResource(R.mipmap.otomatik_mavi);
                ib1.setImageResource(R.mipmap.telefon);
                ib2.setImageResource(R.mipmap.telefon);
                ib3.setImageResource(R.mipmap.telefon);
                ib4.setImageResource(R.mipmap.telefon);
                kilit = false;
                ib5.setImageResource(R.mipmap.kilitle);
                break;
            case 1:
                ib0.setImageResource(R.mipmap.otomatik);
                ib1.setImageResource(R.mipmap.telefon_mavi);
                ib2.setImageResource(R.mipmap.telefon);
                ib3.setImageResource(R.mipmap.telefon);
                ib4.setImageResource(R.mipmap.telefon);
                break;
            case 2:
                ib0.setImageResource(R.mipmap.otomatik);
                ib1.setImageResource(R.mipmap.telefon);
                ib2.setImageResource(R.mipmap.telefon_mavi);
                ib3.setImageResource(R.mipmap.telefon);
                ib4.setImageResource(R.mipmap.telefon);
                break;
            case 3:
                ib0.setImageResource(R.mipmap.otomatik);
                ib1.setImageResource(R.mipmap.telefon);
                ib2.setImageResource(R.mipmap.telefon);
                ib3.setImageResource(R.mipmap.telefon_mavi);
                ib4.setImageResource(R.mipmap.telefon);
                break;
            case 4:
                ib0.setImageResource(R.mipmap.otomatik);
                ib1.setImageResource(R.mipmap.telefon);
                ib2.setImageResource(R.mipmap.telefon);
                ib3.setImageResource(R.mipmap.telefon);
                ib4.setImageResource(R.mipmap.telefon_mavi);
                break;
            case 5:
                if (kilit)
                    ib5.setImageResource(R.mipmap.kilit_mavi);
                else
                    ib5.setImageResource(R.mipmap.kilitle);
        }
    }
}