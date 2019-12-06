package Task.mock;

import Task.DeleteTask;
import Task.ResultListener;
import Task.serialize.DeleteEventRequest;

public class DeleteTaskMock implements DeleteTask {


    @Override
    public void execute(DeleteEventRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
