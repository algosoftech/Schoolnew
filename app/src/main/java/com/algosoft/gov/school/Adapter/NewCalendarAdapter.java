package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.NewCalendar;
import com.algosoft.gov.school.R;

import java.util.ArrayList;

public class NewCalendarAdapter extends RecyclerView.Adapter<NewCalendarAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String>mtotalEventList;
    private ArrayList<String>mEventDate;

    public NewCalendarAdapter(NewCalendar newCalendar, ArrayList<String> totalEventList, ArrayList<String> eventDate) {
        this.mtotalEventList=totalEventList;
        this.mEventDate=eventDate;
        this.context=newCalendar;

    }

    @Override
    public NewCalendarAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newcalendaradapter,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder( NewCalendarAdapter.MyViewHolder holder, int position) {
        holder.date.setText(mEventDate.get(position));
        holder.Events.setText(mtotalEventList.get(position));

    }

    @Override
    public int getItemCount() {
        return mtotalEventList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Attachment,date,Calendar,Events;
        public MyViewHolder(View itemView) {
            super(itemView);

            Events=(TextView)itemView.findViewById(R.id.tvEvent);
            Calendar=(TextView)itemView.findViewById(R.id.tvEventCal);
            date=(TextView)itemView.findViewById(R.id.tvStartDate);
            Attachment=(TextView)itemView.findViewById(R.id.tvCalendarAttache);

        }
    }
}
