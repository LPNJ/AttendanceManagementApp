package Task.impl;

import org.json.JSONObject;

import Task.DeleteTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.DeleteEventRequest;
<<<<<<< HEAD
import Task.SimpleServerTask;

public class DeleteTaskImpl
        extends SimpleServerTask<DeleteEventRequest> implements DeleteTask {
=======

public class DeleteTaskImpl extends AbstractAsyncTask<DeleteEventRequest, Integer> implements DeleteTask {
>>>>>>> OJT: 出欠イベントの参照まで。

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
