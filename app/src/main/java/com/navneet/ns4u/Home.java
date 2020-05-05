package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {


    private SpaceNavigationView space;
    private TextView namedash;
    private CircleImageView profile;
    private CardView cardempmain;
    private LinearLayout ambulancecard;
    private LinearLayout tipscard;
    private LinearLayout lawcard;
    private LinearLayout videocard;
    private FloatingActionButton policebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        space = (SpaceNavigationView) findViewById(R.id.space);
        namedash = (TextView) findViewById(R.id.namedash);
        profile = (CircleImageView) findViewById(R.id.profile);
        cardempmain = (CardView) findViewById(R.id.cardempmain);
        ambulancecard = (LinearLayout) findViewById(R.id.ambulancecard);
        tipscard = (LinearLayout) findViewById(R.id.tipscard);
        lawcard = (LinearLayout) findViewById(R.id.lawcard);
        videocard = (LinearLayout) findViewById(R.id.videocard);
        policebutton = (FloatingActionButton) findViewById(R.id.policebutton);

        tipscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),tips.class));
            }
        });

        lawcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),laws.class));
            }
        });

        videocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        space.showIconOnly();
        space.initWithSaveInstanceState(savedInstanceState);
        space.addSpaceItem(new SpaceItem("Home", R.drawable.ic_home_black_24dp));
        space.addSpaceItem(new SpaceItem("Profile", R.drawable.ic_person_black_24dp));
        space.addSpaceItem(new SpaceItem("Share", R.drawable.ic_home_black_24dp));
        space.addSpaceItem(new SpaceItem("Settings", R.drawable.border));

        space.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Home.this, "centre button", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();


            }
        });

    }
}
