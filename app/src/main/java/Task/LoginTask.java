package Task;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.ojttask.MainActivity;
import com.example.ojttask.MenuActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginTask extends AsyncTask<String,Integer,Integer> {

    private final MainActivity mLoginActivity;

    private final Map<Integer, String> mErrorMessage = new HashMap<>();

    /**
     * コンストラクタ
     * @param loginActivity ログイン画面のアクティビティ
     */
    public LoginTask(final MainActivity loginActivity) {
        mLoginActivity = loginActivity;
        mErrorMessage.put(1, "ログインに失敗しました");
    }


    @Override
    protected Integer doInBackground(String... loginParameter) {
        // サーバーとの通信しないといけない
        HttpURLConnection con = null;

        InputStream is = null;

        String urlStr = "http://host.domain/login";
//        String urlStr = "https://www.google.co.jp";

        try {
        URL url = new URL(urlStr);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
    } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            con.connect();

            if(con.getResponseCode() != 200){
                return 0;
            } else{
                return 0;
            }

            //is = con.getInputStream();
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject)jsonParser.parse(
//                    new InputStreamReader(is, "UTF-8"));
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
        } finally {
            if(con != null){
                con.disconnect();
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;

        // JSONのリクエストを作る
        // 通信用のコネクションの確立
        // リクエストを送る
        // レスポンスを受け取る
        // レスポンスからエラーコードを取得
        // エラーコードを返す
    }

    @Override
    protected void onPostExecute(Integer errorCode) {
        if (0 == errorCode) {
            mLoginActivity.startActivity(new Intent(mLoginActivity, MenuActivity.class));
            return;
        }
        // 失敗時：ダイアログを出す
        else {
            new AlertDialog.Builder(mLoginActivity)
                    .setMessage(mErrorMessage.get(errorCode))
                    .setPositiveButton("OK", null)
                    .create()
                    .show();
        }
    }
}
