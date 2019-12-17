package com.example.ojttask;

import android.content.Intent;
import android.os.Build;
import android.widget.Spinner;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.List;

import entity.AttendanceInfo;
import entity.AttendanceType;
import entity.CandidateDate;
import entity.EventInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class ReferenceActivityTest {
    /**
     * @Before がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施前に呼び出される。
     */
    @Before
    public void setup() {
        // android.utils.Log を使ったログを出力する
        ShadowLog.stream = System.out;
    }

    /**
     * @After がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施後に呼び出される。
     */
    @After
    public void teardown() {
    }

    @Test
    public void onItemSelected() {
        Intent intent = new Intent();
        AttendanceInfo attendanceInfo1 = new AttendanceInfo("user", AttendanceType.valueOf(0));
        AttendanceInfo attendanceInfo2 = new AttendanceInfo("uuuu", AttendanceType.valueOf(1));
        CandidateDate candidateDate1 = new CandidateDate("12/11", "18:00");
        candidateDate1.addAttendance(attendanceInfo1);
        CandidateDate candidateDate2 = new CandidateDate("12/12", "19:00");
        candidateDate2.addAttendance(attendanceInfo2);
        List<CandidateDate> dates = new ArrayList<>();
        dates.add(candidateDate1);
        EventInfo event = new EventInfo(
                "baseball", "needs ball and bat", "0000", dates);
        intent.putExtra(IntentKey.REFERENCE_EVENT, event);
        ReferenceActivity activity = TestUtils.createActivity(ReferenceActivity.class, intent);
        Spinner spinner = (Spinner) spy(activity.findViewById(R.id.DateTimeList));
        doReturn(candidateDate1.getDateAndTime()).when(spinner).getSelectedItem();

        spinner.performItemClick(null, 0, 0);

        TextView number = activity.findViewById(R.id.AttendanceNumber);
        String numberOfAttendance = number.getText().toString();
        TextView attendance = activity.findViewById(R.id.attendanceNames);
        String attendances = attendance.getText().toString();

        assertThat(numberOfAttendance, is("1"));
        assertThat(attendances, is("user\n"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void onCreateNotEventInfo() {
        Intent intent = new Intent();
        intent.putExtra(IntentKey.REFERENCE_EVENT, "aiueo");
        ReferenceActivity activity = Robolectric.buildActivity(
                ReferenceActivity.class, intent).get();
        activity.onCreate(null);
    }
}