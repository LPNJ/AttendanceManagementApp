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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Picker.DatePickerFragment;
import Picker.TimePickerFragment;
import Task.EventCreateTask;
import Task.ResultListener;
import Task.mock.EventCreateTaskMock;
import Task.serialize.EventCreateRequest;
import entity.EventInfo;
import result.EventCreateResult;

public class CreateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerFragment.TimePickerlistener , ResultListener<EventCreateResult> {

    private final EventCreateTask mEventCreateTask;

    /**
     * デフォルトコンストラクタ
     */
    public CreateActivity() {
        super();
        mEventCreateTask = new EventCreateTaskMock(/*this*/);
        Log.i("Regist","register activity contstructor");
    }

    private TextView mDisplayDate[] = new TextView[10];
    private TextView mDisplayTime[] = new TextView[10];

    private Button mBottun_registration_create;

    private TextView DisplayDate;
    private TextView DisplayDate2;

    private TextView DisplayTime;
    private TextView DisplayTime2;

    /** 日付のリスト */
    private List<String> dates = new ArrayList<>();
    /** 時間のリスト */
    private List<String> times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        class CreateActivityOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.Date1_Create: {
                        DialogFragment datepicker = new DatePickerFragment();
                        setText(mDisplayDate[0]);
                        datepicker.show(getSupportFragmentManager(),"date picker");

                   }
                    break;

                  case R.id.Time1_Create: {
                      DialogFragment timepicker = new TimePickerFragment();
                      setText2(mDisplayTime[0]);
                      timepicker.setCancelable(false);
                      timepicker.show(getSupportFragmentManager(),"time picker");
                   }
                    break;

                    case R.id.registration_create: {
                        //EventInfo ei = new EventInfo("","",dates,times, EventInfo.UNDEFINED_EVENT_NUMBER);
                        EventInfo ei = new EventInfo("","",EventInfo.UNDEFINED_EVENT_NUMBER,dates);
                        mEventCreateTask.execute(new EventCreateRequest("",ei, EventCreateRequest.EventType.CREATE), CreateActivity.this);
                        for(String date : dates){
                            Log.i("DATE", date);
                        }
                    }

                    break;
                }

            }
        }

        mDisplayDate[0] = findViewById(R.id.Date1_Create);
        CreateActivityOnClickListener listener = new CreateActivityOnClickListener();
        mDisplayDate[0].setOnClickListener(listener);

        mDisplayTime[0] = findViewById(R.id.Time1_Create);
        mDisplayTime[0].setOnClickListener(listener);

        mBottun_registration_create = findViewById(R.id.registration_create);
        mBottun_registration_create.setOnClickListener(listener);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());

        DisplayDate2 = getText();

        DisplayDate2 = (TextView) findViewById(R.id.Date1_Create);
        DisplayDate2.setText(month+1 + "/" + dayOfMonth);
        dates.add(DisplayDate2.getText().toString());

        //DisplayDate2.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR,hour);
        c.set(Calendar.MINUTE,minute);
//        String currentTimeString = DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(c.getTime());
        DisplayTime2 = getText2();
        DisplayTime2 = (TextView) findViewById(R.id.Time1_Create);
        DisplayTime2.setText(hour + "：" + minute);
        times.add(DisplayTime2.toString());
    }

    void setText(TextView mDisplayDate){
        DisplayDate = mDisplayDate;
    }

    TextView getText(){
        return DisplayDate;
    }

    void setText2(TextView mDisplayTime){
        DisplayTime = mDisplayTime;
    }

    TextView getText2(){
        return DisplayTime;
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
}



