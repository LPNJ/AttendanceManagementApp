package Task.serialize;

import org.json.JSONException;
import org.json.JSONObject;

import entity.EventInfo;


/**
 *   {
 *      "userId":"",
 *      "eventId":"",
 *      "data":""
 *   }
 */
public class AttendanceRequest {
    private final String mUserId;
    private final EventInfo mEventInfo;

    public AttendanceRequest(
            String userId, EventInfo eventInfo) {
        mUserId = userId;
        mEventInfo = eventInfo;
    }

    public JSONObject toJson() {
        try {
            JSONObject data = new JSONObject()
                    .put("date", mDate)
                    .put("time", mTime)
                    .put("status", mStatus);
            return new JSONObject()
                    .put("userId", mUserId)
                    .put("eventId", mEventId)
                    .put("data", data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
