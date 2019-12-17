package com.example.ojttask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.shadows.ShadowLog;

import Task.AttendanceRegistrationTask;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 出欠登録画面のテスト
 */
public class AttendanceRegistrationActivityTest {
    private AttendanceRegistrationActivity mActivity;//Activity名が長いので省略した
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


}