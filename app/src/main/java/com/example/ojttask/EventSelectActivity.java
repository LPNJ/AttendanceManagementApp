package com.example.ojttask;

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

    private final EventSelectTask mEventSelectTask;

    /**
     * デフォルトコンストラクタ
     */
    public EventSelectActivity() {
        mEventSelectTask = new EventSelectTaskMock();
        Log.i("Menu","menu activity constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_select);


        Intent intent = getIntent();
        // TODO 前画面の情報をメンバ変数に設定する
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
            // TODO resultからList<EventInfo>の情報を取得して、メンバ変数に設定する
            List<String> eventNameList = result.getWorkingEventNames();

            ListView events = findViewById(R.id.event_list);

            // simple_list_item_1 は、 もともと用意されている定義済みのレイアウトファイルのID
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,eventNameList );

            events.setAdapter(arrayAdapter);
            events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO メンバ変数に設定したList＜EventInfo＞からpositionのEventInfoを取得する
                    Intent intent = getIntent();
                    // TODO 前画面の情報をメンバ変数に設定する
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
                    // TODO 前画面の情報をもとに次の画面に遷移する
                    // TODO EventInfoからJsonを取得してintentに設定する。

                }
            });
        }
    }
}