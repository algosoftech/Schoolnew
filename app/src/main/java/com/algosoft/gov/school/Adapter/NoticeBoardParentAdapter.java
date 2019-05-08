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

public class NoticeBoardParentAdapter extends RecyclerView.Adapter<NoticeBoardParentAdapter.MyViewHolder> {
    private final ArrayList<String> mNotice;

    public NoticeBoardParentAdapter(Context context,ArrayList<String> Notice) {
        this.mNotice = Notice;
    }

    @Override
    public NoticeBoardParentAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticeboardparent, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( NoticeBoardParentAdapter.MyViewHolder holder, int position) {
           holder.textViewNotice.setText(mNotice.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotice.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNotice;
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewNotice=(TextView)itemView.findViewById(R.id.Text_ParentNotice);
        }
    }
}
