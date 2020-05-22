package com.navneet.ns4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();}
                else {
                    FirebaseDatabase.getInstance().getReference("person").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.hasChild(FirebaseAuth.getInstance().getUid())){
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                finish();
                            }else {
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        },3000);
    }
}