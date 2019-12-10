package entity;

/**
 * 参加者の参加状況
 * 0：参加
 * 1：不明
 * 2：不参加
 */
public enum  AttendanceType {
    // TODO 修正するかも・・・
    // 現状listとかで上から選択されたポジションをAttendanceとかとする。
    // 参加
    ATTENDANCE(0),
    // 不明
    UNKNOWN(1),
    // 不参加
    ABSENCE(2);

    final int mStatus;
    AttendanceType(int i) {
        mStatus = i;
    }

    /**
     * 出欠状況の選択肢から取得した位置を
     * 出欠状況({@link AttendanceType})に変換します。
     * @param status
     * @return
     */
    public static AttendanceType valueOf(int status) {
        for (AttendanceType type : AttendanceType.values()) {
            if (status == type.mStatus) {
                return type;
            }
        }
        return ABSENCE;
    }

    public int toInt() {
        return mStatus;
    }
}
