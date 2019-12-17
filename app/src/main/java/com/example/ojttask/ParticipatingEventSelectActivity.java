package com.example.ojttask;

import Task.EventSelectTask;
import Task.ParticipantEventTask;
import Task.mock.ParticipantEventSelectTaskMock;
import Task.serialize.EventCreateRequest;
import Task.serialize.EventSelectResponse;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import Task.ResultListener;
import entity.CandidateDate;
import entity.EventInfo;
import entity.LoginUser;
import validator.EventRegistVaridator;

/***
 * 出欠登録をするイベント選択をする画面
 */
public class ParticipatingEventSelectActivity extends AppCompatActivity implements ResultListener<EventSelectResponse> {
    /** ログに出力するタグ名 */
    private static final String TAG = ParticipatingEventSelectActivity.class.getSimpleName();
    /** イベントを選択・出欠の内容を入力するタスク */
    private ParticipantEventTask mEventSelectTask;
    /** 確定ボタン */
    private Button mRegistration_participating;

    /** 確定ボタン */
    private EditText mEventId;

    /**
     * デフォルトコンストラクタ
     */
    public ParticipatingEventSelectActivity() {
        mEventSelectTask = new ParticipantEventSelectTaskMock();
        Log.i(TAG, "activity constructor");
    }

    void setParticipantEventTask(ParticipantEventTask task) {
        mEventSelectTask = task;
    }

    void setEventSelectTask(ParticipantEventTask task) {
        mEventSelectTask = task;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_event_select);
        mEventSelectTask.execute(LoginUser.getInstance().getLoginUserId(), ParticipatingEventSelectActivity.this);
        mEventId = findViewById(R.id.eventid_participating);
        mRegistration_participating = findViewById(R.id.registration_participating);
        mRegistration_participating.setOnClickListener(new ParticipatingEventSelectActivityOnClickListener());

    }

    @Override
    public void onResult(final EventSelectResponse result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() != 0) {
            return;
        }
        List<String> eventNameList = result.getWorkingEventNames();
        ListView events = findViewById(R.id.event_list_participating);
        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNameList);
        events.setAdapter(arrayAdapter);
        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ParticipatingEventSelectActivity.this, AttendanceRegistrationActivity.class);
                intent.putExtra(IntentKey.REFERENCE_EVENT,  result.getWorkingEvents().get((int) l));
                startActivity(intent);
            }
        });
    }

    /**
     * この画面の操作リスナ
     */
    private class ParticipatingEventSelectActivityOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.registration_participating: {
                int returnCode = new EventRegistVaridator().validate(mEventId.getText().toString());
                    if (returnCode == 0) {
                        new AlertDialog.Builder(ParticipatingEventSelectActivity.this)
                                .setMessage(R.string.decision)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO イベントIDからイベント取得する必要がある。
                                        Intent intent = new Intent(ParticipatingEventSelectActivity.this, AttendanceRegistrationActivity.class);
                                        //intent.putExtra(IntentKey.REFERENCE_EVENT, );
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    } else if (returnCode == 4) {
                        show(R.string.eventid_no_exist);
                    }
                }
                break;
            }
        }
        void show(int msg){
            new AlertDialog.Builder(ParticipatingEventSelectActivity.this)
                    .setMessage(msg)
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
        }
    }
}
