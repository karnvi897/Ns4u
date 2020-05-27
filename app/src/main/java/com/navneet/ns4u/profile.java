package com.navneet.ns4u;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    private ImageView profilepic;
    private TextView nameprofile;
    private LinearLayout logout;
    private TextView email;
    private TextView phone;
    private TextView address;
    private Button editprofile;
    private ProgressDialog dialog;


    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        LinearLayout linearLayout=view.findViewById(R.id.shear);
        profilepic = (ImageView) view.findViewById(R.id.profilepic);
        nameprofile = (TextView) view.findViewById(R.id.nameprofile);
        logout = (LinearLayout) view.findViewById(R.id.logout);
        email = (TextView) view.findViewById(R.id.email);
        phone = (TextView) view.findViewById(R.id.phone);
        address = (TextView) view.findViewById(R.id.address);
        editprofile = (Button) view.findViewById(R.id.editprofile);

        dialog = new ProgressDialog(getActivity());


        getImage();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: " );
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

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
        dialog.setMessage("Loading Profile");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {

                    String fname,lname,name;

                    fname = dataSnapshot.child("name").getValue().toString();
                    lname = dataSnapshot.child("lname").getValue().toString();
                    name = fname+" "+lname;
                    nameprofile.setText(name);
                    address.setText(dataSnapshot.child("address").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    phone.setText(dataSnapshot.child("phone").getValue().toString());

                    dialog.dismiss();

                } else {
                    dialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    public void getImage() {
        FirebaseDatabase.getInstance().getReference("person").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    String imageurl = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profileurl").getValue().toString();
                    Picasso.get().load(imageurl).into(profilepic);
                } else {
                    Toast.makeText(getActivity(), "Add Profile Picture", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
