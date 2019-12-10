package Task.impl;

import org.json.JSONObject;

import Task.AbstractAsyncTask;
import Task.ParticipantEventTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.EventInfo;

public class ParticipantEventTaskImpl extends AbstractAsyncTask<EventInfo, Integer> implements ParticipantEventTask {
    ParticipantEventTaskImpl(ResultListener listener) {
        super(ServerRequest.RequestType.GET_ALL_ATTEND_EVENT, listener);
    }
    @Override
    protected JSONObject createJson(EventInfo... v) {
       // return v[0].toJsonOnlyUserId();
        return null;
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        // TODO 実装する必要ある
        return null;
    }

    @Override
    public void execute(String s, ResultListener listener) {

    }
}
