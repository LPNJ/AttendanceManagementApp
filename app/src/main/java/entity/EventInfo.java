package entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * イベント情報
 */
public class EventInfo implements Serializable {
    public enum EventStatus {
        WORKING(0), DECIDED(1),DELETED(2);
        final int mStatus;
        EventStatus(int status) {
            mStatus = status;
        }

        public int toInt() {
            return mStatus;
        }

        EventStatus valueOf(int status) {
            for (EventStatus s : EventStatus.values()) {
                if (s.toInt() == status) {
                    return s;
                }
            }
            return DELETED;
        }
    }
    /**未定義のイベント番号 */
    public static final String UNDEFINED_EVENT_NUMBER = "UNDEFINED";
    private String mEventName;  // イベント名
    private String mEventDetails;  // イベント詳細
    private String mEventNumber;  // イベントID（番号）
    private final List<CandidateDate> mCandidateDates; // 候補日一覧

    private EventStatus mEventStatus = EventStatus.WORKING;

    // JSON変換用
    private static final String EVENT_ID = "eventId";
    private static final String EVENT_NAME = "eventName";
    private static final String EVENT_DETAIL = "eventDetail";
    // 0:運用中, 1:確定, 2:削除
    private static final String EVENT_STATUS = "eventStatus";
    private static final String CANDIDATES = "candidates";
    private static final String DATA = "data";


    /**
     * コンストラクタ
     */
    public EventInfo(String eventName, String eventDetail, String eventNumber, List<CandidateDate> candidateDate) {
        mEventName = eventName;
        mEventDetails = eventDetail;
        mEventNumber = eventNumber;
        mCandidateDates = candidateDate;
    }

    public void addCandidateDate(CandidateDate date) {
        mCandidateDates.add(date);
    }

    public void setEventNumber(String eventNumber) {
        mEventNumber = eventNumber;
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

    public void setStatus(EventStatus status) {
        mEventStatus = status;
    }

    public EventStatus getEventStatus() {
        return mEventStatus;
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
        CandidateDate removeDate = contains(date, time);
        if (removeDate == null) {
            return;
        }
        mCandidateDates.remove(removeDate);
    }

<<<<<<< Updated upstream
    public void addAttendanceTo(String date, String time, AttendanceInfo info) {
        String trimmedDateTime = date.trim() + " " + time.trim();
        for (CandidateDate candidate : mCandidateDates) {
            if (candidate.getDateAndTime().equals(trimmedDateTime)) {
                candidate.addAttendance(info);
                return;
            }
        }
        CandidateDate candidateDate = new CandidateDate(date, time);
        candidateDate.addAttendance(info);
        mCandidateDates.add(candidateDate);
    }

    public JSONObject toJson() {
        List<JSONObject> candidates = new ArrayList<>();

        try {
            for (CandidateDate date : mCandidateDates) {
                candidates.add(date.toJson());
            }
            return new JSONObject()
                    .put(EVENT_NAME, mEventName)
                    .put(EVENT_DETAIL, mEventDetails)
                    .put(EVENT_STATUS, mEventStatus.toInt())
                    .put(CANDIDATES, candidates);
        } catch (JSONException e) {
            Log.e("EveInfo", "serialize error", e);
            return null;
        }
    }

    public static EventInfo parseJson(JSONObject json) throws JSONException {
        String eventId = json.optString("eventId");
        JSONObject data = json.optJSONObject(DATA);
        String eventName = data.optString(EVENT_NAME);
        String eventDetail = data.optString(EVENT_DETAIL);
        String eventStatus = data.optString(EVENT_STATUS);
        List<JSONObject> candidate = (List) json.opt(CANDIDATES);
        List<CandidateDate> candidateDates = new ArrayList<>();
        for (JSONObject cand : candidate) {
            candidateDates.add(new CandidateDate(cand.optString("date"), cand.optString("time")));
        }
        EventInfo eventInfo = new EventInfo(eventName, eventDetail, eventId, candidateDates);
        eventInfo.setStatus(EventStatus.valueOf(eventStatus));

        List<JSONObject> attendanceList = (List) json.opt("attendanceList");
        for (JSONObject attendance : attendanceList) {

            String userName = attendance.optString("userId");
            List<JSONObject> candidates = (List) json.opt(CANDIDATES);
            for (JSONObject obj : candidates) {
                String date = obj.optString("date");
                String time = obj.optString("time");
                int status = obj.optInt("attendanceStatus");
                AttendanceInfo info = new AttendanceInfo(userName, AttendanceType.valueOf(status));
                eventInfo.addAttendanceTo(date, time, info);
            }
        }
        return eventInfo;
=======
    /**
     * 引数の日付と時間を持った{@link CandidateDate}を削除します。
     * @param dateTimes
     */
    public void removeCandidates(List<String> dateTimes) {
        // 全リストをディープコピー
        List<CandidateDate> deleteTarget = new ArrayList<>(mCandidateDates);
        // 不要なデータだけ抽出する
        for (String dateTime : dateTimes) {
            CandidateDate remain = contains(dateTime);
            if (remain == null) {
                continue;
            }
            deleteTarget.remove(remain);
        }
        mCandidateDates.removeAll(deleteTarget);
    }
    /**
     * 引数の日付と時間を持った{@link CandidateDate}があるかを確認する
     * @param date 日付
     * @param time 時間
     */
    public CandidateDate contains(String date, String time) {
        return contains(date.trim() + " " + time.trim());
    }
    /**
     * 引数の日付と時間を持った{@link CandidateDate}があるかを確認する
     * @param dateTime
     */
    public CandidateDate contains(String dateTime) {
        for (CandidateDate candidate : mCandidateDates) {
            if (candidate.getDateAndTime().equals(dateTime)) {
                return candidate;
            }
        }
        return null;
>>>>>>> Stashed changes
    }
}
