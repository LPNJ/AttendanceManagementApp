package entity;

import java.util.List;

/**
 * イベント情報
 */
public class EventInfo {
    /**未定義のイベント番号 */
    public static final String UNDEFINED_EVENT_NUMBER = "UNDEFINED";
    private final String mEventName;  // イベント名
    private final String mEventDetails;  // イベント詳細
    private final String mEventNumber;  // イベントID（番号）
    private final List<CandidateDate> mCandidateDates; // 候補日一覧

    /**
     * コンストラクタ
     */
    public EventInfo(String eventName, String eventDetail, String eventNumber, List candidateDates) {
        mEventName = eventName;
        mEventDetails = eventDetail;
        mEventNumber = eventNumber;
        mCandidateDates = candidateDates;

    }

    public String getEventName() {
        return mEventName;
    }

    public String getEventDetails() {
        return mEventDetails;
    }

    public String getEventId() {
        return mEventNumber;
    }

    public List<CandidateDate> getCandidateDates() {
        return mCandidateDates;
    }
}
