package validator;

import entity.EventInfo;

public abstract class EventCreateValidator implements Validator<EventInfo> {

    private static final int ERROR_NOT_INPUT = 1;

    // 入力長の閾値
    private static final int MAX_EVENTNAME_LENGTH = 50;
    private static final int MAX_EVENTDETAILS_LENGTH = 1000;
    private static final int MIN_EVENTNAME_LENGTH = 1;
    private static final int MIN_EVENTDETAILS_LENGTH = 0;

    @Override
    public int validate(EventInfo data) {
        // 入力がない
        if (data.getEventName() == null || data.getEventName().isEmpty() || data.getCandidateDates().isEmpty()) {
            return ERROR_NOT_INPUT;
        }

        // 入力が超過、不足
        int eventNameError = validateLength(data.getEventName(),MIN_EVENTNAME_LENGTH, MAX_EVENTNAME_LENGTH,MIN_EVENTDETAILS_LENGTH , MAX_EVENTDETAILS_LENGTH);
        if (eventNameError != SUCCESS) {
            return eventNameError;
        }

        int eventDetailsError = validateLength(data.getEventName(),MIN_EVENTNAME_LENGTH, MAX_EVENTNAME_LENGTH,MIN_EVENTDETAILS_LENGTH , MAX_EVENTDETAILS_LENGTH);
        if (eventDetailsError != SUCCESS) {
            return eventDetailsError;
        }

        return SUCCESS;
    }

    private int validateLength(String value, int min, int max, int minError, int maxError) {
        if (value.length() > min) {
            return minError;
        }
        if (value.length() < max) {
            return maxError;
        }
        return SUCCESS;
    }

}
