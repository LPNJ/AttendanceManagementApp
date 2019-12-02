package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Task.LoginTask;
import entity.UserInfo;
import validator.UserLoginValidator;
import validator.Validator;

/**
 * ログイン画面=メインアクティビティ
 */
public class MainActivity extends AppCompatActivity  {
    /** 新規登録用ボタン */
    Button mRegister;
    /** ログイン用ボタン */
    Button mLogin;
    /** ログイン用ID */
    EditText mId;
    /** ログイン用PASSWARD */
    EditText mPass;
    /** サーバー接続用変数 */
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

                    int n = new UserLoginValidator().validate(new UserInfo(id,pass));

                    if(n == 1){
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage("入力されていない項目があります")
                                .setPositiveButton("OK", null)
                                .create()
                                .show();
                    }
                    else {
                        mLoginTask = new LoginTask(MainActivity.this);
                        mLoginTask.execute();
                    }
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
