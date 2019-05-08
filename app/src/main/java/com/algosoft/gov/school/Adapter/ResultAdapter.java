package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * Created by abc on 24/11/2018.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder>{
    private final ArrayList<String> mUnit;

    public ResultAdapter(Context context,ArrayList<String> Unit) {
        this.mUnit = Unit;
    }

    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ResultAdapter.MyViewHolder holder, int position) {
        holder.textUnit.setText(mUnit.get(position));

    }

    @Override
    public int getItemCount() {
        return mUnit.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textUnit;
        public MyViewHolder(View itemView) {
            super(itemView);
            textUnit=(TextView)itemView.findViewById(R.id.TextResult);
        }
    }
}
