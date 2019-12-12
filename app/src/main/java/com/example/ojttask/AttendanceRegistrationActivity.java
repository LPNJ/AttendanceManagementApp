package com.example.ojttask;

import Task.serialize.AttendanceRequest;
import Task.serialize.EventCreateRequest;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.mock.AttendanceRegistrationMock;
import entity.AttendanceInfo;
import entity.AttendanceType;
import entity.CandidateDate;
import entity.EventInfo;
import entity.LoginUser;

/**
 * 出欠登録画面
 */
public class AttendanceRegistrationActivity extends AppCompatActivity implements ResultListener<Integer>, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = AttendanceRegistrationActivity.class.getSimpleName();
    private EventInfo mEventInfo;
    private CandidateDate mTargetDate;
    private final AttendanceRegistrationTask mAttendanceRegistrationTask;
    private AdapterView.OnItemSelectedListener mItemClickListener =  new AdapterView.OnItemSelectedListener() {
        //　アイテムが選択された時
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner)parent;
            String item = (String)spinner.getSelectedItem();
            Log.i(TAG, item);
            for(CandidateDate candidate : mEventInfo.getCandidateDates()){
                if(item.equals(candidate.getDateAndTime())) {
                    mTargetDate = candidate;
                }
            }
        }
        //　アイテムが選択されなかった
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // 特に必要がないので、実装しない
        }
    };

    /**
     * デフォルトコンストラクタ
     */
    public AttendanceRegistrationActivity() {
        super();
        mAttendanceRegistrationTask = new AttendanceRegistrationMock();
    }

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
        Spinner spinner = findViewById(R.id.date_spinner);
        // ArrayAdapter
        List<String> dateTimeList = new ArrayList<>();
        for(CandidateDate c : mEventInfo.getCandidateDates()){
            dateTimeList.add(c.getDateAndTime());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateTimeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner に adapter をセット
        spinner.setAdapter(adapter);
        // リスナーを登録
        spinner.setOnItemSelectedListener(mItemClickListener);
        AttendanceRegistrationActivityOnClickListener listener = new AttendanceRegistrationActivityOnClickListener();
        ((RadioGroup)findViewById(R.id.radio_group)).setOnCheckedChangeListener(this);
        findViewById(R.id.event_attendance_reference).setOnClickListener(listener);
        findViewById(R.id.event_attendance_decision).setOnClickListener(listener);
        AttendanceRegistrationTask task = new AttendanceRegistrationMock();
        //task.execute(new AttendanceRequest());
    }

    @Override
    public void onResult(Integer errorCode) {
        if (errorCode != 0) {
            // なんかエラー処理
            return;
        }
        Intent intent = new Intent(AttendanceRegistrationActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.i(TAG, String.valueOf(checkedId));
        String userName = ((EditText)findViewById(R.id.event_attendance_name)).getText().toString();
        switch (checkedId){
            case R.id.radio_attendance:
                mTargetDate.addAttendance(new AttendanceInfo(userName, AttendanceType.ATTENDANCE));
                break;
            case R.id.radio_unknown:
                mTargetDate.addAttendance(new AttendanceInfo(userName, AttendanceType.UNKNOWN));
                break;
            case R.id.radio_absence:
                mTargetDate.addAttendance(new AttendanceInfo(userName, AttendanceType.ABSENCE));
                break;
            default:
                Log.w(TAG, "No item checked");
                break;
        }
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
                    mAttendanceRegistrationTask.execute(new AttendanceRequest(LoginUser.getInstance().getLoginUserId(), mEventInfo), AttendanceRegistrationActivity.this);
                    break;
                }
            }
        }
    }
}
