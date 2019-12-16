package com.example.ojttask;

import android.content.Intent;
import android.os.Build;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.List;

import Task.EventSelectTask;
import Task.ResultListener;
import Task.serialize.EventSelectResponse;
import entity.CandidateDate;
import entity.EventInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class EventSelectActivityTest {
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

    /**
     * ログイン成功でメニュー画面に遷移するかの確認
     */
    @Test
    public void loginSuccess() {
        // ●setup(準備)
        EventSelectActivity activity = createActivity(R.id.edit);
        CandidateDate candidateDate = new CandidateDate("12/12", "19:00");
        List<CandidateDate> dates = new ArrayList<>();
        dates.add(candidateDate);
        EventInfo event = new EventInfo(
                "baseball", "needs ball and bat", "0000", dates);
        List<EventInfo> events = new ArrayList<>();
        events.add(event);
        EventSelectResponse response = new EventSelectResponse(0, events);
        activity.setTask(createTask(response));

        ListView listView = activity.findViewById(R.id.event_list);

        // ●execute(テストの実施)
        // ログインボタンを押下
        listView.performItemClick(null, 0, 0);

        // ●verify(検証)
        String nextActivityName = TestUtils.getLatestAlertDialogMessage(activity);
        assertThat(nextActivityName, is(EventEditActivity.class.getName()));
    }

    private EventSelectActivity createActivity(int buttonId) {
        Intent intent = new Intent();
        intent.putExtra("screen_info", buttonId);
        return TestUtils.createActivity(EventSelectActivity.class, intent);
    }

    /**
     * タスクの結果を設定します。
     * @param result
     * @return
     */
    private EventSelectTask createTask(final EventSelectResponse result) {
        return new EventSelectTask() {
            @Override
            public void execute(String id, ResultListener listener) {
                listener.onResult(result);
            }
        };
    }
}