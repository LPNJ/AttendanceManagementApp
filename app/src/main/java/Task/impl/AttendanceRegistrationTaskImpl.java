package Task.impl;

import org.json.JSONObject;

import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.AttendanceRequest;
import Task.SimpleServerTask;

public class AttendanceRegistrationTaskImpl extends SimpleServerTask<AttendanceRequest>
        implements AttendanceRegistrationTask {

    public AttendanceRegistrationTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.REGISTER_ATTENDANCE, listener);
    }

    @Override
    protected JSONObject createJson(AttendanceRequest... v) {
        return v[0].toJson();
    }

    @Override
    public void execute(AttendanceRequest request, ResultListener listener) {
        execute(request);
    }
}
