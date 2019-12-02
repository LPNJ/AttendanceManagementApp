package entity;

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
}
