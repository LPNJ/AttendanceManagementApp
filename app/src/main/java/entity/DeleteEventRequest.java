package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteEventRequest {
    private final String mUserId;
    private final String mEventId;

    public DeleteEventRequest(String userId, String eventId) {
        this.mUserId = userId;
        this.mEventId = eventId;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject()
                    .put("userId", mUserId)
                    .put("eventId", mEventId);
        } catch (JSONException e) {
            return null;
        }
    }
}
