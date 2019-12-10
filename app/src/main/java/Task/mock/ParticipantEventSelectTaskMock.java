package Task.mock;

import java.util.ArrayList;
import java.util.List;

import Task.EventSelectTask;
import Task.ResultListener;
import entity.AttendanceInfo;
import entity.AttendanceType;
import entity.CandidateDate;
import entity.EventInfo;
import result.EventSelectResult;

public class ParticipantEventSelectTaskMock implements EventSelectTask {
    @Override
    public void execute(String userId, ResultListener listener) {
        List<EventInfo> eventList = new ArrayList<>();
        eventList.add(new EventInfo("忘年会(参加)", "", null,  createMockedDate(1)));
        eventList.add(new EventInfo("新年会(参加)", "", null,  createMockedDate(2)));
        eventList.add(new EventInfo("同期会(参加)", "", null,  createMockedDate(3)));
        listener.onResult(new EventSelectResult(0, eventList));
    }

    /**
     * @param count
     * @return
     */
    private List<CandidateDate> createMockedDate(int count) {
        List<CandidateDate> list = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            CandidateDate date = new CandidateDate("2019/12/1" + i, "18:" + i + "0");
            createMockedAttendance(date, i);
            list.add(date);
        }
        return list;
    }

    private void createMockedAttendance(CandidateDate date, int index) {
        if (index % 2 == 0) {
            return;
        }
        for (int i = 0; i < 100; ++i) {
            date.addAttendance(new AttendanceInfo("user" + i, AttendanceType.valueOf(i % 3)));
        }
    }
}
