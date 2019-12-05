package Task.mock;

import java.util.ArrayList;
import java.util.List;

import Task.EventSelectTask;
import Task.ResultListener;
import entity.EventInfo;
import result.EventSelectResult;

public class EventSelectTaskMock implements EventSelectTask {

    @Override
    public void execute(String userId, ResultListener listener) {
        List<EventInfo> eventList = new ArrayList<>();
        eventList.add(new EventInfo("忘年会", "", null, null, "0001"));
        eventList.add(new EventInfo("新年会", "", null, null, "0002"));
        eventList.add(new EventInfo("同期会", "", null, null, "0003"));
        listener.onResult(new EventSelectResult(0, eventList));
    }
}
