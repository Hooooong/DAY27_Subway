package com.hooooong.subway.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import com.hooooong.subway.R;

/**
 * Created by Android Hong on 2017-10-23.
 */

public class TabText extends android.support.v7.widget.AppCompatTextView {

    private Context context;
    private GradientDrawable gradientDrawable;
    private int color = 0;

    public TabText(Context context, String text) {
        super(context);
        this.context = context;

        gradientDrawable = new GradientDrawable();
        setGravity(Gravity.CENTER);
        setTextSize(12);
        setWidth(100);
        setHeight(100);
        setLineColor(text);
        setBackground();
    }

    private void setLineColor(String text){
        switch (text) {
            case "1호선":
                color = ContextCompat.getColor(context, R.color.lineOne);
                setText("1");
                setTextColor(color);
                break;
            case "2호선":
                color = ContextCompat.getColor(context, R.color.lineTwo);
                setText("2");
                setTextColor(color);
                break;
            case "3호선":
                color = ContextCompat.getColor(context, R.color.lineThree);
                setText("3");
                setTextColor(color);
                break;
            case "4호선":
                color = ContextCompat.getColor(context, R.color.lineFour);
                setText("4");
                setTextColor(color);
                break;
            case "5호선":
                color = ContextCompat.getColor(context, R.color.lineFive);
                setText("5");
                setTextColor(color);
                break;
            case "6호선":
                color = ContextCompat.getColor(context, R.color.lineSix);
                setText("6");
                setTextColor(color);
                break;
            case "7호선":
                color = ContextCompat.getColor(context, R.color.lineSeven);
                setText("7");
                setTextColor(color);
                break;
            case "8호선":
                color = ContextCompat.getColor(context, R.color.lineEight);
                setText("8");
                setTextColor(color);
                break;
            case "9호선":
                color = ContextCompat.getColor(context, R.color.lineNine);
                setText("9");
                setTextColor(color);
                break;
            case "경의중앙선":
                color = ContextCompat.getColor(context, R.color.lineCenter);
                setText("경의중앙");
                setTextColor(color);
                break;
            case "공항철도":
                color = ContextCompat.getColor(context, R.color.lineAirport);
                setText("공항철도");
                setTextColor(color);
                break;
            case "분당":
                color = ContextCompat.getColor(context, R.color.lineBundang);
                setText("분당");
                setTextColor(color);
                break;
            case "신분당":
                color = ContextCompat.getColor(context, R.color.lineNewBundang);
                setText("신분당");
                setTextColor(color);
                break;
            case "수인":
                color = ContextCompat.getColor(context, R.color.lineSuin);
                setText("수인");
                setTextColor(color);
                break;
            default:
                setText(text);
                break;
        }
    }

    private void setBackground() {
        gradientDrawable.setStroke(3, color);
        gradientDrawable.setCornerRadius(100);
        setBackgroundDrawable(gradientDrawable);
    }

    public void changeBackground(boolean flag){
        if(flag){
            // 선택했을 경우
            gradientDrawable.setColor(color);
            gradientDrawable.setCornerRadius(100);
            setTextColor(Color.WHITE);
            setBackgroundDrawable(gradientDrawable);
        }else{
            // 선택하지 않았을 경우
            gradientDrawable.setColor(Color.WHITE);
            gradientDrawable.setStroke(3, color);
            gradientDrawable.setCornerRadius(100);
            setTextColor(color);
            setBackgroundDrawable(gradientDrawable);
        }
    }
}
