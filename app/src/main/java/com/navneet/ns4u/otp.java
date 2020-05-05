package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class otp extends AppCompatActivity {

    private EditText otp;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        String number = getIntent().getStringExtra("number");
        button=findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {

            @   Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);

            }
        });
    }
}
