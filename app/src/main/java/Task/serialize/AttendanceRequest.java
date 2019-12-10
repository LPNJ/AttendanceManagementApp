package Task.serialize;

import org.json.JSONException;
import org.json.JSONObject;

import entity.AttendanceType;
import entity.CandidateDate;
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
    private final CandidateDate mDate;
    private final AttendanceType mAttendanceType;

    public AttendanceRequest(String userId, EventInfo eventInfo, CandidateDate date, AttendanceType type) {
        mUserId = userId;
        mEventInfo = eventInfo;
        mDate = date;
        mAttendanceType = type;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject()
                    .put("userId", mUserId)
                    .put("eventId", mEventInfo.getEventId())
                    .put("date", mDate.getDate())
                    .put("time", mDate.getTime())
                    .put("status", mAttendanceType.toInt());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
