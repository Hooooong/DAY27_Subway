package com.hooooong.subway.view.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hooooong.subway.R;
import com.hooooong.subway.model.subwayline.Row;
import com.hooooong.subway.view.main.SubwayLineView.SubwayListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Android Hong on 2017-10-19.
 */

public class SubwayLineAdapter extends RecyclerView.Adapter<SubwayLineAdapter.ViewHolder>{

    private SubwayListener listener;
    private List<Row> rowList;

    public SubwayLineAdapter(Row[] rows, SubwayListener listener ) {
        this.rowList = Arrays.asList(rows);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Row row = rowList.get(position);
        holder.setTextView(row.getSTATION_NM());
        holder.setLineNumber(row.getLINE_NUM());
    }

    @Override
    public int getItemCount() {
        return rowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private String lineNumber;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.setClick(textView.getText().toString(), lineNumber);
                }
            });
        }

        public void setTextView(String stationName){
            textView.setText(stationName);
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }
    }
}
