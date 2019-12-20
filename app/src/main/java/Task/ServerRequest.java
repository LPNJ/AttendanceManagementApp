package Task;

import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ServerRequest {

    private static final String TAG = "ServReq";

    enum MethodType {
        POST("POST", true),
        GET("GET", true),
        DELETE("DELETE", true);

        private final String mMethod;
        private final boolean isDoOutput;

        MethodType(String method, boolean doOutput) {
            mMethod = method;
            isDoOutput = doOutput;
        }

        String getMethod() {
            return mMethod;
        }

        boolean isDoOutput() {
            return isDoOutput;
        }
    }

    public enum RequestType {
        LOGIN("login", MethodType.POST),
        LOGOUT("logout", MethodType.POST),
        REGISTER_USER("user", MethodType.POST),
        EDIT_EVENT("event", MethodType.POST),
        DELETE_EVENT("event", MethodType.DELETE),
        GET_ALL_EVENT("eventlist", MethodType.GET),
        REGISTER_ATTENDANCE("attendance", MethodType.POST),
        //GET_ALL_ATTENDANCE_EVENT("attendancelist", MethodType.GET);
        GET_ALL_ATTENDANCE_EVENT("attendanceeventlist", MethodType.GET);

        private final String mUrl;
        private final MethodType mMethodType;
        private static final String IP_ADDRESS = "133.139.115.184";
        private static final String PORT = "8080";
        RequestType(String endPoint, MethodType type) {
            mUrl = "http://" + IP_ADDRESS + ":" + PORT + "/event/api/" + endPoint;
            mMethodType = type;
        }

        String getUrl() {
            return mUrl;
        }

        MethodType getMethodType() {
            return mMethodType;
        }
    }
    private static final String ENCODING = "UTF-8";
    private final RequestType mRequestType;
    private final JSONObject mRequestBody;

    public ServerRequest(RequestType type, JSONObject requestBody) {
        mRequestType = type;
        mRequestBody = requestBody;
    }

    public ServerRequest(RequestType type) {
        mRequestType = type;
        mRequestBody = null;
    }

    public String getUrl() {
        return mRequestType.getUrl();
    }

    public String getMethod() {
        return mRequestType.getMethodType().getMethod();
    }

    public boolean isDoOutput() {
        return mRequestType.getMethodType().isDoOutput();
    }

    public void writeRequestBody(OutputStream os) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(os, true, ENCODING);
            ps.print(mRequestBody.toString());
        } catch (UnsupportedEncodingException e) {
            // 発生しないはず
            throw new IllegalArgumentException("Encoding is incorrect. "+ ENCODING, e);
        } finally {
            Log.i(TAG, "json: " + mRequestBody.toString());
            if (ps == null) return;
            ps.close();
        }
    }
}
