package Task.impl;

import org.json.JSONObject;

import Task.RegisterTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.SimpleServerTask;
import entity.UserInfo;

/**
 * RegisterTask の本来実装すべき処理を実装する処理を記述する。
 */
public class RegisterTaskImpl extends SimpleServerTask<UserInfo> implements RegisterTask {
    public RegisterTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.REGISTER_USER, listener);
    }

    @Override
    public void execute(UserInfo userInfo, ResultListener resultListener) {
        super.execute(userInfo);
    }

    @Override
    protected JSONObject createJson(UserInfo... userInfo) {
        return userInfo[0].toJson();
    }
}
