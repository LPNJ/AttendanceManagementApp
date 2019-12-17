package com.example.ojttask;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;

import androidx.appcompat.widget.AppCompatButton;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import Task.AttendanceRegistrationTask;
import Task.mock.AttendanceRegistrationMock;
import entity.AttendanceInfo;
import entity.AttendanceType;
import entity.CandidateDate;
import entity.EventInfo;
import validator.AttendanceValidator;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * 出欠登録画面のテスト
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class AttendanceRegistrationActivityTest {
    private AttendanceRegistrationActivity mActivity;//Activity名が長いので省略した
    private AttendanceRegistrationTask mTask;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;
        Intent mIntent = new Intent();
        mIntent.putExtra(IntentKey.REFERENCE_EVENT,  new EventInfo("test","test","test",createCandidateDateList()));
        mActivity = TestUtils.createActivity(AttendanceRegistrationActivity.class,mIntent);
        mTask = Mockito.mock(AttendanceRegistrationMock.class);
    }

    public List createCandidateDateList(){
        ArrayList<CandidateDate> mlist = new ArrayList<CandidateDate>();
        CandidateDate mdate = new CandidateDate("19920828","11:40:24");
        mlist.add(mdate);
        mdate = new CandidateDate("19990222", "12:12:12");
        mlist.add(mdate);
        return mlist;
    }

    @After
    public void testdown(){
    }

    /**
     * onCreateのテスト
     */
    @Test
    public void onCreateTest(){
        //setupでonCreateのテスト実施しているためOK
    }

    /**
     * onResultのエラー処理テスト
     */
    @Test
    public void onResultTest_err(){
        mActivity.onResult(1);
        //TODO なんかエラー処理されるらしいので追加されたらテスト修正
    }

    /**
     * onResultの画面遷移テスト
     */
    @Test
    public void onResultTest_OK(){
        mActivity.onResult(0);
        String nextActivityName = TestUtils.getNextActivityName(mActivity);
        assertThat(nextActivityName,is(MenuActivity.class.getName()));
    }

    @Test
    public void onCheckChangedTest() {
        RadioGroup mRadioGroup = mActivity.findViewById(R.id.radio_group);
        EditText mEditText = mActivity.findViewById(R.id.event_attendance_name);
        mEditText.setText("event");
        mActivity.onCheckedChanged(mRadioGroup,R.id.radio_attendance);
        CandidateDate candidateDate = mActivity.getCaddCandidateDate();
        assertThat(candidateDate.getAttendanceType("event"),is(AttendanceType.ATTENDANCE.toInt()));
        mActivity.onCheckedChanged(mRadioGroup,R.id.radio_unknown);
        assertThat(candidateDate.getAttendanceType("event"),is(AttendanceType.UNKNOWN.toInt()));
        mActivity.onCheckedChanged(mRadioGroup,R.id.radio_absence);
        assertThat(candidateDate.getAttendanceType("event"),is(AttendanceType.ABSENCE.toInt()));
        mActivity.onCheckedChanged(mRadioGroup,9999);
    }


    @Test
    public void radioTest_event_attendance_reference(){
        AppCompatButton mButton = mActivity.findViewById(R.id.event_attendance_reference);
        mButton.performClick();
        String nextActivityName = TestUtils.getNextActivityName(mActivity);
        assertThat(nextActivityName,is(ReferenceActivity.class.getName()));
    }

    @Test
    public void radioTest_event_attendance_decision_attendance_reference(){
        AppCompatButton mButton1 = mActivity.findViewById(R.id.event_attendance_decision);
        AppCompatButton mButton2 = mActivity.findViewById(R.id.event_attendance_reference);

        mButton1.performClick();
        mButton2.performClick();
    }

    @Test
    public void radioTest_event_attendance_decision() throws NoSuchFieldException, IllegalAccessException {
        AppCompatButton mButton = mActivity.findViewById(R.id.event_attendance_decision);
        RadioGroup mRadioGroup = Mockito.mock(RadioGroup.class);
        AttendanceValidator mAttendanceValidator = Mockito.mock(AttendanceValidator.class);
        //whenNew(AttendanceValidator.class);
        //when(mAttendanceValidator.validate()).thenReturn(1);

        mButton.performClick();

    }

}