package ReferenceEntity;

import java.util.List;

public class ReferenceAttendanceInfo {

    private final String mUserName;

    public enum AttendanceType {
        ATTENDANCE(0),
        UNKNOWN(1),
        ABSENCE(2);
        final int mStatus;
        AttendanceType(int i) {
            mStatus = i;
        }
        int toInt() {
            return mStatus;
        }
    }

    /**
     * コンストラクタ
     */
    public ReferenceAttendanceInfo(String mUserName) {
        this.mUserName = mUserName;
    }

    public String mUserName() {
        return mUserName;
    }



}
