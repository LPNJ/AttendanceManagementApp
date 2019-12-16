package Task.impl;

import org.json.JSONObject;

import Task.ServerTask;
import Task.EventEditTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.EventCreateRequest;
import entity.EventInfo;
import result.EventResult;

public class EventEditTaskImpl extends AbstractEditEventTask implements EventEditTask {
    public EventEditTaskImpl(ResultListener listener) {
        super(listener);
    }

    @Override
    protected JSONObject createJson(EventCreateRequest... v) {
        return v[0].toJson(EventCreateRequest.CreateType.EDIT);
    }

    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        super.execute(request);
    }
}
