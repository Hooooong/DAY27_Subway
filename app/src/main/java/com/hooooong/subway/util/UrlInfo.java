package com.hooooong.subway.util;

import com.hooooong.subway.model.Const;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class UrlInfo {
    /**
     * 지하철 호선 정보를 반환하는 URL
     *
     * @param lineNumber : 1~9: 1~9호선, I: 인천1호선, K: 경의중앙선, B: 분당선, A: 공항철도, G: 경춘선, S:신분당선, SU:수인선
     * @return
     */
    public static String getLineUrl(String lineNumber ){
        return Const.STATION_LINE + lineNumber;
    }

    /**
     * 역의 정보를 반환하는 URL
     *
     * @param stationName : 역 이름
     * @return
     */
    public static String getStationInfoUrl(String stationName){
        return Const.STATION_DETAIL_INFO + stationName;

    }

    /**
     * 실시간 도착 정보를 반환하는 URL
     *
     * @param stationName : 역 이름
     * @return
     */
    public static String getStationRtUrl(String stationName){
        return Const.STATION_RT + stationName;
    }
}
