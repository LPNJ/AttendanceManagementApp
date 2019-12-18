package Task;

import android.util.Log;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ServerConnector {
    private static final String TAG = "ServCon";
    private static final int CONNECTION_TIMEOUT = 30 * 1000;
    private static final int READ_TIMEOUT = 30 * 1000;

    public ServerConnector() {}

    HttpURLConnection getUrlConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /**
     * MT用に外だし。
     * @param
     * @return
     * @throws IOException
     */
    HttpURLConnection getHttpURLConnection(String urlStr) throws IOException {
        try {
            URL url = new URL(urlStr);
            return getUrlConnection(url);
        } catch (MalformedURLException e) {
            // 発生しないはず
            throw new IllegalArgumentException("URL is incorrect. " + urlStr, e);
        } catch (IOException e) {
            Log.e(TAG, "getURLConnection error.", e);
            throw e;
        }
    }

    private void setMethod(HttpURLConnection con, String methodType) {
        try {
            con.setRequestMethod(methodType);
        } catch (ProtocolException e) {
            // 発生しないはず
            throw new IllegalArgumentException(
                    "Protocol is incorrect. " + methodType, e);
        }
    }

    public ServerResponse request(ServerRequest request) throws IOException {

        HttpURLConnection con = getHttpURLConnection(request.getUrl());
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(READ_TIMEOUT);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        setMethod(con, request.getMethod());
        con.setDoOutput(request.isDoOutput());
        con.setDoInput(true);


        OutputStream requestOs = null;
        InputStream responseIn = null;
        try {
            con.connect();

            // リスエストを作成
            if (request.getMethod().equals(ServerRequest.MethodType.GET.getMethod())) {
                requestOs = con.getOutputStream();
                request.writeRequestBody(requestOs);
            }

            int statusCode = con.getResponseCode();

            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("status code is not 200. " + statusCode);
            }

            responseIn = con.getInputStream();
            return new ServerResponse(responseIn);
        } catch (IOException e) {
            Log.e(TAG, "connection error.", e);
            throw new IOException(e);
        } finally {
            con.disconnect();
            closeQuietly(requestOs);
            closeQuietly(responseIn);
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "failed to close.", e);
        }
    }
}
