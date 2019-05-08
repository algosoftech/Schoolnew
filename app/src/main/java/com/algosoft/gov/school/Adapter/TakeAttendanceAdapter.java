package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.StudentDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monika on 1/7/2019.
 */

public class TakeAttendanceAdapter extends RecyclerView.Adapter<TakeAttendanceAdapter.MyViewHolder> {
    private  Context mcontext;
    public   ArrayList<StudentDetail> mStudentNames;
    private  String mclassid,msectionid;
    public ArrayList<StudentDetail> idlist = new ArrayList<>();



    public TakeAttendanceAdapter(Context context, ArrayList<StudentDetail> studentdetail, String classid, String sectionid) {
        mcontext = context;
        this.mclassid=classid;
        this.msectionid=sectionid;
        this.mStudentNames=studentdetail;
    }

    public TakeAttendanceAdapter() {

    }

    @NonNull
    @Override
    public TakeAttendanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewattendance,parent,false);
        MyViewHolder myholder = new MyViewHolder(v);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull TakeAttendanceAdapter.MyViewHolder holder, final int position) {
        mStudentNames.get(position).setSelected(true);
        holder.Name.setText(mStudentNames.get(position).getStudentName());
        holder.btnPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mcontext, "present", Toast.LENGTH_SHORT).show();
                mStudentNames.get(position).setSelected(true);
            }
        });
        holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mcontext, "absent", Toast.LENGTH_SHORT).show();
                mStudentNames.get(position).setSelected(false);
            }
        });
//        holder.textpresent.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                CheckBox cb = (CheckBox) v;
//                StudentDetail contact = (StudentDetail) cb.getTag();
//                contact.setSelected(cb.isChecked());
//                mStudentNames.get(position).setSelected(cb.isChecked());
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mStudentNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements RadioButton.OnClickListener {
        TextView Name;
        RadioGroup radioGroup;
        RadioButton btnPresent, btnAbsent;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.Text_Name);
            radioGroup= (RadioGroup) itemView.findViewById(R.id.radioGroup);
            btnPresent= (RadioButton) itemView.findViewById(R.id.radio_present);
            btnAbsent= (RadioButton) itemView.findViewById(R.id.radio_absent);
            btnPresent.setOnClickListener(this);
            btnAbsent.setOnClickListener(this);
//            radioGroup.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); boolean checked = ((RadioButton) v).isChecked();

            // Check which radio button was clicked
            switch(v.getId()) {
                case R.id.radio_present:
                    if (checked)
                        break;
                case R.id.radio_absent:
                    if (checked)
                        break;
            }
        }
    }

    // method to access in activity after updating selection
    public ArrayList<StudentDetail> getStudentist() {

        return mStudentNames;
    }
}
