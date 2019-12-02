package Task;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ojttask.MainActivity;
import com.example.ojttask.MenuActivity;
import com.example.ojttask.RegisterActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import entity.EditableUserInfo;
import entity.UserInfo;

public class RegisterTask extends AsyncTask<UserInfo,Integer,Integer> {

    private final RegisterActivity mRegisterActivity;

    private final Map<Integer, String> mErrorMessage = new HashMap<>();

    /**
     * コンストラクタ
     * @param registerActivity ログイン画面のアクティビティ
     */
    public RegisterTask(final RegisterActivity registerActivity) {
        mRegisterActivity = registerActivity;
        mErrorMessage.put(1, "すでにユーザーが存在します");
    }

    @Override
    protected Integer doInBackground(UserInfo... userInfo) {



        return 1;
    }

    @Override
    protected void onPostExecute(Integer errorCode) {
        if (0 == errorCode) {
            mRegisterActivity.startActivity(new Intent(mRegisterActivity, MainActivity.class));
            return;
        }
        // 失敗時：ダイアログを出す
        else {
            new AlertDialog.Builder(mRegisterActivity)
                    .setMessage(mErrorMessage.get(errorCode))
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
        }
    }
}
