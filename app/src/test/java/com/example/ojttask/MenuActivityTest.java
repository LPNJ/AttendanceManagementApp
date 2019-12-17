package com.example.ojttask;

import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import Task.EventCreateTask;
import Task.LoginTask;
import Task.ResultListener;
import entity.UserInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class MenuActivityTest {

    private MenuActivity mMenuActivity;
    private EventCreateTask mTask;
    /**
     * @Before がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施前に呼び出される。
     */
    @Before
    public void setup() {
        // android.utils.Log を使ったログを出力する
        ShadowLog.stream = System.out;
        // Activityを生成
        mMenuActivity = TestUtils.createActivity(MenuActivity.class, null);
    }

    /**
     * @After がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施後に呼び出される。
     */
    @After
    public void teardown() {
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void createActivityMoveSuccess() {
        // ●setup(準備)
        Button loginButton = mMenuActivity.findViewById(R.id.create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String nextActivityName = TestUtils.getNextActivityName(mMenuActivity);
        assertThat(nextActivityName, is(CreateActivity.class.getName()));
    }

    @Test
    public void eventEditActivityMoveSuccess() {
        // ●setup(準備)
        Button loginButton = mMenuActivity.findViewById(R.id.edit);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String nextActivityName = TestUtils.getNextActivityName(mMenuActivity);
        assertThat(nextActivityName, is(EventSelectActivity.class.getName()));
    }

    @Test
    public void eventDeleteActivityMoveSuccess() {
        // ●setup(準備)
        Button loginButton = mMenuActivity.findViewById(R.id.delete);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String nextActivityName = TestUtils.getNextActivityName(mMenuActivity);
        assertThat(nextActivityName, is(EventSelectActivity.class.getName()));
    }

    @Test
    public void participatingEventSelectActivityMoveSuccess() {
        // ●setup(準備)
        Button loginButton = mMenuActivity.findViewById(R.id.attend_register);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String nextActivityName = TestUtils.getNextActivityName(mMenuActivity);
        assertThat(nextActivityName, is(ParticipatingEventSelectActivity.class.getName()));
    }


}