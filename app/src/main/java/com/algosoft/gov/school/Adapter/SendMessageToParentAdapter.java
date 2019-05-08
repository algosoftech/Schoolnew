package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.ComposeMessage;
import com.algosoft.gov.school.response.StudentDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SendMessageToParentAdapter extends RecyclerView.Adapter<SendMessageToParentAdapter.MyViewHolder> {
    private ArrayList<StudentDetail>mstudentlist;
    private ArrayList<StudentDetail>mStudentDetailsList;
    private Context mcontext;
    private Selectedinterface selectedinterface;
    String studentId;
    public HashMap<String,Boolean> StudentSelected;


    public SendMessageToParentAdapter(Context context, ArrayList<StudentDetail> studentList, ComposeMessage composeMessage) {
        this.mcontext=context;
        this.mstudentlist=studentList;
        this.selectedinterface=composeMessage;

    }

    @Override
    public SendMessageToParentAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sendmessageparent,parent,false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SendMessageToParentAdapter.MyViewHolder holder, int position) {
        holder.text_title.setText(mstudentlist.get(position).getStudentName());
        holder.text_message.setText(mstudentlist.get(position).getStudentId());



    }

    @Override
    public int getItemCount() {
        return mstudentlist.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView text_message,text_title;
        CheckBox checkBox;



        public MyViewHolder(View itemView) {
            super(itemView);

            text_message=(TextView)itemView.findViewById(R.id.Text_Message);
            text_title=(TextView)itemView.findViewById(R.id.Text_title);
            checkBox=(CheckBox)itemView.findViewById(R.id.Check_box);
            checkBox.setChecked(false);




            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//                    List<boolean>studentData=new ArrayList<boolean>();
                    if(isChecked) {
                        studentId = mstudentlist.get(getLayoutPosition()).getStudentId();
                        Log.e("StudentId", "" + studentId);
                        selectedinterface.addselected(studentId, true);
//                        StudentSelected.put(studentId,true);
                        Log.e("SelectedData",""+StudentSelected);
                    }else if (!isChecked){
                        studentId = mstudentlist.get(getLayoutPosition()).getStudentId();
                        Log.e("StudentIdUncheck", "" + studentId);
                        selectedinterface.addselected(studentId, false);
                    }

                }
            });


        }

    }
    public interface Selectedinterface {
        void addselected(String id,boolean state);
    }
}
