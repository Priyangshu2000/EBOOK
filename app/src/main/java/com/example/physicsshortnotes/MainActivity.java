package com.example.physicsshortnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.BundleCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity {
    public static  int SPLASH_SCREEN=5000;
Animation topanim,botanim;
ImageView books;
TextView appname,comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        botanim= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        books=findViewById(R.id.books);
        appname=findViewById(R.id.appname);
        comp=findViewById(R.id.comp);
        books.setAnimation(topanim);
        comp.setAnimation(botanim);
        appname.setAnimation(botanim);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent(MainActivity.this,Mainscreen.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);

    }
}

