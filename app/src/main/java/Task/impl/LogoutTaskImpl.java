package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.LogoutTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.UserInfo;

public class LogoutTaskImpl extends AbstractAsyncTask<UserInfo, Integer> implements LogoutTask {
    public LogoutTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.LOGOUT, listener);
    }

    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {
        super.execute(userInfo);
    }

    @Override
    protected JSONObject createJson(UserInfo... userInfo) {
        return userInfo[0].toJsonOnlyUserId();
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        if (json == null) return 1;
        return json.optInt("error", 1);
    }
}
