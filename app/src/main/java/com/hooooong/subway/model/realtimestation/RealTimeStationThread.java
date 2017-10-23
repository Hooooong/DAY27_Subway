package com.hooooong.subway.model.realtimestation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.hooooong.subway.util.Remote;
import com.hooooong.subway.util.UrlInfo;
import com.hooooong.subway.view.detail.adapter.StationAdapter;

/**
 * Created by Android Hong on 2017-10-20.
 */

public class RealTimeStationThread {


    private boolean runFlag = true;
    private Context context;

    private static RealTimeStationThread realTimeStationThread;
    private String stationName;
    private StationRTThread thread;
    private StationAdapter.StationListener stationListener;

    private RealTimeStationThread(Context context) {
        stationListener = (StationAdapter.StationListener) context;
        this.context = context;
    }

    public static RealTimeStationThread getInstance(Context context) {
        if (realTimeStationThread == null) {
            realTimeStationThread = new RealTimeStationThread(context);
        }
        return realTimeStationThread;
    }

    public void set(String stationName) {
        runFlag = true;
        this.stationName = stationName;
        thread = new StationRTThread();
        thread.start();
    }

    class StationRTThread extends Thread {
        @Override
        public void run() {
            while (runFlag) {

                String result =  Remote.getData(UrlInfo.getStationRtUrl(stationName));

                Log.e("RealTimeStationThread", "onPostExecute() 호출, " + result );
                final RealTimeStation realTimeStation = parsingJson(result);

                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stationListener.changeData(realTimeStation, System.currentTimeMillis());
                    }
                });

                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    Log.e("에러", "에러");
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
        }catch(Exception e){
            return new RealTimeStation();
        }
    }
}
