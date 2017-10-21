package com.hooooong.subway.model;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class Const {
    /**
     * 지하철 호선 정보
     *
     * STATION_LINE 사용할 때 뒤에 1~9: 1~9호선, I: 인천1호선, K: 경의중앙선, B: 분당선, A: 공항철도, G: 경춘선, S:신분당선, SU:수인선 을 붙여야 한다.
     */
    public static final String STATION_LINE = "http://openapi.seoul.go.kr:8088/53716a524168393235356f44476e77/json/SearchSTNBySubwayLineService/1/120/";

    /**
     * 역의 정보
     *
     * STATION_INFO 사용할 때 뒤에 "역 이름" 을 붙여야 한다.
     */
    public static final String STATION_INFO = "http://swopenAPI.seoul.go.kr/api/subway/53716a524168393235356f44476e77/json/stationInfo/0/10/";

    /**
     * 실시간 도착 정보
     *
     * STATION__RT 를 사용할 때 뒤에"역 이름" 을 붙여야 한다.
     */
    public static final String STATION_RT = "http://swopenapi.seoul.go.kr/api/subway/53716a524168393235356f44476e77/json/realtimeStationArrival/0/20/";

    /**
     * http://data.seoul.go.kr/openinf/openapiview.jsp?infId=OA-110&tMenu=11
     */
    public static final String STATION_TIMETABLE = "http://openapi.seoul.go.kr:8088/53716a524168393235356f44476e77/json/SearchSTNTimeTableByFRCodeService/1/5/";

    public static final String CONNECTION_ERROR = "999";

    public static final String KEY_STATION_LINE = "STATION_LINE";
    public static final String KEY_STATION_NAME= "STATION_NAME";
}
