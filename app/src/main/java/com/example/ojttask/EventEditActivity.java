package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import Task.EventEditTask;
import Task.ResultListener;
import entity.EventInfo;

public class EventEditActivity extends AppCompatActivity implements ResultListener<Integer> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
    }


    @Override
    public void onResult(Integer result) {

        if (result == 1) {
            new AlertDialog.Builder(EventEditActivity.this)
                    .setMessage("ログインに失敗しました")
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
            return;
        }
        Intent intent = new Intent(EventEditActivity.this, MenuActivity.class);
        startActivity(intent);

    }
}
