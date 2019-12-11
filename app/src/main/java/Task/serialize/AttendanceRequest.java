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
    private final List<CandidateDate> mCandidates;

    public AttendanceRequest(
            String userId, String eventId, List<CandidateDate> candidateDates) {
        mEventId = eventId;
        mUserId = userId;
        mCandidates = candidateDates;
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
                    .put("eventId", mEventId)
                    .put("data", new JSONObject().put("candidates", jsons));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
