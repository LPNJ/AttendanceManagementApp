package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.LogoutTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.UserInfo;

// FIXME: 意味が分かりません
public class LogoutTaskImpl extends AbstractAsyncTask<UserInfo, Integer> implements LogoutTask {
    public LogoutTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.LOGOUT, listener);
    }

    @Override
    protected JSONObject createJson(UserInfo... userInfo) {
        //return userInfo[0].toJsonOnlyUserId();
        return null;
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        if (json == null) return 1;
        return json.optInt("error", 1);
    }

    @Override
    public void execute(String s, ResultListener listener) {
        // super.execute(s, listener);
    }
}
