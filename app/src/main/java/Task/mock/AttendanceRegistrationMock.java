package Task.mock;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import entity.EditableUserInfo;

public class AttendanceRegistrationMock implements AttendanceRegistrationTask {

    @Override
    public void execute(EditableUserInfo userInfo, ResultListener listener){
        listener.onResult(0);
    }

}
