package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.StudentDetail;

import java.util.ArrayList;

/**
 * Created by abc on 17/11/2018.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    private final ArrayList<StudentDetail> mStudentNames;

    Context mcontext;
    public AttendanceAdapter(Context context, ArrayList<StudentDetail> studentdetail){
        this.mcontext=context;
        this.mStudentNames=studentdetail;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendanceok, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Name.setText(mStudentNames.get(position).getStudentName());
    }

    @Override
    public int getItemCount() {
       return mStudentNames.size();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name;
        TextView textpresent;
        TextView textAbsent;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's

//            imageView=(ImageView)itemView.findViewById(R.id.Image_Student);
            Name = (TextView) itemView.findViewById(R.id.Text_Name);
            textpresent=(TextView) itemView.findViewById(R.id.Text_present);
            textAbsent=(TextView)itemView.findViewById(R.id.Text_absent);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
