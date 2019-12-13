package Task.mock;

import Task.LoginTask;
import Task.LogoutTask;
import Task.ResultListener;
import entity.UserInfo;

public class LogoutTaskMock implements LogoutTask {
    @Override
    public void execute(String s, ResultListener listener) {
        listener.onResult(0);
    }
}
