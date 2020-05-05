package com.navneet.ns4u;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class otp extends AppCompatActivity {

    private EditText otp;
    private Button button;
    private ProgressDialog dialog;
    private TextView phonetext;
    private ImageView wrong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        dialog = new ProgressDialog(this);
        String number = getIntent().getStringExtra("number");

        phonetext = findViewById(R.id.phonetext);
        wrong = findViewById(R.id.wrongnumber);
        button = findViewById(R.id.submit);
        otp = findViewById(R.id.otp);

        phonetext.setText("91 "+number);

        wrong.setOnClickListener(new View.OnClickListener() {
            @   Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String opts= otp.getText().toString();
                if(opts.length()==6){

                    dialog.setMessage("Verifying, please wait.");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PhoneAuth
    }

}
