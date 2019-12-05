package entity;

public class Candidate {

    private final String mDate;
    private final String mTime;
    private final String AttendanceName;

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

    public Candidate(String mDate, String mTime, String attendanceName) {
        this.mDate = mDate;
        this.mTime = mTime;
        AttendanceName = attendanceName;
    }

}
