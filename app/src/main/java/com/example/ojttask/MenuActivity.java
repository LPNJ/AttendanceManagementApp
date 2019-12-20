package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import Task.mock.EventSelectTaskMock;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * デフォルトコンストラクタ
     */
    public MenuActivity() {
        Log.i("Menu","menu activity contstructor");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create: {
                    Intent intent_create = new Intent(MenuActivity.this, CreateActivity.class);
                    startActivity(intent_create);
                }
                 break;

            case R.id.edit: {
                Intent intent_edit = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_edit.putExtra("screen_info", R.id.edit);
                startActivity(intent_edit);
            }
            break;

            case R.id.decision: {
                Intent intent_decision = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_decision.putExtra("screen_info", R.id.decision);
                startActivity(intent_decision);
            }
            break;

            case R.id.delete: {
                Intent intent_Delete = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_Delete.putExtra("screen_info", R.id.delete);
                startActivity(intent_Delete);
            }
            break;

            case R.id.attend_register: {
                    Intent intent_ParticipatingEventSelect = new Intent(MenuActivity.this, ParticipatingEventSelectActivity.class);
                    startActivity(intent_ParticipatingEventSelect);
                }
                break;
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button_create = findViewById(R.id.create);
        Button button_register = findViewById(R.id.attend_register);
        Button button_edit = findViewById(R.id.edit);
        Button button_delete = findViewById(R.id.delete);
        button_delete.setOnClickListener(this);
        button_edit.setOnClickListener(this);
        button_create.setOnClickListener(this);
        button_register.setOnClickListener(this);

    }
}
