package com.hooooong.subway.model.realtimestation;

import android.os.AsyncTask;
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

    private static RealTimeStationThread realTimeStationThread;
    private String stationName;
    private StationRTThread thread;
    private StationAdapter.StationListener stationListener;

    private RealTimeStationThread(StationAdapter.StationListener stationListener) {
        this.stationListener = stationListener;

    }

    public static RealTimeStationThread getInstance(StationAdapter.StationListener stationListener) {
        if (realTimeStationThread == null) {
            realTimeStationThread = new RealTimeStationThread(stationListener);
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
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected void onPreExecute() {

                    }

                    @Override
                    protected String doInBackground(String... args) {
                        return Remote.getData(args[0]);
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        RealTimeStation realTimeStation = parsingJson(result);
                        stationListener.changeData(realTimeStation, System.currentTimeMillis());
                    }
                }.execute(UrlInfo.getStationRtUrl(stationName));

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
        Gson gson = new Gson();
        return gson.fromJson(result, RealTimeStation.class);
    }

}
