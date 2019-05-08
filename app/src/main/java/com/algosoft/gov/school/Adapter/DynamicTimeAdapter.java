package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.DynamicTimeTable;
import com.algosoft.gov.school.response.TimeTable;

import java.util.ArrayList;

public class DynamicTimeAdapter extends RecyclerView.Adapter<DynamicTimeAdapter.MyviewHolder> {
    ArrayList<TimeTable>mperiodlist;
    ArrayList<String>mlist;
    Context mcontext;
    boolean TYPE=false;


    public DynamicTimeAdapter(DynamicTimeTable context, ArrayList<TimeTable> periodList, boolean righttype) {
        this.mcontext=context;
        this.mperiodlist=periodList;
        this.TYPE=righttype;
       Log.e("TimeTableArray",""+mperiodlist);



    }

    public DynamicTimeAdapter(DynamicTimeTable context, ArrayList<String> dayes) {
        this.mcontext=context;
        this.mlist=dayes;
        this.TYPE=false;
        Log.e("TimeTable",""+mlist);
    }


    @Override
    public DynamicTimeAdapter.MyviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view;
        MyviewHolder myviewHolderiew1=null;
        if (TYPE){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamicsubject,parent,false);
            myviewHolderiew1=new MyviewHolder(view);
       } else if(!TYPE){
        view=LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamictimetale,parent,false);
        myviewHolderiew1=new MyviewHolder(view);
        }
        return myviewHolderiew1;
    }


//    @Override
//    public int getItemViewType(int position) {
//        switch (mperiodlist.get(position).TYPE){
//
//        }
//        return super.getItemViewType(position);
//    }

    @Override
    public void onBindViewHolder(DynamicTimeAdapter.MyviewHolder holder, int position) {
        if (!TYPE){
                if(position==0){
                    holder.text_SN.setText("S.No");
                    holder.text_SN.setWidth(70);
                    holder.text_SN.setHeight(90);
                    holder.text_SN.setGravity(Gravity.CENTER);

                    holder.text_period.setHeight(90);
                    holder.text_period.setText("Days");
                    holder.text_period.setWidth(200);
                    holder.text_period.setGravity(Gravity.CENTER);
                } else if(position>0){
                    holder.text_SN.setText(String.valueOf(position));
                    holder.text_SN.setWidth(70);
                    holder.text_SN.setHeight(90);
                    holder.text_SN.setGravity(Gravity.CENTER);

                    holder.text_period.setWidth(200);
                    holder.text_period.setHeight(110);
                    holder.text_period.setGravity(Gravity.CENTER);
                    holder.text_period.setText(mlist.get(position-1));
                }
        } else if(TYPE)
                if(position==0) {
                    holder.Day1.setText("Period 1");
                    holder.Day1.setWidth(200);
                    holder.Day1.setHeight(90);
                    holder.Day1.setGravity(Gravity.CENTER);
                    holder.Day1.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day1.setTextColor(Color.parseColor("#ffffff"));


                    holder.Day2.setText("Period 2");
                    holder.Day2.setWidth(200);
                    holder.Day2.setHeight(90);
                    holder.Day2.setGravity(Gravity.CENTER);
                    holder.Day2.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day2.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day3.setText("Period 3");
                    holder.Day3.setWidth(200);
                    holder.Day3.setHeight(90);
                    holder.Day3.setGravity(Gravity.CENTER);
                    holder.Day3.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day3.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day4.setText("Lunch");
                    holder.Day4.setWidth(200);
                    holder.Day4.setHeight(90);
                    holder.Day4.setGravity(Gravity.CENTER);
                    holder.Day4.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day4.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day5.setText("Period 4");
                    holder.Day5.setWidth(200);
                    holder.Day5.setHeight(90);
                    holder.Day5.setGravity(Gravity.CENTER);
                    holder.Day5.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day5.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day6.setText("Period 5");
                    holder.Day6.setWidth(200);
                    holder.Day6.setHeight(90);
                    holder.Day6.setGravity(Gravity.CENTER);
                    holder.Day6.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day6.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day7.setText("Period 6");
                    holder.Day7.setWidth(200);
                    holder.Day7.setHeight(90);
                    holder.Day7.setGravity(Gravity.CENTER);
                    holder.Day7.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day7.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day8.setText("Period 7");
                    holder.Day8.setWidth(200);
                    holder.Day8.setHeight(90);
                    holder.Day8.setGravity(Gravity.CENTER);
                    holder.Day8.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day8.setTextColor(Color.parseColor("#ffffff"));

                    holder.Day9.setText("Period 8");
                    holder.Day9.setWidth(200);
                    holder.Day9.setHeight(90);
                    holder.Day9.setGravity(Gravity.CENTER);
                    holder.Day9.setBackgroundResource(R.drawable.shapeperiod);
                    holder.Day9.setTextColor(Color.parseColor("#ffffff"));


                }else if (position>0){
                    holder.Day1.setWidth(200);
                    holder.Day1.setHeight(110);
                    holder.Day1.setGravity(Gravity.CENTER);
                    holder.Day1.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());


                    holder.Day2.setWidth(200);
                    holder.Day2.setHeight(110);
                    holder.Day2.setGravity(Gravity.CENTER);
                    holder.Day2.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day3.setWidth(200);
                    holder.Day3.setHeight(110);
                    holder.Day3.setGravity(Gravity.CENTER);
                    holder.Day3.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day4.setWidth(200);
                    holder.Day4.setHeight(110);
                    holder.Day4.setGravity(Gravity.CENTER);
                    holder.Day4.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day5.setWidth(200);
                    holder.Day5.setHeight(110);
                    holder.Day5.setGravity(Gravity.CENTER);
                    holder.Day5.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());
                    holder.Day6.setWidth(200);
                    holder.Day6.setHeight(110);
                    holder.Day6.setGravity(Gravity.CENTER);
                    holder.Day6.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day7.setWidth(200);
                    holder.Day7.setHeight(110);
                    holder.Day7.setGravity(Gravity.CENTER);
                    holder.Day7.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day8.setWidth(200);
                    holder.Day8.setHeight(110);
                    holder.Day8.setGravity(Gravity.CENTER);
                    holder.Day8.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                    holder.Day9.setWidth(200);
                    holder.Day9.setHeight(110);
                    holder.Day9.setGravity(Gravity.CENTER);
                    holder.Day9.setText(mperiodlist.get(position-1).getSubjectName()+" "+mperiodlist.get(position-1).getClassName()
                            +" "+mperiodlist.get(position-1).getClassSectionName());

                }



    }

    @Override
    public int getItemCount() {
        int size=0;
        if(TYPE){
            size= mperiodlist.size();
        }else if (!TYPE){
            size= mlist.size();

        }
       return size;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView text_SN,text_period;
        TextView Day1,Day2,Day3,Day4,Day5,Day6,Day7,Day8,Day9;

        public MyviewHolder(View itemView) {
            super(itemView);

            text_SN=(TextView)itemView.findViewById(R.id.text_SN);
            text_period=(TextView)itemView.findViewById(R.id.text_lecture);
            Day1=(TextView)itemView.findViewById(R.id.Text_day1);
            Day2=(TextView)itemView.findViewById(R.id.Text_day2);
            Day3=(TextView)itemView.findViewById(R.id.Text_day3);
            Day4=(TextView)itemView.findViewById(R.id.Text_day4);
            Day5=(TextView)itemView.findViewById(R.id.Text_day5);
            Day6=(TextView)itemView.findViewById(R.id.Text_day6);
            Day7=(TextView)itemView.findViewById(R.id.Text_day7);
            Day8=(TextView)itemView.findViewById(R.id.Text_day8);
            Day9=(TextView)itemView.findViewById(R.id.Text_day9);
        }

    }
}
