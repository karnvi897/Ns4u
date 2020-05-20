package com.navneet.ns4u;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    private TextView namedash;
    private CircleImageView profile;
    private CardView cardempmain;
    private LinearLayout ambulancecard;
    private LinearLayout tipscard;
    private LinearLayout lawcard;
    private LinearLayout videocard;
    private FloatingActionButton policebutton;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        namedash = (TextView) view.findViewById(R.id.namedash);
        profile = (CircleImageView) view.findViewById(R.id.profile);
        cardempmain = (CardView) view.findViewById(R.id.cardempmain);
        ambulancecard = (LinearLayout) view.findViewById(R.id.ambulancecard);
        tipscard = (LinearLayout) view.findViewById(R.id.tipscard);
        lawcard = (LinearLayout) view.findViewById(R.id.lawcard);
        videocard = (LinearLayout) view.findViewById(R.id.videocard);
        policebutton = (FloatingActionButton) view.findViewById(R.id.policebutton);

        getData();

        policebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:100"));
                startActivity(intent);
            }
        });

        ambulancecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:108"));
                startActivity(intent);
            }
        });


        tipscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),tips.class));
            }
        });

        lawcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),laws.class));
            }
        });

        videocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),VideoActivity.class));
            }
        });
        return  view;
    }

    public void getData(){
        FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()){
                    namedash.setText(dataSnapshot.child("name").getValue().toString());
                }
                else {
                    Toast.makeText(getActivity(), "Enter Profile", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
