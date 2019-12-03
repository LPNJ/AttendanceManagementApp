package Task.mock;

import Task.LoginTask;
import Task.ResultListener;
import entity.UserInfo;

public class LogoutTaskMock implements LoginTask {
    @Override
    public void execute(UserInfo userInfo, ResultListener listener) {
        listener.onResult(0);
    }
}
