package com.navneet.ns4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        rc = (RecyclerView) findViewById(R.id.rc);

        FirebaseDatabase.getInstance().getReference("video").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VideoAdapter  videoAdapter=new VideoAdapter();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    VideoModel videoModel=dataSnapshot1.getValue(VideoModel.class);
                    videoAdapter.add(videoModel);
                }
                rc.setAdapter(videoAdapter);
                rc.setHasFixedSize(true);
                rc.setLayoutManager(new LinearLayoutManager(VideoActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(VideoActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
