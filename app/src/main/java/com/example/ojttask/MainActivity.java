package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Task.LoginTask;
import Task.ResultListener;
import Task.impl.LoginTaskImpl;
import Task.mock.LoginTaskMock;
import entity.LoginUser;
import entity.UserInfo;
import validator.UserLoginValidator;

/**
 * ログイン画面=メインアクティビティ
 */
public class MainActivity extends AppCompatActivity implements ResultListener<Integer> {
    /** 新規登録用ボタン */
    private Button mRegister;
    /** ログイン用ボタン */
    private Button mLogin;
    /** ログイン用ID */
    private EditText mId;
    /** ログイン用PASSWARD */
    private EditText mPass;
    /** サーバー接続用変数 */
    private LoginTask mLoginTask;
    /** ログインIDデータ保持用 */
    String mId_info;
    /** ログイン用PASSWARDデータ保持用 */
    String mPass_info;

    /**
     * デフォルトコンストラクタ
     */
    public MainActivity() {
        super();
        mLoginTask = new LoginTaskImpl(MainActivity.this);
    }

    /**
     * MT用
     * @param task
     */
    public void setTask(LoginTask task) {
        mLoginTask = task;
    }

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
                    Log.i("LOGIN","SUCCESS");
                    mId_info = mId.getText().toString();
                    mPass_info = mPass.getText().toString();
                    int validationResult = new UserLoginValidator().validate(new UserInfo(mId_info,mPass_info));
                    if(validationResult == 1){
                        new AlertDialog.Builder(MainActivity.this)
                                .setMessage(R.string.login_error)
                                .setPositiveButton(R.string.ok, null)
                                .create()
                                .show();
                    } else {
                        LoginUser loginUser = LoginUser.getInstance();
                        loginUser.setLoginUserId(mId_info);
                        mLoginTask.execute(new UserInfo(mId_info, mPass_info),MainActivity.this);
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

        mId = findViewById(R.id.editText_ID);
        mPass = findViewById(R.id.editText_PASS);
        mRegister = findViewById(R.id.newAccount);
        mLogin = findViewById(R.id.login);

        MainActivityOnClickListener listener = new MainActivityOnClickListener();

        mRegister.setOnClickListener(listener);
        mLogin.setOnClickListener(listener);

    }

    @Override
    public void onResult(Integer result) {
        if (result == 21) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(R.string.cannot_login)
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
            return;
        }
        else if(result == 0){
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }
        else{
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(R.string.cannot_connect)
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
            return;
        }
    }
}


