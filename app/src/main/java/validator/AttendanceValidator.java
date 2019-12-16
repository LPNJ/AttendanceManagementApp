package validator;

import android.util.Log;

import entity.AttendanceInfo;
import entity.EditableUserInfo;

import static entity.AttendanceType.valueOf;

public class AttendanceValidator implements Validator<AttendanceInfo>{

    private static final int ERROR_NOT_INPUT = 1;
    private static final int ERROR_USER_NAME_LONG = 10;

    @Override
    public int validate(AttendanceInfo data) {
        Log.i("validate_number",data.getAttendanceName());
        if (data == null) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " data is null.");
        }
        // 入力がない
        if (data.getAttendanceName() == null ||
                data.getAttendanceName().isEmpty() )
            return ERROR_NOT_INPUT;
        if(data.getStatus() != 0 &&  data.getStatus() != 1 && data.getStatus() != 2) {
            return ERROR_NOT_INPUT;
        }
        // 入力が超過
        if (data.getAttendanceName().length() > 20) {
            return ERROR_USER_NAME_LONG;
        }
        return SUCCESS;
    }
}
