package Task.impl;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Task.ServerTask;
import Task.EventSelectTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.EventSelectResponse;
import entity.EventInfo;
import entity.UserInfo;

/**
 * 幹事がイベント一覧を取得するときと参加者がイベントを取得するときのAPIが継承する
 */
abstract class AbstractSelectEventTask extends ServerTask<String, EventSelectResponse> {
    private static final String TAG = "EveSelTask";

    protected AbstractSelectEventTask(ServerRequest.RequestType type, ResultListener listener) {
        super(type, listener);
    }

    @Override
    protected JSONObject createJson(String... v) {
        try {
            return new JSONObject().put("userId", v[0]);
        } catch (JSONException e) {
            Log.i(TAG, "serialize error.", e);
            return new JSONObject();
        }
    }

    @Override
    protected EventSelectResponse parseJson(JSONObject json) {
        int error = json.optInt("error", -1);
        try {
            JSONArray list = json.getJSONArray("eventList");
            List<EventInfo> events = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                EventInfo info = EventInfo.parseJson(list.getJSONObject(i));
                events.add(info);
            }
            return new EventSelectResponse(error, events);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
