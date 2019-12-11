package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import Task.ResultListener;
import Task.mock.DeleteTaskMock;
import Task.mock.EventCreateTaskMock;
import Task.serialize.DeleteEventRequest;
import entity.CandidateDate;
import entity.EventInfo;
import entity.LoginUser;

public class DeleteActivity extends AppCompatActivity implements ResultListener<Integer> , View.OnClickListener {
    // TODO DeleteTask を宣言する

    /** 新規登録用ボタン */
    private Button mOk;
    /** ログイン用ボタン */
    private Button mCancel;

    private TextView mEventName;

    private EventInfo mEventInfo;

    private DeleteTaskMock mDeleteTask;

    /**
     * デフォルトコンストラクタ
     */
    public DeleteActivity() {
        mDeleteTask = new DeleteTaskMock(/*this*/);
        Log.i("Regist","register activity constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Serializable data = getIntent().getSerializableExtra(IntentKey.REFERENCE_EVENT);
        if (data instanceof EventInfo) {
            mEventInfo = (EventInfo) data;
        } else {
            throw new IllegalArgumentException("data is illegal type");
        }

        mEventName = (TextView) findViewById(R.id.event_name_delete);
        mEventName.setText(mEventInfo.getEventName());

        mOk = (Button) findViewById(R.id.ok_delete);
        mCancel = (Button) findViewById(R.id.cancel_delete);
        mOk.setOnClickListener(this);
        mCancel.setOnClickListener(this);

    }

    @Override
    public void onResult(Integer result) {
        if (result == 1) {
            new AlertDialog.Builder(DeleteActivity.this)
                    .setMessage("削除に失敗しました")
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
            return;
        }
        Intent intent = new Intent(DeleteActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ok_delete: {
                // TODO DeleteTaskのexecuteを実施する,
                LoginUser luser = LoginUser.getInstance();
                mDeleteTask.execute(new DeleteEventRequest(luser.getLoginUserId(),mEventInfo.getEventId()), DeleteActivity.this);
                // 引数に指定するEventIdはmEventInfoから取得
                // UserIdはLoginUserクラスから取得

            }
            break;

            case R.id.cancel_delete: {
                Intent intent = new Intent(this, EventSelectActivity.class);
                startActivity(intent);
            }
            break;
        }

    }
}

