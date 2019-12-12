package Task.serialize;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.CandidateDate;


/**
 *   {
 *      "userId":"",
 *      "eventId":"",
 *      "data":""
 *   }
 */
public class AttendanceRequest {
    private final String mEventId;
    private final String mUserId;
<<<<<<< Updated upstream
    private final List<CandidateDate> mCandidates;

    public AttendanceRequest(
            String userId, String eventId, List<CandidateDate> candidateDates) {
        mEventId = eventId;
        mUserId = userId;
        mCandidates = candidateDates;
=======
    private final EventInfo mEventInfo;

    public AttendanceRequest(String userId, EventInfo eventInfo) {
        mUserId = userId;
        mEventInfo = eventInfo;
>>>>>>> Stashed changes
    }

    public JSONObject toJson() {
        try {
            List<JSONObject> jsons = new ArrayList<>();
            for (CandidateDate date : mCandidates) {
                JSONObject jsonObj = new JSONObject()
                        .put("date", date.getDate())
                        .put("time", date.getTime())
                        .put("status", date.getAttendanceType(mUserId));
                jsons.add(jsonObj);
            }
            return new JSONObject()
                    .put("userId", mUserId)
<<<<<<< Updated upstream
                    .put("eventId", mEventId)
                    .put("data", new JSONObject().put("candidates", jsons));
=======
                    .put("eventId", mEventInfo.getEventId());
//                    .put("date", mDate.getDate())
//                    .put("time", mDate.getTime())
//                    .put("status", mAttendanceType.toInt());
>>>>>>> Stashed changes
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
