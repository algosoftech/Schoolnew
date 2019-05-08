package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.FeedBackListDetail;

import java.util.ArrayList;

/**
 * Created by Algosoft on 1/14/2019.
 */

public class ReceivedDetailAdapter extends RecyclerView.Adapter<ReceivedDetailAdapter.MyViewHolder> {
    private final Context mcontext;
    private final ArrayList<FeedBackListDetail> mfeedbackList;

    public ReceivedDetailAdapter(Context context, ArrayList<FeedBackListDetail> feedbacklist) {
        mcontext = context;
        mfeedbackList = feedbacklist;
    }

    @NonNull
    @Override
    public ReceivedDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_sent,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceivedDetailAdapter.MyViewHolder holder, int position) {
        holder.feedbackId.setText(mfeedbackList.get(position).getFeedbackId());
        holder.feedbackFrom.setText(mfeedbackList.get(position).getFeedbackFrom());
        holder.feedbackClass.setText(mfeedbackList.get(position).getFeedbackClass());
        holder.feedbackTo.setText(mfeedbackList.get(position).getFeedbackTO());
        holder.feedbackSection.setText(mfeedbackList.get(position).getFeedbackSection());
        holder.feedbackSubject.setText(mfeedbackList.get(position).getFeedbackSubject());
        holder.feedbackMessage.setText(mfeedbackList.get(position).getFeedbackMessage());
        holder.wayOfTeching.setText(mfeedbackList.get(position).getWayOfTeaching());
        holder.knowledgeAboutSubject.setText(mfeedbackList.get(position).getKnowledgeAboutSubject());
        holder.coordinationTextview.setText(mfeedbackList.get(position).getClassCoOrdination());
        holder.knowledgeSharing.setText(mfeedbackList.get(position).getKnowledgeSharing());
        holder.messageDate.setText(mfeedbackList.get(position).getMessageDate());
    }

    @Override
    public int getItemCount() {
        return mfeedbackList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView feedbackId,feedbackFrom,feedbackTo,feedbackClass,feedbackSection,feedbackSubject,feedbackMessage,wayOfTeching,
                knowledgeAboutSubject,coordinationTextview,knowledgeSharing,messageDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            feedbackId = (TextView)itemView.findViewById(R.id.feedbackid_tv);
            feedbackFrom = (TextView)itemView.findViewById(R.id.feedback_from_tv);
            feedbackTo = (TextView)itemView.findViewById(R.id.feedback_to_tv);
            feedbackClass = (TextView)itemView.findViewById(R.id.feedback_class_tv);
            feedbackSection = (TextView)itemView.findViewById(R.id.feedback_section_tv);
            feedbackSubject  =(TextView)itemView.findViewById(R.id.feedback_subject_tv);
            feedbackMessage =(TextView)itemView.findViewById(R.id.feedback_message_tv);
            wayOfTeching  =  (TextView)itemView.findViewById(R.id.way_of_teaching_tv);
            knowledgeAboutSubject  = (TextView)itemView.findViewById(R.id.knowledge_about_subject_tv);
            coordinationTextview = (TextView)itemView.findViewById(R.id.class_coordination_tv);
            knowledgeSharing = (TextView)itemView.findViewById(R.id.knowledge_sharing_tv);
            messageDate = (TextView)itemView.findViewById(R.id.message_date_tv);
        }
    }
}
