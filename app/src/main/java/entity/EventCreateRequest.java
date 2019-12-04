package entity;

import org.json.JSONObject;

public class EventCreateRequest {
    private static String SEPARATE_WORD = "#";
    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;

    public EventCreateRequest(String userId, EventInfo eventInfo) {
        this.mUserId = userId;
        this.mEventInfo = eventInfo;
    }

    public JSONObject toJson() {
//        for ()
//        List<String> dateAndTime = new ArrayList<>();
//
//        try {
//            JSONObject data = new JSONObject()
//                .put("eventName", mEventInfo.getEventName())
//                .put("eventDetail", mEventInfo.getEventDetails())
//                .put("")
//
//            JSONObject request = new JSONObject()
//                    .put("userId", mUserId)
//                    .put("data", data);
//            if (mEventInfo.getEventId() != null && !mEventInfo.getEventId().isEmpty()) {
//                request.put("eventId", mEventInfo.getEventId());
//            }
//            return request
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }
}
