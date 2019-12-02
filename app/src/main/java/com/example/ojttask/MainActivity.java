package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Task.LoginTask;

/**
 * ログイン画面=メインアクティビティ
 */
public class MainActivity extends AppCompatActivity {
    /** 新規登録用ボタン */
    Button mRegister;
    /** ログイン用ボタン */
    Button mLogin;
    /** ログイン用ID */
    EditText mId;
    /** ログイン用PASSWARD */
    EditText mPass;

    private LoginTask mLoginTask;

    class MainActivityOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.newAccount: {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
                break;

                case R.id.login: {

                    mId = findViewById(R.id.editText_ID);
                    mPass = findViewById(R.id.editText_PASS);

                    String id = mId.getText().toString();
                    String pass = mPass.getText().toString();

                    mLoginTask = new LoginTask(MainActivity.this);
                    mLoginTask.execute();
                    //Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    //startActivity(intent);
                }
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegister = findViewById(R.id.newAccount);
        mLogin = findViewById(R.id.login);

        MainActivityOnClickListener listener = new MainActivityOnClickListener();

        mRegister.setOnClickListener(listener);
        mLogin.setOnClickListener(listener);
    }

}
