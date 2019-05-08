package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.Teacher.ListNotification;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.StudentReply;
import com.algosoft.gov.school.response.NotificationList;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by abc on 19/11/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private final ArrayList<NotificationList> mQuiz;
    private final Context mcontext;
    private int id;

    public NotificationAdapter(Context context, ArrayList<NotificationList> Quiz) {
        this.mQuiz=Quiz;
        mcontext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapternewsdetails, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( NotificationAdapter.MyViewHolder holder, int position) {
        holder.Message.setText(mQuiz.get(position).getNotificationMessage());
        holder.Date.setText(mQuiz.get(position).getNotificationDate());
       // holder.Title.setText(mQuiz.get(position).getNotificationId());


    }

    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Message,Date,Title;

        public MyViewHolder(View itemView) {
            super(itemView);

            Date=(TextView)itemView.findViewById(R.id.TextNews1);
            Message=(TextView)itemView.findViewById(R.id.TextNews2);
//            Title=(TextView)itemView.findViewById(R.id.title_tv);












//            Quiz=(TextView)itemView.findViewById(R.id.Text_Quiz);
//            Question=(TextView)itemView.findViewById(R.id.Text_Question);
//            Answer=(EditText)itemView.findViewById(R.id.Edit_Answer);
//            imageInformation=(ImageView)itemView.findViewById(R.id.Image_Information1);
//            imageInformation.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i=new Intent(mcontext, StudentReply.class);
//                    mcontext.startActivity(i);
//                }
//            });
//            imageinfo=(ImageView)itemView.findViewById(R.id.Image_info);
//            imageinfo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent myactivity = new Intent(mcontext, ListNotification.class);
//                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
//                    mcontext.startActivity(myactivity);
//                }
//            });
        }



    }
}
