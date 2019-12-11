package com.example.ojttask;

import Task.mock.ParticipantEventSelectTaskMock;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import Task.ResultListener;
import entity.EventInfo;
import entity.LoginUser;
import result.EventSelectResult;

/***
 * 出欠登録をするイベント選択をする画面
 */
public class ParticipatingEventSelectActivity extends AppCompatActivity implements ResultListener<EventSelectResult> {
    /** ログに出力するタグ名 */
    private static final String TAG = ParticipatingEventSelectActivity.class.getSimpleName();
    /** イベントを選択・出欠の内容を入力するタスク */
    private ParticipantEventSelectTaskMock mEventSelectTask;
    /** 確定ボタン */
    private Button mRegistration_participating;

    /**
     * デフォルトコンストラクタ
     */
    public ParticipatingEventSelectActivity() {
        mEventSelectTask = new ParticipantEventSelectTaskMock();
        Log.i(TAG, "activity constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_event_select);
        mEventSelectTask.execute(LoginUser.getInstance().getLoginUserId(), ParticipatingEventSelectActivity.this);
        mRegistration_participating = findViewById(R.id.registration_participating);
        mRegistration_participating.setOnClickListener(new ParticipatingEventSelectActivityOnClickListener());
    }

    @Override
    public void onResult(final EventSelectResult result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() != 0) {
            return;
        }
        List<String> eventNameList = result.getEventNameList();
        ListView events = findViewById(R.id.event_list_participating);
        // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNameList);
        events.setAdapter(arrayAdapter);
        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ParticipatingEventSelectActivity.this, AttendanceRegistrationActivity.class);
                intent.putExtra(IntentKey.REFERENCE_EVENT,  result.getEventInfoList().get((int) l));
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
                    Intent intent = new Intent(ParticipatingEventSelectActivity.this, AttendanceRegistrationActivity.class);
                    //intent.putExtra(IntentKey.REFERENCE_EVENT, );
                    startActivity(intent);
                }
                break;
            }
        }
    }
}
