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
 * Created by abc on 21/11/2018.
 */

public class StudentReplyAdapter extends RecyclerView.Adapter<StudentReplyAdapter.MyViewHolder>{
    private final ArrayList<String> mReplyList;

    public StudentReplyAdapter(Context context,ArrayList<String> ReplyList) {
        this.mReplyList = ReplyList;
    }

    @Override
    public StudentReplyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentreply, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( StudentReplyAdapter.MyViewHolder holder, int position) {
        holder.textname.setText(mReplyList.get(position));
    }



    @Override
    public int getItemCount() {
        return mReplyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textname;
        public MyViewHolder(View itemView) {
            super(itemView);
            textname=(TextView)itemView.findViewById(R.id.Text_name);
        }
    }
}
