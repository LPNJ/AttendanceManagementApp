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
<<<<<<< HEAD
    public enum CreateType {
        CREATE, EDIT;
=======
    /** ログに出力するタグ名 */
    private static final String TAG = EventCreateRequest.class.getSimpleName();
    /**
     * 操作の種類
     */
    public enum OperationType {
        CREATE,
        EDIT,
        ;
>>>>>>> OJT: 出欠イベントの参照まで。
    }

    /** TODO: これの役割が分かりません */
    private static final String SEPARATE_WORD = "#";
    private final String mUserId;
    // 変更の時のみEventIDを指定する。
    private final EventInfo mEventInfo;
<<<<<<< HEAD
=======
    private final OperationType mType;
>>>>>>> OJT: 出欠イベントの参照まで。

    /**
     * コンストラクタ
     * @param userId ユーザーID
     * @param eventInfo イベント情報
     * @param type イベントの登録か、更新か
     */
<<<<<<< HEAD
    public EventCreateRequest(String userId, EventInfo eventInfo) {
=======
    public EventCreateRequest(String userId, EventInfo eventInfo, OperationType type) {
>>>>>>> OJT: 出欠イベントの参照まで。
        mUserId = userId;
        mEventInfo = eventInfo;
    }

<<<<<<< HEAD
    public JSONObject toJson(CreateType type) {
        try {
            JSONObject request = new JSONObject()
                    .put("userId", mUserId)
                    .put("data", mEventInfo.toJson());
            if (type == CreateType.EDIT) {
=======
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
>>>>>>> OJT: 出欠イベントの参照まで。
                request.put("eventId", mEventInfo.getEventId());
            }
            return request;
        } catch (JSONException e) {
            Log.i(TAG, "serialize error.", e);
            return null;
        }
    }
}
