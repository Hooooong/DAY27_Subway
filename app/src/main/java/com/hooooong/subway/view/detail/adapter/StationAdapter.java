package com.hooooong.subway.view.detail.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

    private List<StationView> views = null;
    private StationList[] stationList;

    public StationAdapter(Context context, StationList[] data) {
        this.stationList = data;
        views = new ArrayList<>();
        for (int i = 0; i < stationList.length; i++) {
            views.add(new StationView(context, stationList[i]));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        StationView view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((StationView) object);
    }

    public void changeView(RealTimeStation realTimeStation, long currentTime) {
        for (StationView view : views) {
            // View 에 맞는 데이터를 업데이트한다.
            List<RealTimeArrivalList> realTimeArrivalList = new ArrayList<>();
            for (RealTimeArrivalList realTimeArrival : realTimeStation.getRealtimeArrivalList()) {
                if (view.getSubwayId().equals(realTimeArrival.getSubwayId())) {
                    realTimeArrivalList.add(realTimeArrival);
                }
            }
            view.setChangeData(realTimeArrivalList, currentTime);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public interface StationListener {
        void changeData(RealTimeStation realTimeStation, long currentTime);
    }
}
