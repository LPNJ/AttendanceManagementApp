package Task.impl;

import Task.ParticipantEventTask;
import Task.ResultListener;
import Task.ServerRequest;

public class ParticipantEventTaskImpl extends AbstractSelectEventTask implements ParticipantEventTask {
    public ParticipantEventTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_ATTENDANCE_EVENT , listener);
    }

    @Override
    public void execute(String s, ResultListener listener) {
        super.execute(s);
    }
}
