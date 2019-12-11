package com.example.ojttask;

import Task.serialize.EventCreateRequest;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.mock.AttendanceRegistrationMock;
import entity.AttendanceType;
import entity.EventInfo;
import entity.UserInfo;
import validator.UserLoginValidator;

/**
 * 出欠登録画面
 */
public class AttendanceRegistrationActivity extends AppCompatActivity implements ResultListener<Integer> {
    private EventInfo mEventInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_registration);
        Serializable data = getIntent().getSerializableExtra(IntentKey.REFERENCE_EVENT);
        if (data instanceof EventInfo) {
            mEventInfo = (EventInfo) data;
        } else {
            throw new IllegalArgumentException("data is illegal type");
        }
        AttendanceRegistrationActivityOnClickListener listener = new AttendanceRegistrationActivityOnClickListener();
        findViewById(R.id.event_attendance_reference).setOnClickListener(listener);
        findViewById(R.id.event_attendance_decision).setOnClickListener(listener);
        AttendanceRegistrationTask task = new AttendanceRegistrationMock();
        //task.execute(new AttendanceRequest());
    }

    @Override
    public void onResult(Integer integer) {

    }

    private class AttendanceRegistrationActivityOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.event_attendance_reference:
                    Intent intent = new Intent(AttendanceRegistrationActivity.this, ReferenceActivity.class);
                    intent.putExtra(IntentKey.REFERENCE_EVENT, mEventInfo);
                    startActivity(intent);
                    break;
                case R.id.event_attendance_decision: {
                    break;
                }
            }
        }
    }
}
