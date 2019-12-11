package Task.mock;

import Task.DeleteTask;
import Task.ResultListener;
import Task.serialize.DeleteEventRequest;
import Task.serialize.EventCreateRequest;

public class DeleteTaskMock implements DeleteTask {
    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
