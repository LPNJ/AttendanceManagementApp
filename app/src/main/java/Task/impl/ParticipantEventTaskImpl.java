package Task.impl;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import Task.ServerTask;
import Task.ParticipantEventTask;
import Task.ResultListener;
import Task.ServerRequest;

public class ParticipantEventTaskImpl extends AbstractSelectEventTask implements ParticipantEventTask {
    public ParticipantEventTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_ATTENDANCE_EVENT, listener);
    }

    @Override
    public void execute(String userId, ResultListener listener) {
        super.execute(userId);
    }
}
