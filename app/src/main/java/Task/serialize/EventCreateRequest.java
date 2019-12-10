package Task.serialize;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.CandidateDate;
import entity.EventInfo;

/**
 * サーバーに送る形式のイベント作成のリクエスト
 */
public class EventCreateRequest {
    /** ログに出力するタグ名 */
    private static final String TAG = EventCreateRequest.class.getSimpleName();
    /**
     * 操作の種類
     */
    public enum OperationType {
        CREATE,
        EDIT,
        ;
    }

    /** TODO: これの役割が分かりません */
    private static final String SEPARATE_WORD = "#";
    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;
    private final OperationType mType;

    /**
     * コンストラクタ
     * @param userId ユーザーID
     * @param eventInfo イベント情報
     * @param type イベントの登録か、更新か
     */
    public EventCreateRequest(String userId, EventInfo eventInfo, OperationType type) {
        mUserId = userId;
        mEventInfo = eventInfo;
        mType = type;
    }

    /**
     *
     * @return
     */
    public JSONObject toJson() {
        try {
            List<JSONObject> dates = new ArrayList<>();
            for (CandidateDate date : mEventInfo.getCandidateDates()) {
                JSONObject json = new JSONObject()
                        .put("date", date.getDate())
                        .put("time", date.getTime());
                dates.add(json);
            }
            JSONObject data = new JSONObject()
                .put("eventName", mEventInfo.getEventName())
                .put("eventDetail", mEventInfo.getEventDetails())
                .put("candidateDates", dates);
            JSONObject request = new JSONObject()
                    .put("userId", mUserId)
                    .put("data", data);
            if (mType == OperationType.EDIT) {
                request.put("eventId", mEventInfo.getEventId());
            }
            return request;
        } catch (JSONException e) {
            Log.i(TAG, "serialize error.", e);
            return null;
        }
    }
}
