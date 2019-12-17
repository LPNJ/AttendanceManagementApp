package com.example.ojttask;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.ArrayList;
import java.util.List;

import Task.EventCreateTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;
import Task.serialize.EventCreateResponse;
import Task.serialize.EventSelectResponse;
import entity.CandidateDate;
import entity.EventInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class EventEditActivityTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void onCreateNotEventInfo() {
        // ●setup(準備)
        Intent intent = new Intent();
        intent.putExtra(IntentKey.REFERENCE_EVENT, "aaaa");
        TestUtils.createActivity(EventEditActivity.class, intent);
    }

    @Test
    public void onCreateDisplayEventInfo() {
        // ●setup(準備)
        Intent intent = new Intent();
        intent.putExtra(IntentKey.REFERENCE_EVENT, createEvent());

        EventEditActivity activity = TestUtils.createActivity(EventEditActivity.class, intent);

        TextView date = activity.findViewById(R.id.Date1_Edit);
        TextView time = activity.findViewById(R.id.Time1_Edit);
        assertThat(date.getText().toString(), is("12/12"));
        assertThat(time.getText().toString(), is("19:00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void onResultResultNull() {
        // ●setup(準備)
        Intent intent = new Intent();
        intent.putExtra(IntentKey.REFERENCE_EVENT, createEvent());
        EventEditActivity activity = TestUtils.createActivity(EventEditActivity.class, intent);
        activity.setTask(createTask(null));
        Button registrationButton = activity.findViewById(R.id.registration_edit);

        registrationButton.performClick();
    }

    @Test
    public void onResultNextActivity() {
        // ●setup(準備)
        Intent intent = new Intent();
        intent.putExtra(IntentKey.REFERENCE_EVENT, createEvent());
        EventEditActivity activity = TestUtils.createActivity(EventEditActivity.class, intent);
        activity.setTask(createTask(new EventCreateResponse(0, "1111")));
        Button registrationButton = activity.findViewById(R.id.registration_edit);

        registrationButton.performClick();

        String nextActivityName = TestUtils.getNextActivityName(activity);
        assertThat(nextActivityName, is(MenuActivity.class.getName()));
    }

    private EventInfo createEvent() {
        CandidateDate candidateDate = new CandidateDate("12/12", "19:00");
        List<CandidateDate> dates = new ArrayList<>();
        dates.add(candidateDate);
        return new EventInfo(
                "baseball", "needs ball and bat", "0000", dates);
    }

    /**
     * タスクの結果を設定します。
     * @param result
     * @return
     */
    private EventCreateTask createTask(final EventCreateResponse result) {
        return new EventCreateTask() {
            @Override
            public void execute(EventCreateRequest request, ResultListener listener) {
                listener.onResult(result);
            }
        };
    }
}