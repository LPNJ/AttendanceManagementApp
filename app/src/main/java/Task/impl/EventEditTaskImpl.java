package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.EventEditTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.EventCreateRequest;
import entity.EventInfo;
import result.EventResult;

public class EventEditTaskImpl extends AbstractAsyncTask<EventInfo, EventResult> implements EventEditTask {
    public EventEditTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.EDIT_EVENT, listener);
    }

    @Override
    protected JSONObject createJson(EventInfo... v) {
        return null;
    }

    @Override
    protected EventResult parseJson(JSONObject json) {
        return null;
    }

    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {

    }
}
