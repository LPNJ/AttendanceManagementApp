package Task.mock;

import Task.EventEditTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;

public class EventEditTaskMock implements EventEditTask {
    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
