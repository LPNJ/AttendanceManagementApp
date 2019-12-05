package entity;

import org.json.JSONObject;

import java.util.List;

public class EventInfo {
    /**m未定義のイベント番号 */
    public static final String UNDEFINED_EVENT_NUMBER = "UNDEFINED";
    private final String mEventName;
    private final String mEventDetails;
    private final List<String> mDates;
    private final List<String> mTime;
    private final String mEventNumber;

    /**
     * コンストラクタ
     */
    public EventInfo(String eventName, String eventDetail, List<String> dates, List<String> times, String mEventNumber) {
        this.mEventName = eventName;
        this.mEventDetails = eventDetail;
        this.mDates = dates;
        this.mTime = times;
        this.mEventNumber = mEventNumber;
    }

    public String getEventName() {
        return mEventName;
    }

    public String getEventDetails() {
        return mEventDetails;
    }

    public List<String> getDate() {
        return mDates;
    }

    public List<String> getTime() {
        return mTime;
    }

    public String getEventId() {
        return mEventNumber;
    }
}
