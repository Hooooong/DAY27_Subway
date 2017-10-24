package com.hooooong.subway.view.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hooooong.subway.R;
import com.hooooong.subway.model.Const;
import com.hooooong.subway.model.subwayline.SubwayLine;
import com.hooooong.subway.util.Remote;
import com.hooooong.subway.util.UrlInfo;
import com.hooooong.subway.view.detail.DetailActivity;
import com.hooooong.subway.view.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SubwayLineView.SubwayListener {


    private List<SubwayLine> subwayLineList;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        load();
        initView();
        initListener();
    }

    private void load() {
        subwayLineList = new ArrayList<>();

        new AsyncTask<String, Void, List<SubwayLine>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected List<SubwayLine> doInBackground(String... args) {
                List<SubwayLine> subwayLineList = new ArrayList<>();
                for(String url : args){
                    String result = Remote.getData(url);
                    SubwayLine subwayLine = parsingJson(result);
                    subwayLineList.add(subwayLine);
                }
                return subwayLineList;
            }

            @Override
            protected void onPostExecute(List<SubwayLine> subwayLineList) {
                setTabLayout(subwayLineList);
                initViewPager(subwayLineList);

            }
        }.execute(UrlInfo.getLineUrl("1"),
                UrlInfo.getLineUrl("2"),
                UrlInfo.getLineUrl("3"),
                UrlInfo.getLineUrl("4"),
                UrlInfo.getLineUrl("5"),
                UrlInfo.getLineUrl("6"),
                UrlInfo.getLineUrl("7"),
                UrlInfo.getLineUrl("8"),
                UrlInfo.getLineUrl("9"));
    }

    private SubwayLine parsingJson(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, SubwayLine.class);
    }


    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void setTabLayout(List<SubwayLine> subwayLineList) {
        for(SubwayLine subwayLine : subwayLineList){
            tabLayout.addTab(tabLayout.newTab().setText(subwayLine.getSearchSTNBySubwayLineService().getRow()[0].getLINE_NUM()));
        }


    }

    private void initViewPager(List<SubwayLine> subwayLineList) {
        MainAdapter adapter = new MainAdapter(this, subwayLineList);
        viewPager.setAdapter(adapter);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void setClick(String stationName, String lineNumber) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Const.KEY_STATION_NAME, stationName);
        intent.putExtra(Const.KEY_STATION_LINE, lineNumber);
        startActivity(intent);
    }
}
