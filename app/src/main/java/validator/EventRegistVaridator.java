package validator;

import entity.AttendanceInfo;

public class EventRegistVaridator implements Validator<String> {

    private static final int ERROR_PASS_NAME_LONG = 4;

    @Override
    public int validate(String data) {
        // 入力が超過
        if (!data.equals("0011")) {
            return ERROR_PASS_NAME_LONG;
        }
        return SUCCESS;
    }

}
