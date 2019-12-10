package com.example.ojttask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.AttendanceType;
import entity.CandidateDate;
import entity.EventInfo;

public class ReferenceActivity extends AppCompatActivity {

    private EventInfo mEventInfo;
    /** スピナー（ドロップダウンリスト）で項目が選択された時の操作リスナー */
    private AdapterView.OnItemSelectedListener mItemClickListener =  new AdapterView.OnItemSelectedListener() {
        //　アイテムが選択された時
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner spinner = (Spinner)parent;
            String item = (String)spinner.getSelectedItem();

            for(CandidateDate c : mEventInfo.getCandidateDates()){
                if(item.equals(c.getDateAndTime())){
                    List<String> attendances = c.getAttendanceNames(AttendanceType.ATTENDANCE);
                    ((TextView)findViewById(R.id.AttendanceNumber)).setText(attendances.size());
                    ((TextView)findViewById(R.id.attendanceNames)).setText(createNameList(attendances));
                    List<String> unknowns = c.getAttendanceNames(AttendanceType.UNKNOWN);
                    ((TextView)findViewById(R.id.UnknownNumber)).setText(unknowns.size());
                    ((TextView)findViewById(R.id.unknownNames)).setText(createNameList(unknowns));
                    List<String> absences = c.getAttendanceNames(AttendanceType.ABSENCE);
                    ((TextView)findViewById(R.id.AbsenceNumber)).setText(absences.size());
                    ((TextView)findViewById(R.id.absenceNames)).setText(createNameList(absences));
                }
            }
        }
        //　アイテムが選択されなかった
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // 特に必要がないので、実装しない
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        Serializable info = getIntent().getSerializableExtra(IntentKey.REFERENCE_EVENT);
        if(info instanceof EventInfo){
            mEventInfo = (EventInfo)info;
            List<String> dateTimeList = new ArrayList<>();
            for(CandidateDate c : mEventInfo.getCandidateDates()){
                dateTimeList.add(c.getDateAndTime());
            }
            Spinner spinner = findViewById(R.id.DateTimeList);
            // ArrayAdapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateTimeList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // spinner に adapter をセット
            spinner.setAdapter(adapter);
            // リスナーを登録
            spinner.setOnItemSelectedListener(mItemClickListener);
        }
    }

    private String createNameList(List<String> nameList) {
        StringBuilder sb = new StringBuilder();
        for (String name : nameList) {
            sb.append(name);
            sb.append("\n");
        }
        return sb.toString();
    }

}
