package Task.mock;

import Task.DeleteTask;
import Task.EventCreateTask;
import Task.ResultListener;
import entity.EditableUserInfo;
import entity.EventInfo;

public class DeleteTaskMock implements DeleteTask {

    @Override
    public void execute(EditableUserInfo userInfo, ResultListener listener) {
        listener.onResult(0);
    }
}
