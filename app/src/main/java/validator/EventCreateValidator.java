package validator;

import android.util.Log;

import entity.EventInfo;

public class EventCreateValidator implements Validator<EventInfo> {

    private static final int ERROR_NOT_INPUT = 1;
    private static final int ERROR_EVENTNAME_TOO_LONG = 8;
    private static final int ERROR_DETAILS_TOO_LONG = 9;


    // 入力長の閾値
    private static final int MAX_EVENTNAME_LENGTH = 50;
    private static final int MAX_DETAILS_LENGTH = 1000;

    @Override
    public int validate(EventInfo data) {
        Log.i("eventName",data.getEventName());
        // 入力がない
        if (data.getEventName() == null || data.getEventName().isEmpty() || data.getCandidateDates().isEmpty()) {
            return ERROR_NOT_INPUT;
        }

        // 入力が超過、不足
        int eventNameError = validateLength(data.getEventName(),MAX_EVENTNAME_LENGTH,ERROR_EVENTNAME_TOO_LONG);
        if (eventNameError != SUCCESS) {
            return eventNameError;
        }

        // 入力が超過、不足
        int eventDetailsError = validateLength(data.getEventDetails(),MAX_DETAILS_LENGTH,ERROR_DETAILS_TOO_LONG);
        if (eventDetailsError != SUCCESS) {
            return eventDetailsError;
        }

        return SUCCESS;
    }

    private int validateLength(String value, int max, int maxError) {
        if (value.length() > max) {
            return maxError;
        }
        return SUCCESS;
    }

}
