package com.example.ojttask;

import Task.LogoutTask;
import Task.ResultListener;
import Task.impl.LogoutTaskImpl;
import Task.mock.LogoutTaskMock;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import Task.mock.EventSelectTaskMock;
import entity.EditableUserInfo;
import entity.LoginUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, ResultListener<Integer> {
    private final LogoutTask mLogoutTask;

    /**
     * デフォルトコンストラクタ
     */
    public MenuActivity() {
        mLogoutTask = new LogoutTaskMock();
        Log.i("Menu","menu activity contstructor");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create: {
                Intent intent_create = new Intent(MenuActivity.this, CreateActivity.class);
                startActivity(intent_create);
                break;
            }
            case R.id.edit: {
                Intent intent_edit = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_edit.putExtra("screen_info", R.id.edit);
                startActivity(intent_edit);
                break;
            }
            case R.id.decision: {
                Intent intent_decision = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_decision.putExtra("screen_info", R.id.decision);
                startActivity(intent_decision);
                break;
            }
            case R.id.delete: {
                Intent intent_Delete = new Intent(MenuActivity.this, EventSelectActivity.class);
                intent_Delete.putExtra("screen_info", R.id.delete);
                startActivity(intent_Delete);
                break;
            }
            case R.id.attend_register: {
                Intent intent_ParticipatingEventSelect = new Intent(MenuActivity.this, ParticipatingEventSelectActivity.class);
                startActivity(intent_ParticipatingEventSelect);
                break;
            }
            case R.id.notice: {
//                Intent intent_notice = new Intent(MenuActivity.this, Activity.class);
//                startActivity(intent_notice);
//                break;
            }
            case R.id.logout: {
                new AlertDialog.Builder(this)
                        .setMessage(R.string.logout)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mLogoutTask.execute(LoginUser.getInstance().getLoginUserId(), MenuActivity.this);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button createButton = findViewById(R.id.create);
        Button registerButton = findViewById(R.id.attend_register);
        Button editButton = findViewById(R.id.edit);
        Button deleteButton = findViewById(R.id.delete);
        Button logoutButton = findViewById(R.id.logout);
        deleteButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public void onResult(Integer result) {
        if (result != 0) {
            new AlertDialog.Builder(this)
                    .setMessage("ログアウトに失敗しました。") // TODO メッセージ追加
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
