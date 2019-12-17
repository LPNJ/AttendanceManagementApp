package com.example.ojttask;

import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import Task.EventCreateTask;
import Task.LoginTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;
import entity.CandidateDate;
import entity.UserInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class CreateActivityTest {
    private CreateActivity mCreateActivity;
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
        mCreateActivity = TestUtils.createActivity(CreateActivity.class, null);
        // LoginTaskをモックする
        mTask = Mockito.mock(EventCreateTask.class);
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
    public void EventCreateSuccess() {
        // ●setup(準備)
        // IDとPassを設定
        enterEventInfo("忘年会", "開催は新横浜","10/10","19:00");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 0);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.decision)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventnameEmptyError() {
        // ●setup(準備)
        // IDとPassを設定
        enterEventInfo("", "開催は新横浜","10/10","19:00");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.login_error)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventDateEmptyError() {
        // ●setup(準備)
        // IDとPassを設定
        enterEventInfo("忘年会", "開催は新横浜","","19:00");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.login_error)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventTimeEmptyError() {
        // ●setup(準備)
        // IDとPassを設定
        enterEventInfo("忘年会", "開催は新横浜","10/10","");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.login_error)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventEmptyError() {
        // ●setup(準備)
        // IDとPassを設定
        enterEventInfo("", "","","");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.login_error)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventNameInputOverError() {
        // ●setup(準備)
        // IDとPassを設定

        String str = "a";

        for(int i=0;i<=51;i++){
            str += "a";
        }

        enterEventInfo(str, "場所は新横浜","10/10","19:00");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.eventname_input_over)));
    }

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void EventDetailsInputOverError() {
        // ●setup(準備)
        // IDとPassを設定

        String str = "";

        for(int i=0;i<=1000;i++){
            str += "a";
        }

        enterEventInfo("忘年会", str,"10/10","19:00");
        // LoginTaskで必ず成功が返るように設定
        EventCreateTask task = createTask( 1);
        mCreateActivity.setTask(task);
        Button loginButton = mCreateActivity.findViewById(R.id.registration_create);

        // ●execute(テストの実施)
        // ログインボタンを押下
        loginButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.eventdetails_input_over)));
    }

    /**
     * ログインタスクの結果を設定します。
     * @param result
     * @return
     */
    private EventCreateTask createTask(final int result) {
        return new EventCreateTask() {
            @Override
            public void execute(EventCreateRequest eventCreateRequest, ResultListener listener) {
                listener.onResult(result);
            }
        };
    }

    /**
     * 引数に指定したId, Passを設定します。
     * @param
     * @param
     */
    private void enterEventInfo(String eventName, String eventDetail, String date , String time) {
        EditText eventNameText = mCreateActivity.findViewById(R.id.EventName_Create);
        EditText eventText = mCreateActivity.findViewById(R.id.EventInfo_Create);
        TextView dateText = mCreateActivity.findViewById(R.id.Date1_Create);
        TextView timeText = mCreateActivity.findViewById(R.id.Time1_Create);
        eventNameText.setText(eventName);
        eventText.setText(eventDetail);
        dateText.setText(date);
        timeText.setText(time);
    }

}