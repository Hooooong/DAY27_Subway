package com.hooooong.subway.view.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hooooong.subway.R;
import com.hooooong.subway.model.realtimestation.RealTimeArrivalList;
import com.hooooong.subway.model.station.StationList;

import java.util.List;

/**
 * Created by Android Hong on 2017-10-20.
 */

public class StationView extends FrameLayout{

    private Context context;
    private StationList stationList;
    private TextView textStationName, textStationPrev, textStationNext;
    private TextView textLeftFirstTrain, textLeftFirstWhere, textLeftSecondTrain, textLeftSecondWhere;
    private TextView textRightFirstTrain, textRightFirstWhere, textRightSecondTrain, textRightSecondWhere;
    private TextView textAddress, textPhoneNumber;

    public StationView(@NonNull Context context, StationList stationList) {
        super(context);
        this.context = context;
        this.stationList = stationList;

        initView();
        setStationInfo();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.station_detail, null);
        textStationName = view.findViewById(R.id.textStationName);
        textStationPrev = view.findViewById(R.id.textStationPrev);
        textStationNext = view.findViewById(R.id.textStationNext);
        textLeftFirstTrain = view.findViewById(R.id.textLeftFirstTrain);
        textLeftFirstWhere = view.findViewById(R.id.textLeftFirstWhere);
        textLeftSecondTrain = view.findViewById(R.id.textLeftSecondTrain);
        textLeftSecondWhere = view.findViewById(R.id.textLeftSecondWhere);
        textRightFirstTrain = view.findViewById(R.id.textRightFirstTrain);
        textRightFirstWhere = view.findViewById(R.id.textRightFirstWhere);
        textRightSecondTrain = view.findViewById(R.id.textRightSecondTrain);
        textRightSecondWhere = view.findViewById(R.id.textRightSecondWhere);
        textAddress = view.findViewById(R.id.textAddress);
        textPhoneNumber = view.findViewById(R.id.textPhoneNumber);
        addView(view);
    }

    private void setStationInfo(){
        textStationName.setText(stationList.getStatnNm());

        if(!stationList.getStatnFnm().equals(stationList.getStatnNm())){
            textStationPrev.setText(stationList.getStatnFnm());
        }
        if(!stationList.getStatnTnm().equals(stationList.getStatnNm())){
            textStationNext.setText(stationList.getStatnTnm());
        }
        String address = stationList.getZipNo()+" "+stationList.getAdresBass() +" "+ stationList.getAdresDetail();
        textAddress.setText(address);
        textPhoneNumber.setText(stationList.getTelno());
    }

    public void setChangeData(List<RealTimeArrivalList> realTimeArrivalList){

        textLeftFirstTrain.setText("뿌");
        /*
        textLeftFirstTrain =
        textLeftFirstWhere =
        textLeftSecondTrain =
        textLeftSecondWhere =
        textRightFirstTrain =
        textRightFirstWhere =
        textRightSecondTrain =
        textRightSecondWhere =
        */
    }

    public String getSubwayId() {
        return stationList.getSubwayId();
    }
}


