package entity;

import android.util.Log;

import org.json.JSONArray;
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

        static EventStatus valueOf(int status) {
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
        Log.i("get json",json.toString());
        String eventId = json.optString("eventId");
//        JSONObject data = json.optJSONObject(DATA);
        String dataString = json.optString(DATA);
        JSONObject data = new JSONObject(dataString);
        String eventName = data.optString(EVENT_NAME);
        String eventDetail = data.optString(EVENT_DETAIL);
        int eventStatus = data.optInt(EVENT_STATUS);
        String candidateString = data.getString(CANDIDATES);
        JSONArray candidateArray = new JSONArray(candidateString);
        List<CandidateDate> candidateDates = new ArrayList<>();
        for (int i = 0; i < candidateArray.length(); i++) {
            JSONObject candidate = new JSONObject(candidateArray.getString(i));
            candidateDates.add(new CandidateDate(candidate.optString("date"), candidate.optString("time")));
        }
//        List<JSONObject> candidate = (List) json.opt(CANDIDATES);
//        List<CandidateDate> candidateDates = new ArrayList<>();
//        for (JSONObject cand : candidate) {
//            candidateDates.add(new CandidateDate(cand.optString("date"), cand.optString("time")));
//        }
        EventInfo eventInfo = new EventInfo(eventName, eventDetail, eventId, candidateDates);
        eventInfo.setStatus(EventStatus.valueOf(eventStatus));

        JSONArray attendanceList = json.getJSONArray("attendanceList");
        for (int i = 0; i <attendanceList.length(); i++) {
            JSONObject attendance = attendanceList.getJSONObject(i);
            String userName = attendance.getString("userId");
            String dataStr = attendance.getString("data");
            JSONObject dataObj  = new JSONObject(dataStr);
            String date = dataObj.optString("date");
            String time = dataObj.optString("time");
            int status = dataObj.optInt("attendanceStatus");
            eventInfo.addAttendanceTo(date, time, new AttendanceInfo(userName, AttendanceType.valueOf(status)));
        }
        return eventInfo;
    }

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
    }
}
