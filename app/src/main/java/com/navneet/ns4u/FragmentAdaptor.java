package com.navneet.ns4u;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class FragmentAdaptor extends FragmentPagerAdapter {
    public FragmentAdaptor(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentTips();
            case 1:
                return new Threats();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}
