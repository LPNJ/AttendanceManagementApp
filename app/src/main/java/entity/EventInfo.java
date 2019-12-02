package entity;

public class EventInfo {

    private final String mEventName;
    private final String mEventDetails;
    private final String mDate;
    private final String mTime;
    private final String mEventNumber;

    /**
     * コンストラクタ
     */
    public EventInfo(String mEventName, String mEventDetails, String mDate, String mTime, String mEventNumber) {
        this.mEventName = mEventName;
        this.mEventDetails = mEventDetails;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mEventNumber = mEventNumber;
    }

    public String getEventName() {
        return mEventName;
    }

    public String getEventDetails() {
        return mEventDetails;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
    }

    public String getEvebtNumber() {
        return mEventNumber;
    }

}
