package Task;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ServerResponse {
    private static final String TAG = "ServRes";
    private static final String ENCODING = "UTF-8";
    private final String mResponseBody;

    public ServerResponse(InputStream is) throws IOException {
        mResponseBody = readResponseBody(is);
    }

    private String readResponseBody(InputStream is) throws IOException {
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            isr = new InputStreamReader(is, ENCODING);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            // 発生しないはず
            throw new IllegalArgumentException("Encoding is incorrect. " + ENCODING, e);
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
        }
    }

    public JSONObject getResponseJson() {
        try {
            return new JSONObject(mResponseBody);
        } catch (JSONException e) {
            // 発生しないはず
            Log.w(TAG, "json error.", e);
            throw new IllegalStateException("json error.", e);
        }
    }
}
