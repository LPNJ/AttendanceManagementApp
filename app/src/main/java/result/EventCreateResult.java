package result;

public class EventCreateResult {
    private final int mError;
    private final String mEventId;

    public EventCreateResult(int error, String eventId) {
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
