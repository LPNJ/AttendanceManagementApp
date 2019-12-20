package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import Task.RegisterTask;
import Task.impl.RegisterTaskImpl;
import Task.mock.RegisterTaskMock;
import Task.ResultListener;
import entity.EditableUserInfo;
import validator.UserRegisterValidator;

import static validator.UserRegisterValidator.ERROR_ID_TOO_LONG;
import static validator.UserRegisterValidator.ERROR_ID_TOO_SHORT;
import static validator.UserRegisterValidator.ERROR_INVALID_CHARACTER;
import static validator.UserRegisterValidator.ERROR_NOT_INPUT;
import static validator.UserRegisterValidator.ERROR_PASS_NOT_MATCH;
import static validator.UserRegisterValidator.ERROR_PASS_TOO_LONG;
import static validator.UserRegisterValidator.ERROR_PASS_TOO_SHORT;

public class RegisterActivity extends AppCompatActivity implements ResultListener<Integer>, View.OnClickListener {

    /**
     * ユーザー情報新規作成用ID
     */
    private EditText mId;
    /**
     * ユーザー情報新規作成用PASSWARD
     */
    private EditText mPass;
    /**
     * 再入力用PASSWARD
     */
    private EditText mconfirmationPass;
    /**
     * ユーザーIDデータ保持用
     */
    private String mId_info;
    /**
     * ユーザーPASSWARDデータ保持用
     */
    private String mPass_info;
    /**
     * 再入力用PASSWARD
     */
    private String mConfirmationPass_info;

    private RegisterTask mRegisterTask;

    AlertDialog mAlertDlg;

    private final Map<Integer, Integer> mErrorMessage = new HashMap<>();


    /**
     * デフォルトコンストラクタ
     */
    public RegisterActivity() {
        super();
        mRegisterTask = new RegisterTaskImpl(RegisterActivity.this);
        mErrorMessage.put(-1, R.string.cannot_connect);
        mErrorMessage.put(1, R.string.cannot_register_error);
        Log.i("Regist", "register activity contstructor");
    }

    /**
     * MT用
     * @param task
     */
    public void setTask(RegisterTask task) {
        mRegisterTask = task;
    }

    /**
     * コンストラクタ。テスト用
     *
     * @param registerTask
     */
    public RegisterActivity(final RegisterTask registerTask) {
        super();
        mRegisterTask = registerTask;
        Log.i("Regist", "register activity contstructo has no arg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button = findViewById(R.id.registration);
        button.setOnClickListener(this);
    }

    void show(int msg){
        new AlertDialog.Builder(RegisterActivity.this)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registration:
                mId_info = ((EditText) findViewById(R.id.register_ID)).getText().toString();
                mPass_info = ((EditText) findViewById(R.id.register_PASS)).getText().toString();
                mConfirmationPass_info = ((EditText) findViewById(R.id.register_COMFIRMATION_PASS)).getText().toString();
                int returnCode = new UserRegisterValidator().validate(new EditableUserInfo(mId_info, mPass_info, mConfirmationPass_info));
                Log.i("regAc" , "returncode = "+ returnCode);
                if (returnCode == 0) {
                    new AlertDialog.Builder(this)
                            .setMessage(R.string.decision)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mRegisterTask.execute(new EditableUserInfo(mId_info, mPass_info, mConfirmationPass_info), RegisterActivity.this);
                                }
                            })
                            .setNegativeButton("Cancel", null).show();
                } else if (returnCode == ERROR_NOT_INPUT) {
                    show(R.string.login_error);
                } else if (returnCode == ERROR_INVALID_CHARACTER) {
                    show(R.string.Not_allowed_error);
                } else if (returnCode == ERROR_ID_TOO_LONG) {
                    show(R.string.id_input_long);
                } else if (returnCode == ERROR_PASS_TOO_LONG) {
                    show(R.string.pass_input_long);
                } else if (returnCode == ERROR_ID_TOO_SHORT) {
                    show(R.string.id_input_short);
                } else if (returnCode == ERROR_PASS_TOO_SHORT) {
                    show(R.string.pass_input_short);
                } else if (returnCode == ERROR_PASS_NOT_MATCH) {
                    show(R.string.pass_not_match);
                }
                    default:
                        break;
                }
        }
    @Override
    public void onResult(Integer result) {
        if (result != 0) {
            show(mErrorMessage.get(result));
            return;
        }
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}