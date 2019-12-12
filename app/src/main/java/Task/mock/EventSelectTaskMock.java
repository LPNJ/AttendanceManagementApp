package Task.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Task.EventSelectTask;
import Task.ResultListener;
import Task.serialize.EventSelectResponse;
import entity.CandidateDate;
import entity.EventInfo;

public class EventSelectTaskMock implements EventSelectTask {
    @Override
    public void execute(String userId, ResultListener listener) {
        List<EventInfo> eventList = new ArrayList<>();
        eventList.add(new EventInfo("忘年会", "", null,  createMockedDate(0)));
        eventList.add(new EventInfo("新年会", "", null,  createMockedDate(1)));
        eventList.add(new EventInfo("同期会", "", null,  createMockedDate(2)));
        listener.onResult(new EventSelectResponse(0, eventList));
    }

    /**
     * @param count
     * @return
     */
    private List<CandidateDate> createMockedDate(int count) {
        List<CandidateDate> list = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            list.add(new CandidateDate("2019/12/1" + count, "18:" + count + "0"));
        }
        return list;
    }
}
