package com.navneet.ns4u;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button;
        button=findViewById(R.id.button);
        phone=findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String Ph = phone.getText().toString();
                if (Ph.isEmpty()) {
                    phone.setError("Enter your phone number");
                }
                else if(phone.length()!= 10){
                    phone.setError("Enter Valid Number");
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), otp.class);
                    intent.putExtra("number", Ph);
                    startActivity(intent);
                }
            }
        });

}
}
