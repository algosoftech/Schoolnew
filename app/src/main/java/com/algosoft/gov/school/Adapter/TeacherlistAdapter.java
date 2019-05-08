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

public class TeacherlistAdapter extends RecyclerView.Adapter<TeacherlistAdapter.MyViewHolder> {
    private final ArrayList<String> mTeacher;

    public TeacherlistAdapter(Context context,ArrayList<String> Teacher) {
        this.mTeacher = Teacher;
    }

    @Override
    public TeacherlistAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherlist, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( TeacherlistAdapter.MyViewHolder holder, int position) {
        holder.textTeacher.setText(mTeacher.get(position));

    }

    @Override
    public int getItemCount() {
        return mTeacher.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textTeacher;
        public MyViewHolder(View itemView) {
            super(itemView);
            textTeacher=(TextView)itemView.findViewById(R.id.Textteacher);
        }
    }
}
