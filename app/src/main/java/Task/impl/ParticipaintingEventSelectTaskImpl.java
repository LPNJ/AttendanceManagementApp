package Task.impl;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.ParticipaintingEventSelectTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.EventInfo;
import entity.UserInfo;

public class ParticipaintingEventSelectTaskImpl
        extends AbstractAsyncTask<UserInfo,Integer> implements ParticipaintingEventSelectTask {

    ParticipaintingEventSelectTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_ATTEND_EVENT, listener);
    }

    @Override
    protected JSONObject createJson(UserInfo... v) {
        return v[0].toJsonOnlyUserId();
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        // TODO 実装する必要ある
        return null;
    }

    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {
        super.execute(userInfo);
    }
}
