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

    public List getEventNameList() {
        List<String> eventNameList = new ArrayList<>();
        for(int i = 0 ; i < mEventInfoList.size() ; i++){
            EventInfo eventInfo = mEventInfoList.get(i);
            String eventName = eventInfo.getEventName();
            eventNameList.add(eventName);
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
