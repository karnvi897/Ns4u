package com.navneet.ns4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ProfileEdit extends AppCompatActivity {

    private ImageView editprofilepic;
    private EditText editname;
    private EditText editlname;
    private EditText editemail;
    private EditText editphone;
    private EditText editaddress;
    private Button saveprofile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_edit);
        editprofilepic = (ImageView) findViewById(R.id.editprofilepic);
        editname = (EditText) findViewById(R.id.editname);
        editlname = (EditText) findViewById(R.id.editlname);
        editemail = (EditText) findViewById(R.id.editemail);
        editphone = (EditText) findViewById(R.id.editphone);
        editaddress = (EditText) findViewById(R.id.editaddress);
        saveprofile = (Button) findViewById(R.id.saveprofile);

        editphone.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());


        saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String en = editname.getText().toString();
                String eln = editlname.getText().toString();
                String ee = editemail.getText().toString();
                String ep = editphone.getText().toString();
                String ea = editaddress.getText().toString();


        if (en.isEmpty()){
            editname.setError("Enter Name");
        }
        else if (eln.isEmpty()){
            editlname.setError("Enter Name");
        }
        else if (ee.isEmpty()){
            editemail.setError("Enter Name");
        }
        else if (ea.isEmpty()){
            editaddress.setError("Enter Addrress");
        }
        else{
            HashMap<String,String> map=new HashMap<>();

            map.put("name", en);
            map.put("lname",eln);
            map.put("email", ee);
            map.put("address", ea);
            map.put("phone", ep);
            map.put("photourl","null");

            FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                                Intent homeintent = new Intent(getApplicationContext(),Home.class);
                                startActivity(homeintent);
                                finish();
                    }
                    else {
                        Toast.makeText(ProfileEdit.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

            });
        }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        getdata();
    }

    private void getdata() {
        FirebaseDatabase.getInstance().getReference("person").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(FirebaseAuth.getInstance().getUid())){
                    String s = getIntent().getStringExtra("key");

                    if (s == "1") {

                        editname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("name").getValue().toString());
                        editlname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("lname").getValue().toString());
                        editaddress.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("address").getValue().toString());
                        editemail.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("email").getValue().toString());

                        Toast.makeText(ProfileEdit.this, "Welcome Back", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        editname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("name").getValue().toString());
                        editlname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("lname").getValue().toString());
                        editaddress.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("address").getValue().toString());
                        editemail.setText(dataSnapshot.child(FirebaseAuth.getInstance().getUid()).child("email").getValue().toString());
                    }

                }else {
                    Toast.makeText(ProfileEdit.this, "Welcome Enter Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileEdit.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
