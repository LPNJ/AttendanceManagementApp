package com.example.ojttask;

import Task.serialize.EventCreateResponse;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import Picker.DatePickerFragment;
import Picker.TimePickerFragment;
import Task.EventCreateTask;
import Task.ResultListener;
import Task.mock.EventCreateTaskMock;
import Task.serialize.EventCreateRequest;
import entity.CandidateDate;
import entity.EventInfo;
import entity.LoginUser;

public class EventEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerFragment.TimePickerlistener , ResultListener<EventCreateResponse>{
    /** 時間を入力するテキストビューのID一覧 */
    private static final List<Integer> TIME_TEXTVIEW_ID_LIST = Collections.unmodifiableList(Arrays.asList(
            new Integer[] {
                    R.id.Time1_Edit, R.id.Time2_Edit, R.id.Time3_Edit, R.id.Time4_Edit, R.id.Time5_Edit,
                    R.id.Time6_Edit, R.id.Time7_Edit, R.id.Time8_Edit, R.id.Time9_Edit, R.id.Time10_Edit,
            }
    ));
    private TextView mDisplayDate[] = new TextView[10];
    /** 日付を入力するテキストビューのID一覧 */
    private static final List<Integer> DATE_TEXTVIEW_ID_LIST = Collections.unmodifiableList(Arrays.asList(
            new Integer[] {
                    R.id.Date1_Edit, R.id.Date2_Edit, R.id.Date3_Edit, R.id.Date4_Edit, R.id.Date5_Edit,
                    R.id.Date6_Edit, R.id.Date7_Edit, R.id.Date8_Edit, R.id.Date9_Edit, R.id.Date10_Edit,
            }
    ));
    private TextView mDisplayTime[] = new TextView[10];

    private static final String DATE_PICKER = "date picker";
    private static final String TIME_PICKER = "time picker";

    private EventCreateTask mEventCreateTask;

    /** 現在操作しているテキストビューのID */
    private int mTargetTextViewId;
    /** 編集中のイベント */
    private EventInfo mEventInfo;

    /**
     * デフォルトコンストラクタ
     */
    public EventEditActivity() {
        super();
        mEventCreateTask = new EventCreateTaskMock(/*this*/);
        Log.i("Regist","register activity constructor");
    }

    void setTask(EventCreateTask task) {
        mEventCreateTask = task;
    }
    private Button mBottun_registration_edit;

    /** 候補日のリスト */
    private List<CandidateDate> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Serializable data = getIntent().getSerializableExtra(IntentKey.REFERENCE_EVENT);
        if (data instanceof EventInfo) {
            mEventInfo = (EventInfo) data;
        } else {
            throw new IllegalArgumentException("data is illegal type");
        }

        EventEditActivity.EventEditActivityOnClickListener listener = new EventEditActivity.EventEditActivityOnClickListener();
        List<CandidateDate> candidates = mEventInfo.getCandidateDates();

        for (int i = 0; i < DATE_TEXTVIEW_ID_LIST.size(); ++i) {
            mDisplayDate[i] = findViewById(DATE_TEXTVIEW_ID_LIST.get(i));
            mDisplayDate[i].setOnClickListener(listener);
            mDisplayTime[i] = findViewById(TIME_TEXTVIEW_ID_LIST.get(i));
            mDisplayTime[i].setOnClickListener(listener);
            // 対象のテキストボックスに登録データがない
            if (i < candidates.size()) {
                mDisplayDate[i].setText(candidates.get(i).getDate());
                mDisplayTime[i].setText(candidates.get(i).getTime());
            }
        }
        mBottun_registration_edit = findViewById(R.id.registration_edit);
        mBottun_registration_edit.setOnClickListener(listener);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        mDisplayDate[mTargetTextViewId].setText(month + 1 + "/" + dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR,hour);
        c.set(Calendar.MINUTE,minute);
        mDisplayTime[mTargetTextViewId].setText(hour + "：" + minute);
    }

    @Override
    public void onResult(EventCreateResponse result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() == 0) {
            Intent intent = new Intent(EventEditActivity.this, MenuActivity.class);
            intent.putExtra(IntentKey.EVENT_NUMBER, result.getEventId());
            startActivity(intent);
        }
    }

    private class EventEditActivityOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Date1_Edit:
                    setDate(0);
                    break;
                case R.id.Date2_Edit:
                    setDate(1);
                    break;
                case R.id.Date3_Edit:
                    setDate(2);
                    break;
                case R.id.Date4_Edit:
                    setDate(3);
                    break;
                case R.id.Date5_Edit:
                    setDate(4);
                    break;
                case R.id.Date6_Edit:
                    setDate(5);
                    break;
                case R.id.Date7_Edit:
                    setDate(6);
                    break;
                case R.id.Date8_Edit:
                    setDate(7);
                    break;
                case R.id.Date9_Edit:
                    setDate(8);
                    break;
                case R.id.Date10_Edit:
                    setDate(9);
                    break;
                case R.id.Time1_Edit:
                    setTime(0);
                    break;
                case R.id.Time2_Edit:
                    setTime(1);
                    break;
                case R.id.Time3_Edit:
                    setTime(2);
                    break;
                case R.id.Time4_Edit:
                    setTime(3);
                    break;
                case R.id.Time5_Edit:
                    setTime(4);
                    break;
                case R.id.Time6_Edit:
                    setTime(5);
                    break;
                case R.id.Time7_Edit:
                    setTime(6);
                    break;
                case R.id.Time8_Edit:
                    setTime(7);
                    break;
                case R.id.Time9_Edit:
                    setTime(8);
                    break;
                case R.id.Time10_Edit:
                    setTime(9);
                    break;
                case R.id.registration_edit: {
                    updateEventInfo();
                    mEventCreateTask.execute(new EventCreateRequest(LoginUser.getInstance().getLoginUserId(), mEventInfo), EventEditActivity.this);
                    for(CandidateDate date : dates){
                        Log.i("DATE", date.getDateAndTime());
                    }
                }
                break;
            }
        }

        private void setDate(int id) {
            mTargetTextViewId = id;
            new DatePickerFragment().show(getSupportFragmentManager(), DATE_PICKER);
        }

        private void setTime(int id) {
            DialogFragment timepicker = new TimePickerFragment();
            timepicker.setCancelable(false);
            timepicker.show(getSupportFragmentManager(),TIME_PICKER);
        }

        /**
         * 編集対象のイベント情報を更新します
         */
        private void updateEventInfo() {
            List<String> dateTime = new ArrayList<>();
            for (int i = 0; i < DATE_TEXTVIEW_ID_LIST.size(); ++i) {
                String date = mDisplayDate[i].getText().toString();
                String time = mDisplayTime[i].getText().toString();
                dateTime.add(date.trim() + " " + time.trim());
                if (date == null || time == null || date.isEmpty() || time.isEmpty()) {
                    // TODO 空だったらエラー（Validation)
                    continue;
                }
                CandidateDate candidate = mEventInfo.contains(date, time);
                if (candidate == null) {
                    mEventInfo.addCandidateDate(new CandidateDate(date, time));
                }
            }
            mEventInfo.removeCandidates(dateTime);
        }
    }

}
