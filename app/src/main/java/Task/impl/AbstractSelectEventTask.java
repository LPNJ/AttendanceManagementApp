package Task.impl;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
        EventSelectResponse response = new EventSelectResponse(error);
        List<JSONObject> list = null;
        try {
            list = (List) json.get("eventList");
            for (JSONObject obj : list) {
                EventInfo info = EventInfo.parseJson(obj);
                response.addEventInfo(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }
}
