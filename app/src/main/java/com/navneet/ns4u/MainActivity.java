package com.navneet.ns4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=findViewById(R.id.videoView);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.logo);
        videoView.setVideoURI(uri);
        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                Intent login =new Intent (getApplicationContext(), Login.class);
                startActivity(login);
                finish();
            }
        },4000);

    }
}
