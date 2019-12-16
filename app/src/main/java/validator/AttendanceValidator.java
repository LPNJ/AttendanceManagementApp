package validator;

import entity.AttendanceInfo;
import entity.EditableUserInfo;

public class AttendanceValidator implements Validator<AttendanceInfo>{

    private static final int ERROR_NOT_INPUT = 1;
    private static final int ERROR_PASS_NAME_LONG = 7;

    @Override
    public int validate(AttendanceInfo data) {
        if (data == null) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " data is null.");
        }
        // 入力がない
        if (data.getAttendanceName() == null ||
                data.getAttendanceName().isEmpty() || data.getAttendanceName().isEmpty() ) {
            return ERROR_NOT_INPUT;
        }
        // 入力が超過
        if (data.getAttendanceName().length() > 20) {
            return ERROR_PASS_NAME_LONG;
        }
        return SUCCESS;
    }
}
