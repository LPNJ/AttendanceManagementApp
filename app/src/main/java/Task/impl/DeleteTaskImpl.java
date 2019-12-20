package Task.impl;

import org.json.JSONObject;

import Task.DeleteTask;
import Task.EventEditTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.SimpleServerTask;
import Task.serialize.EventCreateRequest;

/**
 * Eventを削除するAPIを利用すると実際に削除されて削除された情報を取得できないので
 * {@link entity.EventInfo}の状態だけ変えて論理的に削除する。
 */
public class DeleteTaskImpl
        extends SimpleServerTask<EventCreateRequest> implements DeleteTask {
    private EventEditTask mEventEditTask;

    public DeleteTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.DELETE_EVENT, listener);
        mEventEditTask = new EventEditTaskImpl(listener);

    }

    @Override
    protected JSONObject createJson(EventCreateRequest... v) {
        return v[0].toJson(EventCreateRequest.CreateType.EDIT);
    }

    @Override
    public void execute(EventCreateRequest request, ResultListener listener) {
        mEventEditTask.execute(request, listener);
    }
}
