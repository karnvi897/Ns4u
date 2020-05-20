package com.navneet.ns4u;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class tips extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView lnavgation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


            viewPager=findViewById(R.id.loginviewpager);
        lnavgation=findViewById(R.id.bottomNavigationView);



            FragmentAdaptor adaptor=new FragmentAdaptor(getSupportFragmentManager());
            viewPager.setAdapter(adaptor);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            lnavgation.setSelectedItemId(R.id.owner);
                            break;
                    case 1:
                        lnavgation.setSelectedItemId(R.id.manager);
                        break;

                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        lnavgation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.owner:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.manager:
                        viewPager.setCurrentItem(1);
                        return true;
                    default:
                        return false;
                }
            }
        });

        }
    }

