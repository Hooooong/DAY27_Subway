package com.hooooong.subway.model.realtimestation;

/**
 * Created by Android Hong on 2017-10-21.
 */
public class RealTimeStation {
    private ErrorMessage errorMessage;

    private RealTimeArrivalList[] realtimeArrivalList;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RealTimeArrivalList[] getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(RealTimeArrivalList[] realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }

    @Override
    public String toString() {
        return "ClassPojo [errorMessage = " + errorMessage + ", realtimeArrivalList = " + realtimeArrivalList + "]";
    }
}
