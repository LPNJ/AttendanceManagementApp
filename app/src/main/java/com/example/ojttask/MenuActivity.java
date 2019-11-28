package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    class MenuActivityOnClickListener_Create implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuActivity.this, CreateActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button_create = findViewById(R.id.create);
        MenuActivityOnClickListener_Create listener = new MenuActivityOnClickListener_Create();
        button_create.setOnClickListener(listener);

    }
}
