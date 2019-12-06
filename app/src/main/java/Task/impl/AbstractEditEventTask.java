package Task.impl;

import org.json.JSONObject;

import Task.ServerTask;
import Task.ResultListener;
import Task.ServerRequest;
import Task.serialize.EventCreateRequest;
import Task.serialize.EventCreateResponse;

/**
 * イベント新規作成とイベント編集のAPIが継承する
 */
abstract class AbstractEditEventTask extends ServerTask<EventCreateRequest,EventCreateResponse> {
    protected AbstractEditEventTask(ResultListener listener) {
        super(ServerRequest.RequestType.EDIT_EVENT, listener);
    }

    @Override
    protected EventCreateResponse parseJson(JSONObject json) {
        if (json == null) {
            return new EventCreateResponse(-1, "");
        }
        return EventCreateResponse.parseJson(json);
    }
}
