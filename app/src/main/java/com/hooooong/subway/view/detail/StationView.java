package com.hooooong.subway.view.detail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
    private GradientDrawable gradientDrawable;

    public StationView(@NonNull Context context, StationList stationList) {
        super(context);
        this.context = context;
        this.stationList = stationList;
        gradientDrawable = new GradientDrawable();

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
        int color = getColor(stationList.getSubwayNm());

        setStationTitle(color);

        if(!stationList.getStatnFnm().equals(stationList.getStatnNm())){
            textStationPrev.setText(stationList.getStatnFnm());
            textStationPrev.setBackgroundColor(color);
            textStationPrev.setTextColor(Color.WHITE);
        }
        if(!stationList.getStatnTnm().equals(stationList.getStatnNm())){
            textStationNext.setText(stationList.getStatnTnm());
            textStationNext.setBackgroundColor(color);
            textStationNext.setTextColor(Color.WHITE);
        }

        String address = stationList.getZipNo()+" "+stationList.getAdresBass() +" "+ stationList.getAdresDetail();
        textAddress.setText(address);
        textPhoneNumber.setText(stationList.getTelno());
    }

    private void setStationTitle(int color) {

        gradientDrawable.setCornerRadius(100);
        gradientDrawable.setStroke(20, color);
        gradientDrawable.setColor(Color.WHITE);

        textStationName.setText(stationList.getStatnNm());
        textStationName.setTextColor(color);
        textStationName.bringToFront();


        textStationName.measure(0, 0);       //must call measure!
        textStationName.getMeasuredHeight(); //get height
        textStationName.getMeasuredWidth();  //get width

        gradientDrawable.setSize( textStationName.getMeasuredWidth()+150, textStationName.getMeasuredHeight()+100);
        textStationName.setBackgroundDrawable(gradientDrawable);
    }

    public void setChangeData(List<RealTimeArrivalList> realTimeArrivalList, long currentTime){
        List<RealTimeArrivalList> upLine = new ArrayList<>();
        List<RealTimeArrivalList> downLine = new ArrayList<>();

        /**
         * 로직을 다시 짜야함
         */
        for(RealTimeArrivalList temp : realTimeArrivalList){
            if("상행".equals(temp.getUpdnLine())){
                upLine.add(temp);
            }else{
                downLine.add(temp);
            }
        }

        Log.e("StationView : " , "upLine.size() " + upLine.size() );
        Log.e("StationView : " , "downLine.size() " + downLine.size() );

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

    private int getColor(String lineNumber){
        int color = 0;
        switch (lineNumber){
            case "1호선":
                color = ContextCompat.getColor(context, R.color.lineOne);
                break;
            case "2호선":
                color = ContextCompat.getColor(context, R.color.lineTwo);
                break;
            case "3호선":
                color = ContextCompat.getColor(context, R.color.lineThree);
                break;
            case "4호선":
                color = ContextCompat.getColor(context, R.color.lineFour);
                break;
            case "5호선":
                color = ContextCompat.getColor(context, R.color.lineFive);
                break;
            case "6호선":
                color = ContextCompat.getColor(context, R.color.lineSix);
                break;
            case "7호선":
                color = ContextCompat.getColor(context, R.color.lineSeven);
                break;
            case "8호선":
                color = ContextCompat.getColor(context, R.color.lineEight);
                break;
            case "9호선":
                color = ContextCompat.getColor(context, R.color.lineNine);
                break;
            case "경의중앙선":
                color = ContextCompat.getColor(context, R.color.lineCenter);
                break;
            case "공항철도":
                color = ContextCompat.getColor(context, R.color.lineAirport);
                break;
            case "분당선":
                color = ContextCompat.getColor(context, R.color.lineBundang);
                break;
            case "신분당선":
                color = ContextCompat.getColor(context, R.color.lineNewBundang);
                break;
            case "수인":
                color = ContextCompat.getColor(context, R.color.lineSuin);
                break;
            default:
                break;
        }

        return color;
    }
}


