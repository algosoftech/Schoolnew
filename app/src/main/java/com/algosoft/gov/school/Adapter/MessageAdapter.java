package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.Message;
import com.algosoft.gov.school.response.MessageList;

import java.util.ArrayList;

/**
 * Created by abc on 17/11/2018.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private final ArrayList<MessageList> nMessage;
    Context context;



    public MessageAdapter(Context context, ArrayList message) {
        this.nMessage=message;
        this.context= context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messageview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.dateTime.setText(nMessage.get(position).getMessageDate());
        if(Message.messageType.equalsIgnoreCase("received")) {
            holder.senderName.setText(nMessage.get(position).getMessageFrom());
            String from=nMessage.get(position).getMessageFrom();
            if(!nMessage.get(position).getMessageFrom().trim().isEmpty()) {
                holder.nameInitial.setText(nMessage.get(position).getMessageFrom().trim());
//                holder.nameInitial.setText(String.valueOf(nMessage.get(position).getMessageFrom().toString()).charAt(0));

            }else{
                holder.nameInitial.setText("!");
            }
        }else if(Message.messageType.equalsIgnoreCase("sent")){
            String to= nMessage.get(position).getMessageTo();
            holder.senderName.setText(nMessage.get(position).getMessageTo());
            try {
                if(!nMessage.get(position).getMessageTo().trim().isEmpty()) {
//                    holder.nameInitial.setText(to.charAt(0));
                }else{
                    holder.nameInitial.setText("!");
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        holder.message.setText(nMessage.get(position).getMessageContent());
    }



    @Override
    public int getItemCount()
    {
        return nMessage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgCircle;
        TextView message, nameInitial, senderName, dateTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            message = (TextView) itemView.findViewById(R.id.msg_content);
            imgCircle= (ImageView) itemView.findViewById(R.id.img_circle);
            nameInitial= (TextView) itemView.findViewById(R.id.name_initial);
            senderName= (TextView) itemView.findViewById(R.id.sender_name);
            dateTime= (TextView) itemView.findViewById(R.id.msg_date_time);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
