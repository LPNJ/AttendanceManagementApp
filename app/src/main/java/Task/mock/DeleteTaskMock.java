package Task.mock;

import Task.DeleteTask;
import Task.EventCreateTask;
import Task.ResultListener;
import entity.DeleteEventRequest;
import entity.EditableUserInfo;
import entity.EventInfo;

public class DeleteTaskMock implements DeleteTask {


    @Override
    public void execute(DeleteEventRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
