package com.hooooong.subway.model.station;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class Station {
    private ErrorMessage errorMessage;

    private StationList[] stationList;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StationList[] getStationList() {
        return stationList;
    }

    public void setStationList(StationList[] stationList) {
        this.stationList = stationList;
    }

    @Override
    public String toString() {
        return "ClassPojo [errorMessage = " + errorMessage + ", stationList = " + stationList + "]";
    }
}