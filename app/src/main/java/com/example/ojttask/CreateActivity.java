package com.example.ojttask;

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
import result.EventCreateResult;

public class CreateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerFragment.TimePickerlistener , ResultListener<EventCreateResult> {
    /** 日付を入力するテキストビューのID一覧 */
    private static final List<Integer> DATE_TEXTVIEW_ID_LIST = Collections.unmodifiableList(Arrays.asList(
            new Integer[] {
                    R.id.Date1_Create, R.id.Date2_Create, R.id.Date3_Create, R.id.Date4_Create, R.id.Date5_Create,
                    R.id.Date6_Create, R.id.Date7_Create, R.id.Date8_Create, R.id.Date9_Create, R.id.Date10_Create,
            }
    ));
    /** 時間を入力するテキストビューのID一覧 */
    private static final List<Integer> TIME_TEXTVIEW_ID_LIST = Collections.unmodifiableList(Arrays.asList(
            new Integer[] {
                    R.id.Time1_Create, R.id.Time2_Create, R.id.Time3_Create, R.id.Time4_Create, R.id.Time5_Create,
                    R.id.Time6_Create, R.id.Time7_Create, R.id.Time8_Create, R.id.Time9_Create, R.id.Time10_Create,
            }
    ));
    private TextView mDisplayDate[] = new TextView[10];
    private TextView mDisplayTime[] = new TextView[10];

    private static final String DATE_PICKER = "date picker";
    private static final String TIME_PICKER = "time picker";

    private final EventCreateTask mEventCreateTask;

    /** 現在操作しているテキストビューのID */
    private int mTargetTextViewId;
    /**
     * デフォルトコンストラクタ
     */
    public CreateActivity() {
        super();
        mEventCreateTask = new EventCreateTaskMock(/*this*/);
        Log.i("Regist","register activity constructor");
    }


    private Button mBottun_registration_create;

    /** 候補日のリスト */
    private List<CandidateDate> dates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        CreateActivityOnClickListener listener = new CreateActivityOnClickListener();
        for (int i = 0; i < DATE_TEXTVIEW_ID_LIST.size(); ++i) {
            mDisplayDate[i] = findViewById(DATE_TEXTVIEW_ID_LIST.get(i));
            mDisplayDate[i].setOnClickListener(listener);
            mDisplayTime[i] = findViewById(TIME_TEXTVIEW_ID_LIST.get(i));
            mDisplayTime[i].setOnClickListener(listener);
        }
        mBottun_registration_create = findViewById(R.id.registration_create);
        mBottun_registration_create.setOnClickListener(listener);
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
    public void onResult(EventCreateResult result) {
        if (result == null) {
            throw new IllegalArgumentException("result is null");
        }
        if (result.getError() == 0) {
            Intent intent = new Intent(CreateActivity.this, NumberingActivity.class);
            intent.putExtra(IntentKey.EVENT_NUMBER, result.getEventId());
            startActivity(intent);
        }
    }

    private class CreateActivityOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Date1_Create:
                    setDate(0);
                    break;
                case R.id.Date2_Create:
                    setDate(1);
                    break;
                case R.id.Date3_Create:
                    setDate(2);
                    break;
                case R.id.Date4_Create:
                    setDate(3);
                    break;
                case R.id.Date5_Create:
                    setDate(4);
                    break;
                case R.id.Date6_Create:
                    setDate(5);
                    break;
                case R.id.Date7_Create:
                    setDate(6);
                    break;
                case R.id.Date8_Create:
                    setDate(7);
                    break;
                case R.id.Date9_Create:
                    setDate(8);
                    break;
                case R.id.Date10_Create:
                    setDate(9);
                    break;
                case R.id.Time1_Create:
                    setTime(0);
                    break;
                case R.id.Time2_Create:
                    setTime(1);
                    break;
                case R.id.Time3_Create:
                    setTime(2);
                    break;
                case R.id.Time4_Create:
                    setTime(3);
                    break;
                case R.id.Time5_Create:
                    setTime(4);
                    break;
                case R.id.Time6_Create:
                    setTime(5);
                    break;
                case R.id.Time7_Create:
                    setTime(6);
                    break;
                case R.id.Time8_Create:
                    setTime(7);
                    break;
                case R.id.Time9_Create:
                    setTime(8);
                    break;
                case R.id.Time10_Create:
                    setTime(9);
                    break;
                case R.id.registration_create: {
                    EventInfo ei = new EventInfo("","",EventInfo.UNDEFINED_EVENT_NUMBER, dates);
                    mEventCreateTask.execute(new EventCreateRequest("",ei), CreateActivity.this);
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
    }
}



