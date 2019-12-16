package Task.serialize;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Task.ServerRequest;
import entity.CandidateDate;
import entity.EventInfo;

/**
 * サーバーに送る形式のイベント作成のリクエスト
 */
public class EventCreateRequest {
    /**
     * 操作の種類
     */
    public enum CreateType {
        CREATE, EDIT;
    }
    /** ログに出力するタグ名 */
    private static final String TAG = EventCreateRequest.class.getSimpleName();

    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;

    /**
     * コンストラクタ
     * @param userId ユーザーID
     * @param eventInfo イベント情報
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
            Log.i(TAG, "serialize error.", e);
            return null;
        }
    }
}
