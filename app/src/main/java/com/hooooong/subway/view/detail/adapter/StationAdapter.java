package com.hooooong.subway.view.detail.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.hooooong.subway.model.realtimestation.RealTimeArrivalList;
import com.hooooong.subway.model.realtimestation.RealTimeStation;
import com.hooooong.subway.model.station.StationList;
import com.hooooong.subway.view.detail.StationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-10-20.
 */

public class StationAdapter extends PagerAdapter {

    private List<StationView> views;
    private Context context;
    private StationList[] stationList;
    private RealTimeStation realTimeStation;

    public StationAdapter(Context context, StationList[] data) {
        this.context = context;
        this.stationList = data;
        views = new ArrayList<>();
        for (int i = 0; i < stationList.length; i++) {
            views.add(new StationView(context, stationList[i]));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e("StationAdapter", "instantiateItem() 호출");
        Log.e("position", position+"");
        StationView view = views.get(position);
        if(realTimeStation != null){
            List<RealTimeArrivalList> realTimeArrivalList = new ArrayList<>();
            Log.e("view.getSubwayId()", view.getSubwayId());
            for (RealTimeArrivalList realTimeArrival : realTimeStation.getRealtimeArrivalList()) {
                Log.e("getSubwayId()", realTimeArrival.getSubwayId());
                if (view.getSubwayId().equals(realTimeArrival.getSubwayId())) {
                    realTimeArrivalList.add(realTimeArrival);
                }
            }
            view.setChangeData(realTimeArrivalList);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void changeView(RealTimeStation realTimeStation) {
        Log.e("changeView", realTimeStation.getRealtimeArrivalList().length+"");
        this.realTimeStation = realTimeStation;
        notifyDataSetChanged();
    }

    /* Log.e("views.size()", views.size()+"");
        for(StationView view : views){
        List<RealTimeArrivalList> realTimeArrivalList = new ArrayList<>();
        Log.e("view.getSubwayId()", view.getSubwayId());
        for (RealTimeArrivalList realTimeArrival : realTimeStation.getRealtimeArrivalList()) {
            Log.e("getSubwayId()", realTimeArrival.getSubwayId());

            if (view.getSubwayId().equals(realTimeArrival.getSubwayId())) {
                realTimeArrivalList.add(realTimeArrival);
            }
        }
        Log.e("List.size()", realTimeArrivalList.size()+"");
        view.setChangeData(realTimeArrivalList);
    }
    */
    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }



    public interface StationListener {
        void changeData(RealTimeStation realTimeStation);
    }
}
