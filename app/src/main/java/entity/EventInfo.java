package entity;

import java.util.List;

/**
 * イベント情報
 */
public class EventInfo {
    /**未定義のイベント番号 */
    public static final String UNDEFINED_EVENT_NUMBER = "UNDEFINED";
    private String mEventName;  // イベント名
    private String mEventDetails;  // イベント詳細
    private final String mEventNumber;  // イベントID（番号）
    private final List<CandidateDate> mCandidateDates; // 候補日一覧

    /**
     * コンストラクタ
     */
    public EventInfo(String eventName, String eventDetail, String eventNumber, List candidateDate) {
        mEventName = eventName;
        mEventDetails = eventDetail;
        mEventNumber = eventNumber;
        mCandidateDates = candidateDate;
    }

    public void addCandidateDate(CandidateDate date) {
        mCandidateDates.add(date);
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    public String getEventDetails() {
        return mEventDetails;
    }

    public void setEventDetails(String details) {
        mEventDetails = details;
    }

    public String getEventId() {
        return mEventNumber;
    }

    public List<CandidateDate> getCandidateDates() {
        return mCandidateDates;
    }

    /**
     * 引数の日付と時間を持った{@link CandidateDate}を削除します。
     * @param date
     * @param time
     */
    public void removeCandidate(String date, String time) {
        CandidateDate removeDate = null;
        String trimmedDateTime = date.trim() + " " + time.trim();
        for (CandidateDate candidate : mCandidateDates) {
            if (candidate.getDateAndTime().equals(trimmedDateTime)) {
                removeDate = candidate;
                break;
            }
        }
        if (removeDate == null) {
            return;
        }
        mCandidateDates.remove(removeDate);
    }
}
