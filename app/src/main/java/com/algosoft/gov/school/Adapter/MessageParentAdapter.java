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

public class MessageParentAdapter extends RecyclerView.Adapter<MessageParentAdapter.MyViewHolder> {
    private final ArrayList<String> mMessage;

    public MessageParentAdapter(Context context,ArrayList<String> Message) {
        this.mMessage = Message;
    }

    @Override
    public MessageParentAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messageparent, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MessageParentAdapter.MyViewHolder holder, int position) {
        holder.textMessage.setText(mMessage.get(position));

    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textMessage;
        public MyViewHolder(View itemView) {
            super(itemView);
            textMessage=(TextView)itemView.findViewById(R.id.Text_Message);
        }
    }
}
