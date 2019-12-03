package Task.impl;

import android.os.AsyncTask;

import Task.RegisterTask;
import Task.ResultListener;
import entity.EditableUserInfo;

/**
 * RegisterTask の本来実装すべき処理を実装する処理を記述する。
 */
public class RegisterTaskImpl extends AsyncTask<String, Integer, Integer> implements RegisterTask {
    @Override
    public void execute(EditableUserInfo userInfo, ResultListener resultListener) {
        super.execute();
    }

    @Override
    protected Integer doInBackground(String... integers) {
        return null;
    }
}
