package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * Created by abc on 26/11/2018.
 */

public class NofificationParentAdapter extends RecyclerView.Adapter<NofificationParentAdapter.MyViewHolder> {
    private final ArrayList<String> mNotification;

    public NofificationParentAdapter(Context context,ArrayList<String> Notification) {
        this.mNotification = Notification;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parentnotification, parent, false);
        MyViewHolder Vh=new MyViewHolder(v);
        return Vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textMain.setText(mNotification.get(position));
    }



    @Override
    public int getItemCount() {
        return mNotification.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textMain;
        public MyViewHolder(View itemView) {
            super(itemView);
            textMain=(TextView)itemView.findViewById(R.id.Textmain);
        }
    }
}
