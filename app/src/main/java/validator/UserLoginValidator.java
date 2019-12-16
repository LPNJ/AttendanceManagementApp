package validator;

import entity.UserInfo;

public class UserLoginValidator implements Validator<UserInfo> {

    private static final int ERROR_NOT_INPUT = 1;

    @Override
    public int validate(UserInfo data) {

        // 入力がない
        if (data.getUserId() == null || data.getPassword() == null ||
                data.getUserId().isEmpty() || data.getPassword().isEmpty()) {

            return ERROR_NOT_INPUT;
        }

        return SUCCESS;
    }

}
