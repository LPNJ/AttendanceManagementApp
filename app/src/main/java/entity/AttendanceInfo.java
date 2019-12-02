package entity;

public class AttendanceInfo extends EventInfo {

    private final String mParticipantsName;
    private final String mAttendancebutton;

    /**
     * コンストラクタ
     *
     * @param mEventName
     * @param mEventDetails
     * @param mDate
     * @param mTime
     * @param mEventNumber
     */
    public AttendanceInfo(String mEventName, String mEventDetails, String mDate, String mTime, String mEventNumber, String mParticipantsName, String mAttendancebutton) {
        super(mEventName, mEventDetails, mDate, mTime, mEventNumber);
        this.mParticipantsName = mParticipantsName;
        this.mAttendancebutton = mAttendancebutton;
    }
}
