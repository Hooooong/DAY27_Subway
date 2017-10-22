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

import java.util.ArrayList;
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

    public void setChangeData(List<RealTimeArrivalList> realTimeArrivalList, long currentTime){
        List<RealTimeArrivalList> upLine = new ArrayList<>();
        List<RealTimeArrivalList> downLine = new ArrayList<>();

        for(RealTimeArrivalList temp : realTimeArrivalList){
            if("상행".equals(temp.getUpdnLine())){
                upLine.add(temp);
            }else{
                downLine.add(temp);
            }
        }

        if(upLine.size() == 0){

        }else if(upLine.size() == 1){
            String train = upLine.get(0).getTrainLineNm();
            train =  train.substring(0, train.indexOf("-")-1);

            textLeftFirstTrain.setText(train);
            textLeftFirstWhere.setText(upLine.get(0).getArvlMsg2());
        }else{
            String train = upLine.get(0).getTrainLineNm();
            train =  train.substring(0, train.indexOf("-")-1);
            String train2 = upLine.get(1).getTrainLineNm();
            train2 =  train2.substring(0, train2.indexOf("-")-1);

            textLeftFirstTrain.setText(train);
            textLeftFirstWhere.setText(upLine.get(0).getArvlMsg2());
            textLeftSecondTrain.setText(train2);
            textLeftSecondWhere.setText(upLine.get(1).getArvlMsg2());
        }


        if(downLine.size() == 0){

        }else if(downLine.size() == 1){
            String train = downLine.get(0).getTrainLineNm();
            train =  train.substring(0, train.indexOf("-")-1);

            textRightFirstTrain.setText(train);
            textRightFirstWhere.setText(downLine.get(0).getArvlMsg2());
        }else{
            String train = downLine.get(0).getTrainLineNm();
            train =  train.substring(0, train.indexOf("-")-1);
            String train2 = downLine.get(1).getTrainLineNm();
            train2 =  train2.substring(0, train2.indexOf("-")-1);

            textRightFirstTrain.setText(train);
            textRightFirstWhere.setText(downLine.get(0).getArvlMsg2());
            textRightSecondTrain.setText(train2);
            textRightSecondWhere.setText(downLine.get(1).getArvlMsg2());
        }
    }

    public String getSubwayId() {
        return stationList.getSubwayId();
    }

    public String getStationNm(){return stationList.getStatnNm();}
}


