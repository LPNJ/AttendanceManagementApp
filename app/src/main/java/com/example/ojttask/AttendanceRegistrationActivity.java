package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.mock.AttendanceRegistrationMock;
import entity.UserInfo;
import validator.UserLoginValidator;

public class AttendanceRegistrationActivity extends AppCompatActivity implements ResultListener<Integer> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_registration);

        AttendanceRegistrationTask task = new AttendanceRegistrationMock();
        //task.execute(new AttendanceRequest());

        class MainActivityOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.newAccount: {
//                    Intent intent = new Intent(Activity.this, RegisterActivity.class);
//                    startActivity(intent);
                    }
                    break;

                    case R.id.login: {
//                    int n = new UserLoginValidator().validate(new UserInfo(mId_info,mPass_info));
                    }
                    break;
                }
            }

//    @Override
//    public void onResult(Integer result) {
//        if (result == 1) {
//            new AlertDialog.Builder(AttendanceRegistrationActivity.this)
//                    .setMessage("ログインに失敗しました")
//                    .setPositiveButton("OK", null)
//                    .create()
//                    .show();
//            return;
//        }
//        Intent intent = new Intent(AttendanceRegistrationActivity.this, NumberingActivity.class);
//        startActivity(intent);
//    }
        }

    }

    @Override
    public void onResult(Integer integer) {

    }
}
