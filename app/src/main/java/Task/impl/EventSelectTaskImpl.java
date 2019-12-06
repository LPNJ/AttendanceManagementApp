package Task.impl;

import Task.EventSelectTask;
import Task.ResultListener;
import Task.ServerRequest;

public class EventSelectTaskImpl extends AbstractSelectEventTask implements EventSelectTask {
    public EventSelectTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_EVENT, listener);
    }

    @Override
    public void execute(String userId, ResultListener listener) {
        super.execute(userId);
    }
}
