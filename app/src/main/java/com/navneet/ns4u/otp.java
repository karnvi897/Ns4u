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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {

    private EditText otp;
    private Button button;
    private String token;
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

        phonetext.setText("+91 " + number);

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String opts = otp.getText().toString();
                if (opts.length() == 6) {

                    dialog.setMessage("Verifying, please wait.");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(token, opts);
                    siginwithphone(phoneAuthCredential);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + number, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                dialog.setMessage("Verifying, please wait.");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                siginwithphone(phoneAuthCredential);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                token = s;
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void siginwithphone(PhoneAuthCredential phoneAuthCredential) {

        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(otp.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Intent intent = new Intent(getApplicationContext(), ProfileEdit.class);
                    intent.putExtra("key", "1");
                    startActivity(intent);
                    finish();
                    dialog.dismiss();

                } else {
                    Toast.makeText(otp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
    }
}
