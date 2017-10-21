package com.hooooong.subway.view.detail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hooooong.subway.R;
import com.hooooong.subway.model.Const;
import com.hooooong.subway.model.realtimestation.RealTimeStation;
import com.hooooong.subway.model.realtimestation.RealTimeStationThread;
import com.hooooong.subway.model.station.Station;
import com.hooooong.subway.util.Remote;
import com.hooooong.subway.util.UrlInfo;
import com.hooooong.subway.view.detail.adapter.StationAdapter;

public class DetailActivity extends AppCompatActivity implements StationAdapter.StationListener {

    private ProgressBar progressBar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Station station;
    private String stationName;
    private String stationLine;

    private StationAdapter stationAdapter;
    private RealTimeStationThread realTimeStationThread ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if(intent != null){
            stationName = intent.getStringExtra(Const.KEY_STATION_NAME);
            stationLine = intent.getStringExtra(Const.KEY_STATION_LINE);
        }

        realTimeStationThread = RealTimeStationThread.getInstance(this);

        initView();
        initListener();
        load();
    }

    private void initView(){
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        tabLayout = toolbar.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
    }

    private void initListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void load(){
        new AsyncTask<String, Void, String>(){
            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(String... arg) {
                return Remote.getData(arg[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                progressBar.setVisibility(View.GONE);
                if(Const.CONNECTION_ERROR.equals(result)){
                    Toast.makeText(DetailActivity.this, "지하철 역 정보 API 호출 실패!!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    parsingJson(result);
                    setTabLayout();
                    setDetailView();
                    realTimeStationThread.set(stationName);
                }
            }
        }.execute(UrlInfo.getStationInfoUrl(stationName));
    }

    private void parsingJson(String result) {
        Gson gson = new Gson();
        station = gson.fromJson(result, Station.class);
    }

    private void setTabLayout() {
        // TabLayout 에 Text 출력
        for(int i = 0 ; i<station.getStationList().length; i++){
            tabLayout.addTab(tabLayout.newTab().setText(station.getStationList()[i].getSubwayNm()));
        }
    }

    private void setDetailView() {
        // ViewPager 에 adapter setting
        stationAdapter = new StationAdapter(this, station.getStationList());
        viewPager.setAdapter(stationAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_close:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        realTimeStationThread.set(stationName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        realTimeStationThread.setStop();
    }

    @Override
    public void changeData(RealTimeStation realTimeStation) {
        stationAdapter.changeView(realTimeStation);
    }
}
