package Task.mock;

import Task.EventCreateTask;
import Task.ResultListener;
import entity.EventCreateRequest;
import entity.EventInfo;
import result.EventCreateResult;

public class EventCreateTaskMock implements EventCreateTask {

    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        listener.onResult(new EventCreateResult(0,"0011"));
    }
}
