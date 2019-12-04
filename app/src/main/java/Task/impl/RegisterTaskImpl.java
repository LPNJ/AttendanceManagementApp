package Task.impl;


import org.json.JSONException;
import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.RegisterTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.UserInfo;

/**
 * RegisterTask の本来実装すべき処理を実装する処理を記述する。
 */
public class RegisterTaskImpl extends AbstractAsyncTask<UserInfo, Integer> implements RegisterTask {
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

    @Override
    protected Integer parseJson(JSONObject json) {
        if (json == null) {
            return 1;
        }
        return json.optInt("error", 1);
    }
}
