package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import Picker.DatePickerFragment;
import Picker.TimePickerFragment;
import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.mock.EventSelectTaskMock;
import entity.EventCreateRequest;
import entity.EventInfo;
import entity.UserID;
import result.EventSelectResult;

public class ParticipatingEventSelectActivity extends AppCompatActivity implements ResultListener<EventSelectResult> {

    private EventSelectTaskMock mEventSelectTask;

    /** 確定ボタン */
    private Button mRegistration_participating;

    /**
     * デフォルトコンストラクタ
     */
    public ParticipatingEventSelectActivity() {
        mEventSelectTask = new EventSelectTaskMock();
        Log.i("Menu","menu activity contstructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_event_select);

        UserID id = new UserID();
        id.setUserID("komi");
        mEventSelectTask.execute(id.getUserId(), ParticipatingEventSelectActivity.this);

        class ParticipatingEventSelectActivityOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.registration_participating: {
                    Intent intent = new Intent(ParticipatingEventSelectActivity.this, AttendanceRegistrationActivity.class);
                    startActivity(intent);
                }
                break;
            }
        }
    }
        mRegistration_participating = findViewById(R.id.registration_participating);
        ParticipatingEventSelectActivityOnClickListener listener = new ParticipatingEventSelectActivityOnClickListener();
        mRegistration_participating.setOnClickListener(listener);
    }

    @Override
    public void onResult(EventSelectResult result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() == 0) {

            List<String> eventNameList = result.getEventNameList();

            ListView events = findViewById(R.id.event_list_participating);

            // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,eventNameList );

            events.setAdapter(arrayAdapter);
        }
    }
}
