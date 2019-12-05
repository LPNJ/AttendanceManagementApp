package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import Task.ResultListener;

public class DeleteActivity extends AppCompatActivity implements ResultListener<Integer> {

    /** 新規登録用ボタン */
    private Button mOk;
    /** ログイン用ボタン */
    private Button mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


//        mRegister = findViewById(R.id.newAccount);
//        mLogin = findViewById(R.id.login);
//
//        MainActivity.MainActivityOnClickListener listener = new MainActivity.MainActivityOnClickListener();
//
//        mRegister.setOnClickListener(listener);
//        mLogin.setOnClickListener(listener);

    }

    @Override
    public void onResult(Integer result) {

        if (result == 1) {
            new AlertDialog.Builder(DeleteActivity.this)
                    .setMessage("削除に失敗しました")
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
            return;
        }
        Intent intent = new Intent(DeleteActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}

