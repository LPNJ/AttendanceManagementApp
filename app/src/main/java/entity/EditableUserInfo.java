package entity;

public class EditableUserInfo extends UserInfo {
    private final String mConfirmationPassword;

    /**
     * コンストラクタ
     */
    public EditableUserInfo(String userId, String password, String confirmationPassword) {
        super(userId, password);
        mConfirmationPassword = confirmationPassword;
    }
    public String getConfirmationPassword() {
        return mConfirmationPassword;
    }
}
