package ReferenceEntity;

import java.util.List;

public class ReferenceEventInfo {

    /**m未定義のイベント番号 */
    public static final String UNDEFINED_EVENT_NUMBER = "UNDEFINED";
    private final String mEventName;
    private final String mEventDetails;
    private final List<ReferenceDateTime> mDateTime;

    /**
     * コンストラクタ
     */
    public ReferenceEventInfo(String eventName, String eventDetail, List<ReferenceDateTime> dateTime) {
        this.mEventName = eventName;
        this.mEventDetails = eventDetail;
        this.mDateTime = dateTime;
    }

    public String getEventName() {
        return mEventName;
    }

    public String getEventDetails() {
        return mEventDetails;
    }

    public List<ReferenceDateTime> getDateTime() {
        return mDateTime;
    }
}
