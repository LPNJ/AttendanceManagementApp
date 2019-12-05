package entity;

import java.util.ArrayList;
import java.util.List;


/**
 * 候補日
 */
public class CandidateDate {
    private final String mDate; // 日付
    private final String mTime; // 時間
    private final List<AttendanceInfo> mAttendanceInfoList; //参加者一覧

    public CandidateDate(String date, String time) {
        mDate = date;
        mTime = time;
        mAttendanceInfoList = new ArrayList<>();
    }

    /**
     * この日付に参加者を追加します。
     * @param info
     */
    public void addAttendance(AttendanceInfo info) {
        mAttendanceInfoList.add(info);
    }

    /**
     * 日付 スペース 時間 で日時を返します。
     * 日付と時間の表示形式は設定時の情報を引き継ぎます。
     * 例： 12/5 11:00
     * @return
     */
    public String getDateAndTime() {
        return (mDate + " " + mTime).trim();
    }

    /**
     * 日付を返します。
     * 設定したときと同じ形式で返します。
     * @return
     */
    public String getDate() {
        return mDate;
    }

    /**
     * 時間を返します。
     * @return
     */
    public String getTime() {
        return mTime;
    }

    /**
     * 引数に指定した状態の参加者名の一覧を返します。
     * @return
     */
    public List<String> getAttendanceNames(AttendanceType type) {
        List<String> names = new ArrayList<>();
        for (AttendanceInfo info : mAttendanceInfoList) {
            if (info.getStatus() == type.mStatus) {
                names.add(info.getAttendanceName());
            }
        }
        return names;
    }
}
