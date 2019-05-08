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
 * Created by abc on 22/11/2018.
 */

public class ParentHomeworkAdapter extends RecyclerView.Adapter<ParentHomeworkAdapter.MyViewHolder>{
    private final ArrayList<String> msublist;

    public ParentHomeworkAdapter(Context context,ArrayList<String> sublist) {
        this.msublist = sublist;
    }

    @Override
    public ParentHomeworkAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parenthomework, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ParentHomeworkAdapter.MyViewHolder holder, int position) {
        holder.textSub.setText(msublist.get(position));

    }

    @Override
    public int getItemCount() {
        return msublist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textSub;
        public MyViewHolder(View itemView) {
            super(itemView);
            textSub=(TextView)itemView.findViewById(R.id.Text_subject);
        }
    }
}
