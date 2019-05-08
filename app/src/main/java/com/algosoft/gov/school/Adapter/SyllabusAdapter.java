package com.algosoft.gov.school.Adapter;//package com.algosoft.gov.school.Adapter;
//
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.Parents.Syllabus;
import com.algosoft.gov.school.R;

import java.util.ArrayList;


/**
 * Created by abc on 24/11/2018.
 */


//
    public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.MyViewHolder> {
    private final ArrayList<String> mSubject;

    public SyllabusAdapter(Context context,ArrayList<String> Subject) {
        this.mSubject = Subject;
    }

    public SyllabusAdapter(Syllabus syllabus, ArrayList subject, ArrayList<String> Subject) {
        this.mSubject = Subject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabusview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.textView.setText(mSubject.get(position));

    }

    @Override
    public int getItemCount() {
        return mSubject.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.TextSyllabus);
        }
    }
}
//
//
//
//
//
//