package Task.mock;

import Task.EventCreateTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;
import Task.serialize.EventCreateResponse;

public class EventCreateTaskMock implements EventCreateTask {

    @Override
    public void execute(EventCreateRequest eventCreateRequest, ResultListener listener) {
        listener.onResult(new EventCreateResponse(0,"0011"));
    }
}
