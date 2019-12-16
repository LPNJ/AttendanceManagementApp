package ReferenceEntity;

import java.util.List;

public class ReferenceDateTime {

    private final String mDate;
    private final String mTime;
    private final List<ReferenceAttendanceInfo> mAttendanceInfo;

    /**
     * コンストラクタ
     */
    public ReferenceDateTime(String mDate, String mTime, List<ReferenceAttendanceInfo> mAttendanceInfo) {
        this.mDate = mDate;
        this.mTime = mTime;
        this.mAttendanceInfo = mAttendanceInfo;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
    }

    public List<ReferenceAttendanceInfo> getAttendanceInfo() {
        return mAttendanceInfo;
    }

}
