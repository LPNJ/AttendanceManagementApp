package Task.serialize;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Task.ServerRequest;
import entity.CandidateDate;
import entity.EventInfo;

public class EventCreateRequest {
    public enum CreateType {
        CREATE, EDIT;
    }

    private static String SEPARATE_WORD = "#";
    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;

    /**
     * @param userId
     * @param eventInfo
     */
    public EventCreateRequest(String userId, EventInfo eventInfo) {
        mUserId = userId;
        mEventInfo = eventInfo;
    }

    public JSONObject toJson(CreateType type) {
        try {
            JSONObject request = new JSONObject()
                    .put("userId", mUserId)
                    .put("data", mEventInfo.toJson());
            if (type == CreateType.EDIT) {
                request.put("eventId", mEventInfo.getEventId());
            }
            return request;
        } catch (JSONException e) {
            Log.i("EveCreReq", "serialize error.", e);
            return null;
        }
    }
}
