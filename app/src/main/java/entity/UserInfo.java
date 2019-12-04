package entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ユーザー情報を保持するエンティティ。<br>
 * ユーザーIDとパスワードを保持する
 */
public class UserInfo {
    private final String mUserId;
    private final String mPassword;

    /**
     * コンストラクタ
     */
    public UserInfo(String userId, String password) {
        mUserId = userId;
        mPassword = password;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getPassword() {
        return mPassword;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject()
                    .put("userId", mUserId)
                    .put("password", mPassword);
        } catch (JSONException e) {
            // 発生しないはず。
            return null;
        }
    }

    public JSONObject toJsonOnlyUserId() {
        try {
            return new JSONObject()
                    .put("userId", mUserId);
        } catch (JSONException e) {
            // 発生しないはず。
            return null;
        }
    }
}
