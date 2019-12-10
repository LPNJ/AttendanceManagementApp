package Task.impl;

import org.json.JSONObject;

import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.SimpleServerTask;
import Task.LoginTask;
import entity.UserInfo;

/**
 * FIXME: 引数の型を決定
 */
public class LoginTaskImpl extends SimpleServerTask implements LoginTask {
    /**
     * 子クラスはpublicにしよう
     * @param listener
     */
    public LoginTaskImpl(ServerRequest.RequestType type, ResultListener listener) {
        super(type, listener);
    }

    @Override
    protected JSONObject createJson(Object[] v) {
        return null;
    }

    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {

    }
}
