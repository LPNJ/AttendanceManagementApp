package Task.mock;

import Task.EventSelectTask;
import Task.ResultListener;
import entity.EditableUserInfo;

public class EventSelectTaskMock implements EventSelectTask {

    @Override
    public void execute(EditableUserInfo userInfo, ResultListener listener) {
        listener.onResult(1);
    }
}
