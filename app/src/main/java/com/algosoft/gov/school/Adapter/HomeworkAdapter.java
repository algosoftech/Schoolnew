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
import com.algosoft.gov.school.response.HomeWorkList;

import java.util.ArrayList;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {
    Context context;
    ArrayList<HomeWorkList> homeWorkLists;

    public HomeworkAdapter(DisplayHomework displayHomework, ArrayList<HomeWorkList> homeWorkList) {
        this.context= displayHomework;
        this.homeWorkLists= homeWorkList;
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hw_list_item,null);
        HomeworkViewHolder vh = new HomeworkViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        holder.tvHwTitle.setText(homeWorkLists.get(position).getHomeWorkTitle());
        String fromdate= homeWorkLists.get(position).getHomeWorkFromDate();
        String[] newFrom= fromdate.split("\\s+");
        holder.tvStartDate.setText(newFrom[0]);
        String todate= homeWorkLists.get(position).getHomeWorkToDate();
        String[] newTo= todate.split("\\s+");
        holder.tvStartDate.setText(newFrom[0]);
        holder.tvEndDate.setText(newTo[0]);
        holder.tvHwDesc.setText(homeWorkLists.get(position).getHomeWorkContent());
    }

    @Override
    public int getItemCount() {
        return homeWorkLists.size();
    }

    public class HomeworkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvHwTitle, tvHwDesc, tvStartDate, tvEndDate;
        public HomeworkViewHolder(View itemView) {
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
            intent.putExtra("class",homeWorkLists.get(index).getClassName());
            intent.putExtra("section",homeWorkLists.get(index).getSectionName());
            intent.putExtra("subject",homeWorkLists.get(index).getSubjectName());
            intent.putExtra("title", homeWorkLists.get(index).getHomeWorkTitle());
            intent.putExtra("desc",homeWorkLists.get(index).getHomeWorkContent());
            intent.putExtra("startDate", tvStartDate.getText().toString());
            intent.putExtra("endDate", tvEndDate.getText().toString());
            if(homeWorkLists.get(index).getHomeWorkFile() !=null) {
                Log.d("IS IMG","YES"+index);
                intent.putExtra("image", homeWorkLists.get(index).getHomeWorkFile());
            }
            context.startActivity(intent);
        }
    }
}
