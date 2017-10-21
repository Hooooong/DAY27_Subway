package com.hooooong.subway.view.main.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.hooooong.subway.model.subwayline.SubwayLine;
import com.hooooong.subway.view.main.SubwayLineView;

import java.util.List;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class MainAdapter extends PagerAdapter {

    private Context context;
    private List<SubwayLine> subwayLineList;

    public MainAdapter(Context context, List<SubwayLine> subwayLineList) {
        this.context = context;
        this.subwayLineList = subwayLineList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = new SubwayLineView(context, subwayLineList.get(position).getSearchSTNBySubwayLineService().getRow());
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return subwayLineList.size();
    }

}
