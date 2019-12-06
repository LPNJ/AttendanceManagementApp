package Task.impl;

import org.json.JSONObject;

import Task.EventCreateTask;
import Task.ResultListener;
import Task.serialize.EventCreateRequest;

public class EventCreateTaskImpl extends AbstractEditEventTask implements EventCreateTask {
    public EventCreateTaskImpl(ResultListener listener) {
        super(listener);
    }

    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        super.execute(request);
    }

    @Override
    protected JSONObject createJson(EventCreateRequest... v) {
        return v[0].toJson(EventCreateRequest.CreateType.CREATE);
    }
}
