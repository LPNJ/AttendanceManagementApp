package Task.serialize;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.CandidateDate;
import entity.EventInfo;

public class EventCreateRequest {
    enum EventType {
        CREATE, EDIT
    }
    private static String SEPARATE_WORD = "#";
    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;
    private final EventType mType;

    /**
     *
     * @param userId
     * @param eventInfo
     */
    public EventCreateRequest(String userId, EventInfo eventInfo, EventType type) {
        mUserId = userId;
        mEventInfo = eventInfo;
        mType = type;
    }

    public JSONObject toJson() {

        try {
            List<JSONObject> dates = new ArrayList<>();
            for (CandidateDate date : mEventInfo.getCandidateDates()) {
                for ()
                JSONObject json = new JSONObject()
                        .put("date", date.getDate())
                        .put("time", date.getTime())
                        .put("")
                dates.add(json);
            }
            JSONObject data = new JSONObject()
                .put("eventName", mEventInfo.getEventName())
                .put("eventDetail", mEventInfo.getEventDetails())
                .put("candidateDates", dates);

            JSONObject request = new JSONObject()
                    .put("userId", mUserId)
                    .put("data", data);
            if (mType == EventType.EDIT) {
                request.put("eventId", mEventInfo.getEventId());
            }
            return request;
        } catch (JSONException e) {
            Log.i("EveCreReq", "serialize error.", e);
            return null;
        }
        return null;
    }


}
