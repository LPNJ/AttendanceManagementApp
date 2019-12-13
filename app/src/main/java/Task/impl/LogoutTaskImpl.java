package Task.impl;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import Task.LogoutTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.SimpleServerTask;

public class LogoutTaskImpl extends SimpleServerTask<String> implements LogoutTask {

    private static final String TAG = "LogoutTask";

    public LogoutTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.LOGOUT, listener);
    }

    public void execute(String userInfo, ResultListener listener) {
        super.execute(userInfo);
    }

    @Override
    protected JSONObject createJson(String... userId) {
        try {
            return new JSONObject().put("userId", userId[0]);
        } catch (JSONException e) {
            Log.i(TAG, "serialize error.", e);
            return new JSONObject();
        }
    }
}
