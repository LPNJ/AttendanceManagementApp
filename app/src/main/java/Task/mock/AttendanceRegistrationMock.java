package Task.mock;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.serialize.AttendanceRequest;

public class AttendanceRegistrationMock implements AttendanceRegistrationTask {

    @Override
    public void execute(AttendanceRequest request, ResultListener listener) {
        listener.onResult(0);
    }
}
