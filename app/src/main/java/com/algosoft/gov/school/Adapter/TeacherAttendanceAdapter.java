package com.algosoft.gov.school.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GetTeacherAttendanceResponse;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TeacherAttendanceAdapter extends RecyclerView.Adapter<TeacherAttendanceAdapter.TeacherAttendanceViewHolder> {
    private Context context;
    private ArrayList<GetTeacherAttendanceResponse.Result.AttendanceData> attendanceList;

    public TeacherAttendanceAdapter(FragmentActivity activity, ArrayList<GetTeacherAttendanceResponse.Result.AttendanceData> attendanceDetails) {
        this.context= activity;
        this.attendanceList= attendanceDetails;
    }

    @NonNull
    @Override
    public TeacherAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_attendance_item,null);
        TeacherAttendanceViewHolder myViewHolder=new TeacherAttendanceViewHolder(view);
        return myViewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TeacherAttendanceViewHolder holder, int position) {
      holder.attDate.setText(attendanceList.get(position).getAttendanceDate());
      holder.attTime.setText(attendanceList.get(position).getAttendanceTime());
      holder.attType.setText(attendanceList.get(position).getAttendanceType());
      if( holder.attType.getText().toString().equalsIgnoreCase("Absent")){
          holder.box.setBackgroundResource(R.color.red);
      }
      else if(holder.attType.getText().toString().equalsIgnoreCase("Present")){
          holder.box.setBackgroundResource(R.color.colorYellow);
      }
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class TeacherAttendanceViewHolder extends RecyclerView.ViewHolder{

        TextView attDate, attTime, attType;
        LinearLayout box;
        public TeacherAttendanceViewHolder(View itemView) {
            super(itemView);
            attDate= (TextView) itemView.findViewById(R.id.att_date);
            attTime= (TextView) itemView.findViewById(R.id.att_time);
            attType= (TextView) itemView.findViewById(R.id.att_type);
            box= (LinearLayout) itemView.findViewById(R.id.card_att_type);
        }

    }
}
