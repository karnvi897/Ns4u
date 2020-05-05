package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button;
        final EditText phone;
        button=findViewById(R.id.button);
        phone=findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ph = phone.getText().toString();
                if (Ph.isEmpty()) {
                    phone.setError("Enter your phone number");
                } else {
                    Intent intent = new Intent(getApplicationContext(), otp.class);
                    startActivity(intent);
                }
            }
        });

}
}
