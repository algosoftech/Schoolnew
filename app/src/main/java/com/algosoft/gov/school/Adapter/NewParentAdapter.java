package com.algosoft.gov.school.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Fragment.NewParentFragment;
import com.algosoft.gov.school.Parents.AddChild;
import com.algosoft.gov.school.Parents.Attendance;
import com.algosoft.gov.school.Parents.BusRoute;
import com.algosoft.gov.school.Parents.Fee;
import com.algosoft.gov.school.Parents.Homework;
import com.algosoft.gov.school.Parents.Message;
import com.algosoft.gov.school.Parents.MyCalender;
import com.algosoft.gov.school.Parents.MyProfile;
import com.algosoft.gov.school.Parents.NoticeBoard;
import com.algosoft.gov.school.Parents.Notificationparents;
import com.algosoft.gov.school.Parents.Result;
import com.algosoft.gov.school.Parents.School;
import com.algosoft.gov.school.Parents.Syllabus;
import com.algosoft.gov.school.Parents.Teacherslist;
import com.algosoft.gov.school.Parents.Timetable;
import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * Created by abc on 20/11/2018.
 */

public class NewParentAdapter extends RecyclerView.Adapter<NewParentAdapter.MyViewHolder> {
    private NewParentFragment mContext;
    private final ArrayList<String>mParentlist;
    public NewParentAdapter(NewParentFragment context,ArrayList<String>parentlist){
        mContext=context;
        mParentlist=parentlist;
    }
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parentshome,parent,false);
        view.setOnClickListener(Home.myOnClickListener);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( NewParentAdapter.MyViewHolder holder, int position) {
        holder.textattendance.setText(mParentlist.get(position));

    }

    @Override
    public int getItemCount() {
        return mParentlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView Imageattendance;
        TextView textattendance;
        ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textattendance=(TextView)itemView.findViewById(R.id.textattendanceParents);
            this.Imageattendance=(ImageView) itemView.findViewById(R.id.ImageattendanceParent);
            this.progressBar=(ProgressBar) itemView.findViewById(R.id.progressBarParents);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int posit=getAdapterPosition();
            String names= mParentlist.get(posit);


//            Toast.makeText(mContext, ""+posit+name, Toast.LENGTH_SHORT).show();
            if(names.equalsIgnoreCase(names)&& posit==10){
                Intent i=new Intent(v.getContext(),School.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==0){
                Intent i=new Intent(v.getContext(),Attendance.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==1){
                Intent i=new Intent(v.getContext(),Homework.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==2){
                Intent i=new Intent(v.getContext(),Syllabus.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==3){
                Intent i=new Intent(v.getContext(),MyProfile.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==4){
                Intent i=new Intent(v.getContext(),Timetable.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==5){
                Intent i=new Intent(v.getContext(),NoticeBoard.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==6){
                Intent i=new Intent(v.getContext(),Message.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==7){
                Intent i=new Intent(v.getContext(),Notificationparents.class);
                mContext.startActivity(i);
//            }else if (names.equalsIgnoreCase(names)&& posit==8){
//                Intent i=new Intent(v.getContext(),AddChild.class);
//                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==8){
                Intent i=new Intent(v.getContext(),MyCalender.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==9){
                Intent i=new Intent(v.getContext(),Result.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==11){
                Intent i=new Intent(v.getContext(),Fee.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==12){
                Intent i=new Intent(v.getContext(),Teacherslist.class);
                mContext.startActivity(i);
            }else if (names.equalsIgnoreCase(names)&& posit==13){
                Intent i=new Intent(v.getContext(),BusRoute.class);
                mContext.startActivity(i);
            }

        }
    }
}
