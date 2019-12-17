package com.example.ojttask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.shadows.ShadowLog;

import Task.AttendanceRegistrationTask;

import static org.junit.Assert.*;

/**
 * 出欠登録画面のテスト
 */
public class AttendanceRegistrationActivityTest {
    private AttendanceRegistrationActivity mActivity;
    private AttendanceRegistrationTask mTask;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;
        mActivity = TestUtils.createActivity(AttendanceRegistrationActivity.class,null);
        mTask = Mockito.mock(AttendanceRegistrationTask.class);
    }

    @After
    public void testdown(){
    }

    @Test
    public void onCreate(){
        //setupでonCreateのテスト実施しているためOK
    }

    @Test
    public void onResultTest_err(){
        mActivity.onResult(1);

    }

}