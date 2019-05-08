package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.AttendanceDetail;

import java.util.ArrayList;

/**
 * Created by abc on 21/11/2018.
 */

public class ViewAttendanceAdapter extends RecyclerView.Adapter<ViewAttendanceAdapter.MyViewHolder>{
    private final ArrayList<AttendanceDetail> mStudentNames;

    public ViewAttendanceAdapter(Context context,ArrayList<AttendanceDetail> StudentNames) {
        this.mStudentNames = StudentNames;
    }

    @Override
    public ViewAttendanceAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentattendancelist, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( ViewAttendanceAdapter.MyViewHolder holder, int position) {
        holder.textNo.setText(mStudentNames.get(position).getStudentName());
        holder.textname.setText(mStudentNames.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mStudentNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textname,textNo;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textname=(TextView)itemView.findViewById(R.id.Text);
            textNo=(TextView)itemView.findViewById(R.id.textattendance);
            imageView=(ImageView)itemView.findViewById(R.id.profile_image);
        }
    }
}
