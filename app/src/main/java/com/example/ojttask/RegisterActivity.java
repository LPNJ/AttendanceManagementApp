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

import Task.RegisterTask;
import entity.EditableUserInfo;
import validator.UserRegisterValidator;

public class RegisterActivity extends AppCompatActivity {

    /** ユーザー情報新規作成用ID */
    EditText mId;
    /** ユーザー情報新規作成用PASSWARD */
    EditText mPass;
    /** 再入力用PASSWARD */
    EditText mconfirmationPass;


    private final RegisterTask mRegisterTask;

    AlertDialog mAlertDlg;

    /**
     * デフォルトコンストラクタ
     */
    public RegisterActivity() {
        super();
        mRegisterTask = new RegisterTask(this);
        Log.i("Regist","register activity contstructor");
    }

    /**
     * コンストラクタ。テスト用
     * @param registerTask
     */
    public RegisterActivity(final RegisterTask registerTask) {
        super();
        mRegisterTask = registerTask;
        Log.i("Regist","register activity contstructo has no arg");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("内容を確定しますか？")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mRegisterTask.execute();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                )
                .setNegativeButton("Cancel", null);

        mAlertDlg = builder.create();
        Button button = findViewById(R.id.registration);



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.registration:
//                        String id = ((EditText) findViewById(R.id.register_ID)).getText().toString();
//                        String pass = ((EditText) findViewById(R.id.register_PASS)).getText().toString();
//                        String confirmationPass = ((EditText) findViewById(R.id.register_COMFIRMATION_PASS)).getText().toString();
//                        int returnCode = new UserRegisterValidator().validate(new EditableUserInfo(id, pass, confirmationPass));
//                        if (returnCode == 0) {
                            mAlertDlg.show();
//                        } else {
//                            new AlertDialog.Builder(RegisterActivity.this)
//                                    .setMessage("エラー")
//                                    .setPositiveButton("OK", null)
//                                    .show();
//                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

}

