package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    private ImageView profilepic;
    private TextView nameprofile;
    private LinearLayout savedvideos;
    private LinearLayout logout;
    private TextView email;
    private TextView phone;
    private TextView address;
    private Button editprofile;


    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        profilepic = (ImageView) view.findViewById(R.id.profilepic);
        nameprofile = (TextView) view.findViewById(R.id.nameprofile);
        savedvideos = (LinearLayout) view.findViewById(R.id.savedvideos);
        logout = (LinearLayout) view.findViewById(R.id.logout);
        email = (TextView) view.findViewById(R.id.email);
        phone = (TextView) view.findViewById(R.id.phone);
        address = (TextView) view.findViewById(R.id.address);
        editprofile = (Button) view.findViewById(R.id.editprofile);






        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ProfileEdit.class);
                startActivity(intent);
            }
        });


        getdata();
        return  view;

    }

    private void getdata() {
        FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {


                    nameprofile.setText(dataSnapshot.child("name").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());

                    Toast.makeText(getContext(), "Welcome Back", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
