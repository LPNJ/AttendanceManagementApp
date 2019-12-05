package Task.mock;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import entity.AttendanceRequest;
import entity.EditableUserInfo;

public class AttendanceRegistrationMock implements AttendanceRegistrationTask {

    @Override
    public void execute(AttendanceRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
