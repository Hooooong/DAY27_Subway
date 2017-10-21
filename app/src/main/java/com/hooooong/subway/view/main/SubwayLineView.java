package com.hooooong.subway.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hooooong.subway.R;
import com.hooooong.subway.model.subwayline.Row;
import com.hooooong.subway.view.main.adapter.SubwayLineAdapter;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class SubwayLineView extends FrameLayout {

    private Row[] rows;
    private RecyclerView recyclerView;
    private SubwayListener listener;

    public SubwayLineView(@NonNull Context context, Row[] rows) {
        super(context);
        if (context instanceof SubwayListener) {
            this.listener = (SubwayListener) context;
        }
        this.rows = rows;
        initView();
        setAdapter();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.subway_list, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        addView(view);
    }

    private void setAdapter() {
        // LINE 호선 을 불러 RecyclerView 를 불러온다.
        SubwayLineAdapter adapter = new SubwayLineAdapter(rows, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public interface SubwayListener {
        void setClick(String stationName, String lineNumber);
    }
}
