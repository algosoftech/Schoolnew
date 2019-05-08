package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.NoticeBoardList;

import java.util.ArrayList;

/**
 * Created by abc on 19/11/2018.
 */

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.MyViewHolder>{
    private  ArrayList<NoticeBoardList> nNotice;
    private  Context context;

    public NoticeBoardAdapter(Context context, ArrayList<NoticeBoardList> notice) {
        this.nNotice=notice;
        this.context=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticboardview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.heading.setText(nNotice.get(position).getNoticeBoardMessage());
        holder.data.setText(nNotice.get(position).getNoticeBoardDate());
       // holder.heading.setText(nNotice.get(position).getNoticeBoardMessage());



    }


    @Override
    public int getItemCount() {
        return nNotice.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView heading;
        TextView text,data;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=(ImageView)itemView.findViewById(R.id.Image_Notice);
            heading=(TextView)itemView.findViewById(R.id.Text_heading);
            text=(TextView)itemView.findViewById(R.id.Text_text);
            data=(TextView)itemView.findViewById(R.id.Text_Date);



        }

        @Override
        public void onClick(View v) {

        }
    }

}
