package com.example.ojttask;

import Task.impl.EventSelectTaskImpl;
import Task.serialize.EventCreateResponse;
import Task.serialize.EventSelectResponse;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Task.EventSelectTask;
import Task.ResultListener;
import Task.mock.EventSelectTaskMock;
import entity.LoginUser;

public class EventSelectActivity extends AppCompatActivity implements ResultListener<EventSelectResponse> {
    private static final String TAG = "EveSeleAc";

    private EventSelectTask mEventSelectTask;

    /**
     * デフォルトコンストラクタ
     */
    public EventSelectActivity() {
        mEventSelectTask = new EventSelectTaskImpl(EventSelectActivity.this);
        Log.i("Menu","menu activity constructor");
    }

    void setTask(EventSelectTask task) {
        mEventSelectTask = task;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);
        Intent intent = getIntent();
        int id_info = intent.getIntExtra("screen_info", 0);
        Log.i("Menu", id_info +  "");
        mEventSelectTask.execute(LoginUser.getInstance().getLoginUserId(), EventSelectActivity.this);
    }

    @Override
    public void onResult(final EventSelectResponse result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() == 0) {
            List<String> eventNameList = result.getWorkingEventNames();

            ListView events = findViewById(R.id.event_list);

            // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,eventNameList );

            events.setAdapter(arrayAdapter);
            events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = getIntent();
                    int id_info = intent.getIntExtra("screen_info", 0);
                    Log.i("Menu", id_info +  "");
                    switch (id_info) {
                        case R.id.edit: {
                            Intent i = new Intent(EventSelectActivity.this, EventEditActivity.class);
                            i.putExtra(IntentKey.REFERENCE_EVENT,  result.getWorkingEvents().get((int) id));
                            startActivity(i);
                        }
                        break;
                        case R.id.delete: {
                            Intent i = new Intent(EventSelectActivity.this, DeleteActivity.class);
                            i.putExtra(IntentKey.REFERENCE_EVENT,  result.getWorkingEvents().get((int) id));
                            startActivity(i);
                        }
                        break;
                    }
                }
            });
        } else {
            Log.e(TAG, "サーバーからイベントの取得に失敗しました。");
        }
    }
}