package Task.impl;

import org.json.JSONObject;

import Task.DeleteTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.DeleteEventRequest;
import Task.SimpleServerTask;

public class DeleteTaskImpl
        extends SimpleServerTask<DeleteEventRequest> implements DeleteTask {

    DeleteTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.DELETE_EVENT, listener);
    }

    @Override
    protected JSONObject createJson(DeleteEventRequest... v) {
        return v[0].toJson();
    }

    @Override
    public void execute(DeleteEventRequest request, ResultListener listener) {
        super.execute(request);
    }
}
