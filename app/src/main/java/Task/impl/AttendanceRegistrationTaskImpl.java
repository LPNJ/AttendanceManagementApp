package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.AttendanceRegistrationTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.AttendanceRequest;

public class AttendanceRegistrationTaskImpl extends AbstractAsyncTask<AttendanceRequest, Integer>
        implements AttendanceRegistrationTask {

    public AttendanceRegistrationTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.REGISTER_ATTENDANCE, listener);
    }

    @Override
    protected JSONObject createJson(AttendanceRequest... v) {
        return v[0].toJson();
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        return json.optInt("error", 1);
    }

    @Override
    public void execute(AttendanceRequest request, ResultListener listener) {
        execute(request);
    }
}
