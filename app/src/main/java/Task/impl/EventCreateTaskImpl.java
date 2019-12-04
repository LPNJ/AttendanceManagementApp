package Task.impl;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ojttask.CreateActivity;
import com.example.ojttask.NumberingActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Task.AbstractAsyncTask;
import Task.ResultListener;
import Task.ServerRequest;
import entity.EventInfo;

public class EventCreateTaskImpl extends AbstractAsyncTask<EventInfo,Integer> {

    /**
     * 子クラスはpublicにしよう
     *
     * @param type
     * @param listener
     */
    protected EventCreateTaskImpl(ServerRequest.RequestType type, ResultListener listener) {
        super(type, listener);
    }

    @Override
    protected JSONObject createJson(EventInfo... v) {
        return null;
    }

    @Override
    protected Integer parseJson(JSONObject json) {
        return null;
    }

//    private final CreateActivity mCreateActivity;
//    private final Map<Integer, String> mErrorMessage = new HashMap<>();
//
//    public EventCreateTaskImpl(CreateActivity mCreateActivity) {
//        this.mCreateActivity = mCreateActivity;
//        mErrorMessage.put(1, "イベントの作成に失敗しました");
//    }
//
//    @Override
//    protected Integer doInBackground(EventInfo... eventInfo) {
//        return 0;
//    }
//
//    @Override
//    protected void onPostExecute(Integer errorCode) {
//        if (0 == errorCode) {
//            mCreateActivity.startActivity(new Intent(mCreateActivity, NumberingActivity.class));
//
//            return;
//        }
//        // 失敗時：ダイアログを出す
//        else {
//            new AlertDialog.Builder(mCreateActivity)
//                    .setMessage(mErrorMessage.get(errorCode))
//                    .setPositiveButton("OK", null)
//                    .create()
//                    .show();
//        }
//    }


}
