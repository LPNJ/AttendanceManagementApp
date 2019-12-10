package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.DeleteTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.DeleteEventRequest;

public class DeleteTaskImpl extends AbstractAsyncTask<DeleteEventRequest, Integer> implements DeleteTask {

    DeleteTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.DELETE_EVENT, listener);
    }

    @Override
    protected JSONObject createJson(DeleteEventRequest... v) {
        return v[0].toJson();
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        return json.optInt("error", 1);
    }

    @Override
    public void execute(DeleteEventRequest request, ResultListener listener) {
        super.execute(request);
    }
}
