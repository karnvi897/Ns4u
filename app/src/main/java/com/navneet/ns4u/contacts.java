package com.navneet.ns4u;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class contacts extends Fragment {

    private FloatingActionButton addfab;
    private LinearLayout addcontactlayout;
    private EditText addname;
    private EditText addmobile;
    private Button addcontact;




    public contacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addfab = view.findViewById(R.id.addfab);
        addcontactlayout = view.findViewById(R.id.addcontactlayout);
        addname = view.findViewById(R.id.addname);
        addmobile = view.findViewById(R.id.addmobile);
        addcontact = view.findViewById(R.id.addcontact);

        addcontactlayout.setVisibility(View.GONE);

        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcontactlayout.setVisibility(View.VISIBLE);
            }
        });

        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcontactlayout.setVisibility(View.GONE);
            }
        });
    }
}
