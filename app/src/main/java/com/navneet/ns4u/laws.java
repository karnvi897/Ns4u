package com.navneet.ns4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class laws extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);
        Button button;
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @   Override
            public void onClick(View v) {
                android.content.Intent intent=new Intent(getApplicationContext(),Acidattack.class);
                startActivity(intent);

            }
        });
        Button button1;
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @   Override
            public void onClick(View v) {
                android.content.Intent intent=new Intent(getApplicationContext(),Rape.class);
                startActivity(intent);

            }
        });
        Button button2;
        button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @   Override
            public void onClick(View v) {
                android.content.Intent intent=new Intent(getApplicationContext(),kidnap.class);
                startActivity(intent);

            }
        });
        Button button3;
        button=findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(), Murder.class);
                startActivity(intent);
            }
        });
        Button button4;
        button=findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(),Cruelty.class);
                startActivity(intent);
            }
        });
        Button button5;
        button=findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(),Outraging.class);
                startActivity(intent);
            }
        });
        Button button6;
        button=findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(),Harassment.class);
                startActivity(intent);
            }
        });
        Button button7;
        button=findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(),Stalking.class);
                startActivity(intent);
            }
        });
        Button button8;
        button=findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            private Object Intent;

            @Override
            public void onClick(View v) {
                android.content.Intent intent = new Intent(getApplicationContext(),Assault.class);
                startActivity(intent);
            }
        });
    }
}
