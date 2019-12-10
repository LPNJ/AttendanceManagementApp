package Task;

import android.os.AsyncTask;
import org.json.JSONObject;
import java.io.IOException;

/**
 * 全タスククラスの基底となる抽象クラス
 * @param <V>
 * @param <R>
 */
public abstract class AbstractAsyncTask<V, R> extends AsyncTask<V, Void, JSONObject> {
    private final ServerRequest.RequestType mRequestType;
    private final ResultListener mListener;
    /**
     * 子クラスはpublicにしよう
     * @param listener
     */
    protected AbstractAsyncTask(ServerRequest.RequestType type, ResultListener listener) {
        mRequestType = type;
        mListener = listener;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        R r = parseJson(result);
        mListener.onResult(r);
    }

    @Override
    protected JSONObject doInBackground(V... v) {
        JSONObject requestJson = createJson(v);

        ServerRequest request = new ServerRequest(mRequestType, requestJson);
        try {
            ServerResponse response = new ServerConnector().request(request);
            return response.getResponseJson();
        } catch (IOException e) {
            return null;
        }
    }

    protected abstract JSONObject createJson(V... v);

    protected abstract R parseJson(JSONObject json);
}
