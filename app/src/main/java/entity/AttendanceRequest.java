package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class AttendanceRequest {
    public enum AttendanceType {
        ATTENDANCE(0), UNKNOWN(1), ABSENCE(2);

        final int mStatus;
        AttendanceType(int i) {
            mStatus = i;
        }

        int toInt() {
            return mStatus;
        }
    }

    private final String mUserId;
    private final String mEventId;
    private final String mDate;
    private final String mTime;
    private final int mStatus;


    public AttendanceRequest(
            String userId, String eventId, String date, String time, AttendanceType status) {
        this.mUserId = userId;
        this.mEventId = eventId;
        this.mDate = date;
        this.mTime = time;
        this.mStatus = status.toInt();
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
