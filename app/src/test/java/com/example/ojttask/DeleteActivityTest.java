package com.example.ojttask;

import android.content.Entity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.Serializable;

import Task.DeleteTask;
import Task.LoginTask;
import Task.ResultListener;
import Task.mock.DeleteTaskMock;
import Task.serialize.EventCreateRequest;
import entity.EventInfo;
import entity.UserInfo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class DeleteActivityTest {

    private DeleteActivity mDeleteActivity;
    private DeleteTask mTask;

    @Before
    public void setup(){
        // android.utils.Log を使ったログを出力する
        ShadowLog.stream = System.out;
        // Activityを生成
        Intent mIntent = new Intent();
        mIntent.putExtra(IntentKey.REFERENCE_EVENT,  new EventInfo("test","test","test",null));
        mDeleteActivity = TestUtils.createActivity(DeleteActivity.class,mIntent);
        // LoginTaskをモックする
        mTask = Mockito.mock(DeleteTask.class);
    }

    /**
     * @After がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施後に呼び出される。
     */
    @After
    public void teardown() {
    }


    @Test
    public void onCreate() {
        //setupでcreateしているのでOK
    }

    /**
     * ダイアログが表示されないことを確認する
     */
    @Test
    public void onResultTest_result0() {
        Intent mIntent = Mockito.mock(Intent.class);
        mDeleteActivity.onResult(0);

        String nextActiveName = TestUtils.getNextActivityName(mDeleteActivity);
        assertThat(nextActiveName,is(MenuActivity.class.getName()));

    }

    /**
     * ダイアログが表示されメッセージを確認する
     */
    @Test
    public void onResultTest_result1() {
        Intent mIntent = Mockito.mock(Intent.class);
        mDeleteActivity.onResult(1);
        String messege = TestUtils.getLatestAlertDialogMessage();
        assertThat(messege, is("削除に失敗しました"));

    }

    /**
     * 実装的にただ素通りするだけのテスト
     */
    @Test
    public void onClick_delete() {
        DeleteTaskMock mdeleteTaskMock = createTask(0);
        Button button = mDeleteActivity.findViewById(R.id.ok_delete);
        button.performClick();
    }

    /**
     * EventSelectActivityで、キャンセルボタンを押したときの画面遷移を確認する
     */
    @Test
    public void onClick_cancel() {

        Button button = mDeleteActivity.findViewById(R.id.cancel_delete);
        button.performClick();
        String nextActivityName = TestUtils.getNextActivityName(mDeleteActivity);
        assertThat(nextActivityName, is(EventSelectActivity.class.getName()));

    }

    /**
     * deleteタスクの結果を設定します。
     * @param result 0:成功 1:失敗
     * @return DeleteTaskMock 成功or失敗をすることが確定しているタスク
     */
    private DeleteTaskMock createTask(final int result) {
        return new DeleteTaskMock() {
            @Override
            public void execute(EventCreateRequest eventCreateRequest, ResultListener listener) {
                listener.onResult(result);
            }
        };
    }
}