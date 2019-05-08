package com.algosoft.gov.school.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.CalendarActivity;
import com.algosoft.gov.school.Activity.NewCalendar;
import com.algosoft.gov.school.Teacher.Assignment;
import com.algosoft.gov.school.Fragment.HomeFragment;
import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Teacher.DynamicTimeTable;
import com.algosoft.gov.school.Teacher.HomeWork;
import com.algosoft.gov.school.Teacher.Message;
import com.algosoft.gov.school.Teacher.NoticBoard;
import com.algosoft.gov.school.Teacher.Notification;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.Syllabus;
import com.algosoft.gov.school.Teacher.TakeAttendanceActivity;
import com.algosoft.gov.school.Teacher.TimeTable;
import com.algosoft.gov.school.Teacher.ViewAttendance;
import com.algosoft.gov.school.response.MenuList;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by abc on 13/11/2018.
 */

public class Homeadapter extends RecyclerView.Adapter<Homeadapter.MyViewHolder>{

    private HomeFragment mcontext;
    private final ArrayList<MenuList> mname;

    public Homeadapter(HomeFragment context, ArrayList<MenuList> menulist ) {
        mcontext = context;
        mname = menulist;

    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.homecard,parent,false);
        view.setOnClickListener(Home.myOnClickListener);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
    holder.textattendance.setText(mname.get(position).getName());
    Glide.with(mcontext)
                .load(mname.get(position).getImage())
                .into( holder.Imageattendance);
    holder.Imageattendance.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mname.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView Imageattendance;
        TextView textattendance;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textattendance=(TextView)itemView.findViewById(R.id.textattendance);
            this.Imageattendance=(ImageView)itemView.findViewById(R.id.Imageattendance);
            this.progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            if(PreferenceUtil.getAttendanceDate(mcontext.getContext()).equalsIgnoreCase(KeyGenerationClass.getDate()) && PreferenceUtil.getSelectedTypeFromServer(mcontext.getContext()).equalsIgnoreCase("Teacher")){
                itemView.setOnClickListener(this);
            }else{
                itemView.setOnClickListener(null);
            }
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            String name  = mname.get(pos).getName();
            if(name.equalsIgnoreCase("Attendance")){
                Intent i=new Intent(v.getContext(),TakeAttendanceActivity.class);
                mcontext.startActivity(i);
            }else if(name.equalsIgnoreCase("Home Work")){
                Intent i=new Intent(v.getContext(),HomeWork.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Syllabus")){
                Intent i=new Intent(v.getContext(),Syllabus.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("View Attendance")){
                Intent i=new Intent(v.getContext(),ViewAttendance.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Time Table")){
                Intent i=new Intent(v.getContext(), DynamicTimeTable.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Notice Board")){
                Intent i=new Intent(v.getContext(),NoticBoard.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Message")){
                Intent i=new Intent(v.getContext(),Message.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Notification")){
                Intent i=new Intent(v.getContext(),Notification.class);
                mcontext.startActivity(i);
            }else if (name.equalsIgnoreCase("Assignment")){
                Intent i=new Intent(v.getContext(),Assignment.class);
                mcontext.startActivity(i);
            }
            else if (name.equalsIgnoreCase("Calendar")){
                Intent i=new Intent(v.getContext(), NewCalendar.class);
                mcontext.startActivity(i);
            }
        }

    }

}
