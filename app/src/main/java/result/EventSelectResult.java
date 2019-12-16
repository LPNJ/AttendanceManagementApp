package result;

import java.util.ArrayList;
import java.util.List;

import entity.EventInfo;

public class EventSelectResult {
    private final int mError;
    private final List<EventInfo> mEventInfoList;

    public EventSelectResult(int error, List<EventInfo> eventInfoList) {
        mError = error;
        mEventInfoList = eventInfoList;
    }

    public List<String> getEventNameList() {
        List<String> eventNameList = new ArrayList<>();
        if (mEventInfoList == null) {
            return eventNameList;
        }
        for(EventInfo eventInfo : mEventInfoList){
            eventNameList.add(eventInfo.getEventName());
        }
        return eventNameList;
    }

    public int getError() {
        return mError;
    }

    public List<EventInfo> getEventInfoList() {
        return mEventInfoList;
    }


}
