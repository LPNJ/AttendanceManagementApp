package Task;

import org.json.JSONObject;

import Task.ResultListener;
import Task.ServerRequest;
import Task.ServerTask;

public abstract class SimpleServerTask<V> extends ServerTask<V, Integer> {
    /**
     * 子クラスはpublicにしよう
     * @param listener
     */
    protected SimpleServerTask(ServerRequest.RequestType type, ResultListener listener) {
        super(type, listener);
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        if (json == null) {
            return -1;
        }
        return json.optInt("error", -1);
    }
}
