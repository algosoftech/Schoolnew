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
 * Created by abc on 19/11/2018.
 */

public class ListNotificationAdapter extends RecyclerView.Adapter<ListNotificationAdapter.MyViewHolder>{
    private final ArrayList<String> mNamelist;
    public ListNotificationAdapter(Context context, ArrayList namelist) {
        this.mNamelist=namelist;
    }

    @Override
    public ListNotificationAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listnotificationview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ListNotificationAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mNamelist.get(position));
    }

    @Override
    public int getItemCount() {
        return mNamelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.Textone);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
