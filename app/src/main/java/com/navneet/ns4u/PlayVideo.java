package com.navneet.ns4u;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {
    private TextView textView69;
    private VideoView videoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        VideoModel videoModel= (VideoModel) getIntent().getSerializableExtra("data");

        textView69 = (TextView) findViewById(R.id.textView69);
        videoView3 = (VideoView) findViewById(R.id.videoView3);


        textView69.setText(videoModel.getName());
      //  videoView3.setVideoPath(videoModel.getVideourl());
        videoView3.setVideoURI(Uri.parse(videoModel.getVideourl()));

    }
}
