package result;

public class EventResult {
    private final int mError;
    private final String mEventId;

    EventResult(int error, String eventId) {
        mError = error;
        mEventId = eventId;
    }

    public int getError() {
        return mError;
    }

    public String getEventId() {
        return mEventId;
    }

}
