package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.DisplayHomework;
import com.algosoft.gov.school.Teacher.HomeworkDetails;
import com.algosoft.gov.school.response.AssignmentList;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    Context context;
    ArrayList<AssignmentList> assignmentLists;

    public AssignmentAdapter(DisplayHomework displayHomework, ArrayList<AssignmentList> assignmentList) {
        this.context = displayHomework;
        this.assignmentLists= assignmentList;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hw_list_item,null);
        AssignmentViewHolder vh= new AssignmentViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        holder.tvHwTitle.setText(assignmentLists.get(position).getAssignmentTitle());
        String fromdate= assignmentLists.get(position).getAssignmentFromDate();
        String[] newFrom= fromdate.split("\\s+");
        holder.tvStartDate.setText(newFrom[0]);
        String todate= assignmentLists.get(position).getAssignmentToDate();
        String[] newTo= todate.split("\\s+");
        holder.tvStartDate.setText(newFrom[0]);
        holder.tvEndDate.setText(newTo[0]);
        holder.tvHwDesc.setText(assignmentLists.get(position).getAssignmentContent());
    }

    @Override
    public int getItemCount() {
        return assignmentLists.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvHwTitle, tvHwDesc, tvStartDate, tvEndDate;
        public AssignmentViewHolder(View itemView) {
            super(itemView);
            tvHwTitle= (TextView) itemView.findViewById(R.id.hw_title);
            tvStartDate= (TextView) itemView.findViewById(R.id.start_date);
            tvEndDate= (TextView) itemView.findViewById(R.id.end_date);
            tvHwDesc= (TextView) itemView.findViewById(R.id.hw_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index= getLayoutPosition();
            Intent intent= new Intent(context, HomeworkDetails.class);
            intent.putExtra("class",assignmentLists.get(index).getClassName());
            intent.putExtra("section",assignmentLists.get(index).getSectionName());
            intent.putExtra("subject",assignmentLists.get(index).getSubjectName());
            intent.putExtra("title", assignmentLists.get(index).getAssignmentTitle());
            intent.putExtra("desc",assignmentLists.get(index).getAssignmentContent());
            intent.putExtra("startDate", tvStartDate.getText().toString());
            intent.putExtra("endDate", tvEndDate.getText().toString());
            if(assignmentLists.get(index).getAssignmentFile()!=null) {
                Log.d("IS IMG","YES"+index);
                intent.putExtra("image", assignmentLists.get(index).getAssignmentFile());
            }
            context.startActivity(intent);
        }
    }
}
