package Task.mock;

import Task.LoginTask;
import Task.ResultListener;
import entity.UserInfo;

public class LoginTaskMock implements LoginTask {
    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {
        if ("failure".equals(userInfo.getUserId())) {
            listener.onResult(1);
        } else {
            listener.onResult(0);
        }
    }
}
