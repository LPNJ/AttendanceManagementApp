package Task.mock;

import Task.EventEditTask;
import Task.ResultListener;
import entity.EventInfo;

public class EventEditTaskMock implements EventEditTask {


    @Override
    public void execute(EventInfo eventInfo, ResultListener listener) {
        listener.onResult(0);
    }
}
