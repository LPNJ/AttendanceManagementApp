package entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 参加者の情報
 */
public class AttendanceInfo {
    private final String mAttendanceName; // 名前
    private final AttendanceType mType;  // 状況

    public AttendanceInfo(String attendanceName, AttendanceType type) {
        mAttendanceName = attendanceName;
        mType = type;
    }

    /**
     * 登録者の名前を取得します。
     * @return
     */
    public String getAttendanceName() {
        return mAttendanceName;
    }

    /**
     * 登録者の状態を0～2で返します。
     * 0~2については詳しくは{@link AttendanceType}をご覧ください。
     * @return
     */
    public int getStatus() {
        return mType.toInt();
    }

    public JSONObject toJson() throws JSONException {
        return new JSONObject()
                .put("attendanceName", mAttendanceName)
                .put("status", mType.mStatus);
    }

}
