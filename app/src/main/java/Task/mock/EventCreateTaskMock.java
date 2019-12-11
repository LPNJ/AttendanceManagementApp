package Task.mock;

import Task.EventCreateTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;
import result.EventCreateResult;

public class EventCreateTaskMock implements EventCreateTask {

    @Override
    public void execute(EventCreateRequest eventCreateRequest, ResultListener listener) {
        listener.onResult(new EventCreateResult(0,"0011"));
    }
}
