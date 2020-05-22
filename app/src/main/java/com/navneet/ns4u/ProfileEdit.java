package com.navneet.ns4u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class ProfileEdit extends AppCompatActivity {

    private ImageView editprofilepic;
    private EditText editname;
    private EditText editlname;
    private EditText editemail;
    private EditText editphone;
    private EditText editaddress;
    private Button saveprofile;

    private Uri uri=null;
    private Bitmap buri=null;
    private ProgressDialog dialog;
    private String imageurl;
    private String imageurl1;

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

        dialog = new ProgressDialog(this);

        editphone.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        getImage();

        FirebaseDatabase.getInstance().getReference("person").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    imageurl1 = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profileurl").getValue().toString();
                }
                else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ProfileEdit.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        editprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileEdit.this,
                            Manifest.permission.CAMERA)) {

                    } else if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileEdit.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        ActivityCompat.requestPermissions(ProfileEdit.this,
                                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                                0);
                    }
                } else {
                    selectImage(ProfileEdit.this);
                }
            }
        });


        saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String en = editname.getText().toString();
                String eln = editlname.getText().toString();
                String ee = editemail.getText().toString();
                String ep = editphone.getText().toString();
                String ea = editaddress.getText().toString();


                if (en.isEmpty()) {
                    editname.setError("Enter Name");
                } else if (eln.isEmpty()) {
                    editlname.setError("Enter Name");
                } else if (ee.isEmpty()) {
                    editemail.setError("Enter Name");
                } else if (ea.isEmpty()) {
                    editaddress.setError("Enter Addrress");
                }else if (imageurl == ""){
                    Toast.makeText(ProfileEdit.this, "Select Image", Toast.LENGTH_SHORT).show();
                }
                else {

                    dialog.setMessage("Please wait.");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    final HashMap<String, String> map = new HashMap<>();

                    map.put("name", en);
                    map.put("lname", eln);
                    map.put("email", ee);
                    map.put("address", ea);
                    map.put("phone", ep);

                    if (imageurl1 == "") {

                        Toast.makeText(ProfileEdit.this, "hello", Toast.LENGTH_SHORT).show();
                        final StorageReference mountainsRef = FirebaseStorage.getInstance().getReference("person").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(FirebaseAuth.getInstance().getCurrentUser() + ".png");
                        UploadTask uploadTask = null;
                        if (uri != null) {
                            uploadTask = mountainsRef.putFile(uri);
                        } else {
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            buri.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] data = baos.toByteArray();
                            uploadTask = mountainsRef.putBytes(data);
                        }
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }

                                // Continue with the task to get the download URL
                                return mountainsRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    map.put("profileurl", downloadUri.toString());


                                    FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Intent homeintent = new Intent(getApplicationContext(), Home.class);
                                                startActivity(homeintent);
                                                dialog.dismiss();
                                                finish();
                                            } else {
                                                Toast.makeText(ProfileEdit.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                    });
                                }
                            }
                        });
                    }else
                    {
                        map.put("profileurl", imageurl1);


                        FirebaseDatabase.getInstance().getReference("person").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Intent homeintent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(homeintent);
                                    dialog.dismiss();
                                    finish();
                                } else {
                                    Toast.makeText(ProfileEdit.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                    }
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

                        editname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").getValue().toString());
                        editlname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lname").getValue().toString());
                        editaddress.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("address").getValue().toString());
                        editemail.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").getValue().toString());

                    }
                    else{
                        editname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").getValue().toString());
                        editlname.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("lname").getValue().toString());
                        editaddress.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("address").getValue().toString());
                        editemail.setText(dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").getValue().toString());
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

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        buri = (Bitmap) data.getExtras().get("data");
                        editprofilepic.setImageBitmap(buri);

                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        uri =  data.getData();
                        editprofilepic.setImageURI(uri);

                    }
                    break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {
                    Toast.makeText(getApplicationContext(),
                            "Unable to get Permissions", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    public void getImage(){
        FirebaseDatabase.getInstance().getReference("person").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    imageurl = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profileurl").getValue().toString();
                    Picasso.get().load(imageurl).into(editprofilepic);
                }
                else {
                    Toast.makeText(ProfileEdit.this, "No Profile Pic", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ProfileEdit.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
