package Task.mock;

import Task.EventCreateTask;
import Task.ResultListener;
import entity.EventInfo;

public class EventCreateTaskMock implements EventCreateTask {

    @Override
    public void execute(EventInfo eventInfo, ResultListener listener) {
        listener.onResult(0);
    }

}
