package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Task.LoginTask;

public class MenuActivity extends AppCompatActivity {

    class MenuActivityOnClickListener_Create implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.create: {
                    Intent intent = new Intent(MenuActivity.this, CreateActivity.class);
                    startActivity(intent);
                }
                break;

                case R.id.register: {
                    Intent intent2 = new Intent(MenuActivity.this, ParticipatingEventSelectActivity.class);
                    startActivity(intent2);
                }
                break;


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button_create = findViewById(R.id.create);
        Button button_register = findViewById(R.id.register);
        MenuActivityOnClickListener_Create listener = new MenuActivityOnClickListener_Create();
        button_create.setOnClickListener(listener);
        button_register.setOnClickListener(listener);

    }
}
