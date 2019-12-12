package Task.serialize;

import java.util.ArrayList;
import java.util.List;

import entity.EventInfo;

public class EventSelectResponse {
    private int mError;
    private final List<EventInfo> mDeletedEvents;
    private final List<EventInfo> mDecidedEvents;
    private final List<EventInfo> mWorkingEvents;

    public EventSelectResponse(int error, List<EventInfo> eventInfoList) {
        mError = error;
        mDecidedEvents = new ArrayList<>();
        mDeletedEvents = new ArrayList<>();
        mWorkingEvents = new ArrayList<>();
        for (EventInfo info : eventInfoList) {
            addEventInfo(info);
        }
    }

    private void addEventInfo(EventInfo eventInfo) {
        switch (eventInfo.getEventStatus()) {
            case WORKING:
                mWorkingEvents.add(eventInfo);
                return;
            case DECIDED:
                mDecidedEvents.add(eventInfo);
                return;
            case DELETED:
                mDeletedEvents.add(eventInfo);
                return;

        }
    }

    public int getError() {
        return mError;
    }

    public List<String> getWorkingEventNames() {
        List<String> names = new ArrayList<>();
        for (EventInfo info : mWorkingEvents) {
            names.add(info.getEventName());
        }
        return names;
    }

    public List<EventInfo> getWorkingEvents() {
        return mWorkingEvents;
    }

//    public List<EventInfo> getDeletedEvents() {
//        return mDeletedEvents;
//    }
//
//    public List<EventInfo> getDecidedEvents() {
//        return mDecidedEvents;
//    }
}
