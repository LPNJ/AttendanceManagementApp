package entity;

/**
 * ログインユーザーの情報を保持するシングルトンクラス
 */
public class LoginUser {
    /** 唯一のログインユーザーIDインスタンス */
    private static final LoginUser mInstance = new LoginUser();
    /** ユーザーID */
    private String mLoginUserId;

    /**
     * シングルトンなので private
     */
    private LoginUser() {
    }

    /**
     * ログインユーザー情報インスタンスを取得する
     * @return
     */
    public static LoginUser getInstance() {
        return mInstance;
    }

    /**
     * ログインユーザーIDを設定する
     * @param loginUserId
     */
    public void setLoginUserId(String loginUserId) {
        mLoginUserId = loginUserId;
    }

    /**
     * ログインユーザーIDを設定する
     * @return ログインユーザーID
     */
    public String getLoginUserId() {
        return mLoginUserId;
    }

}
