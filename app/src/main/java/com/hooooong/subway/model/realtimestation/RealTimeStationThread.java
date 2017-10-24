package com.hooooong.subway.model.realtimestation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hooooong.subway.model.Const;
import com.hooooong.subway.util.Remote;
import com.hooooong.subway.util.UrlInfo;
import com.hooooong.subway.view.detail.adapter.StationAdapter;

/**
 * Created by Android Hong on 2017-10-20.
 */

public class RealTimeStationThread {

    private boolean runFlag = true;

    private Context context;
    private String stationName;
    private StationRTThread thread;
    private StationAdapter.StationListener stationListener;

    public RealTimeStationThread(Context context) {
        stationListener = (StationAdapter.StationListener) context;
        this.context = context;
    }

    public void set(String stationName) {
        runFlag = true;
        this.stationName = stationName;
        thread = new StationRTThread();
        thread.start();
    }

    private class StationRTThread extends Thread {
        @Override
        public void run() {
            while (runFlag) {
                String result = Remote.getData(UrlInfo.getStationRtUrl(stationName));
                Log.e("RealTimeStation : " , result);
                if (!Const.CONNECTION_ERROR.equals(result)) {
                    final RealTimeStation realTimeStation = parsingJson(result);
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stationListener.changeData(realTimeStation, System.currentTimeMillis());
                        }
                    });
                }
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStop() {
        runFlag = false;
    }

    private RealTimeStation parsingJson(String result) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(result, RealTimeStation.class);
        } catch (Exception e) {
            // JSONParsing 오류 처리!
            return new RealTimeStation();
        }
    }
}
