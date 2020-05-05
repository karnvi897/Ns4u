package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {


    private SpaceNavigationView space;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flfragment, new Dashboard());
        ft.commit();


        space = (SpaceNavigationView) findViewById(R.id.space);

        space.showIconOnly();
        space.initWithSaveInstanceState(savedInstanceState);
        space.addSpaceItem(new SpaceItem("Home", R.drawable.ic_home_black_24dp));
        space.addSpaceItem(new SpaceItem("Profile", R.drawable.ic_person_black_24dp));
        space.addSpaceItem(new SpaceItem("Share", R.drawable.ic_people_black_24dp));
        space.addSpaceItem(new SpaceItem("Settings", R.drawable.ic_settings_black_24dp));

        space.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Home.this, "centre button", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemName, Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemIndex){
                    case 0:
                        ft.replace(R.id.flfragment, new Dashboard());
                        ft.commit();

                        break;
                    case 1:
                        ft.replace(R.id.flfragment, new profile());
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.flfragment, new contacts());
                        ft.commit();

                        break;
                    case 3:
                        ft.replace(R.id.flfragment, new Settings());
                        ft.commit();
                        break;
                }


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemName, Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemIndex){
                    case 0:
                        ft.replace(R.id.flfragment, new Dashboard());
                        ft.commit();

                        break;
                    case 1:
                        ft.replace(R.id.flfragment, new profile());
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.flfragment, new contacts());
                        ft.commit();

                        break;
                    case 3:
                        ft.replace(R.id.flfragment, new Settings());
                        ft.commit();
                        break;
                }
            }
        });

    }
}
