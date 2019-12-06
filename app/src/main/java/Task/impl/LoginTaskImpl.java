package Task.impl;

import org.json.JSONObject;

import Task.ResultListener;
import Task.ServerRequest;
import Task.SimpleServerTask;
import Task.LoginTask;
import entity.UserInfo;

public class LoginTaskImpl extends SimpleServerTask<UserInfo> implements LoginTask {
    public LoginTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.LOGIN, listener);
    }

    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {
        super.execute(userInfo);
    }

    @Override
    protected JSONObject createJson(UserInfo... v) {
        return v[0].toJson();
    }
}
