package Task.serialize;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class EventCreateResponse {
    private final int mError;
    private final String mEventId;

    public EventCreateResponse(int error, String eventId) {
        mError = error;
        mEventId = eventId;
    }

    public int getError() {
        return mError;
    }

    public String getEventId() {
        return mEventId;
    }

    public static EventCreateResponse parseJson(JSONObject json) {
        try {
            int error = json.getInt("error");
            String eventId = json.getString("eventId");
            return new EventCreateResponse(error, eventId);
        } catch (JSONException e) {
            Log.e("EveCreRes", "deserializeError", e);
            return new EventCreateResponse(-1, "");
        }
    }
}
