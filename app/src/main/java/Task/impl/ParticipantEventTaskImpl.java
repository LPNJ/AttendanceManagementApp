package Task.impl;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.ParticipantEventTask;
import Task.ResultListener;
import Task.ServerRequest;

import entity.EventInfo;

public class ParticipantEventTaskImpl extends AbstractAsyncTask<String, Integer> implements ParticipantEventTask {
    ParticipantEventTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_ATTENDANCE_EVENT, listener);
    }
    @Override
    protected JSONObject createJson(String... v) {
        try {
            return new JSONObject().put("userId", v[0]);
        } catch (JSONException e) {
            Log.e("ParEveTask", "serialize error.", e);
            return null;
        }
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        // TODO 実装する必要ある
        return null;
    }

    @Override
    public void execute(String s, ResultListener listener) {

    }
}
