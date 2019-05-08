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
 * Created by abc on 26/11/2018.
 */

public class SchoolfeeAdapter extends RecyclerView.Adapter<SchoolfeeAdapter.MyViewHolder>{
    private final ArrayList<String> mfeeamount;
    private final ArrayList<String> mfeetype;

    public SchoolfeeAdapter(Context context, ArrayList<String> feeamount, ArrayList<String> mfeetype) {
        this.mfeeamount = feeamount;
        this.mfeetype = mfeetype;
    }


    @Override
    public SchoolfeeAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolfeedetails, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SchoolfeeAdapter.MyViewHolder holder, int position) {
        holder.texttype.setText(mfeetype.get(position));
        holder.textamount.setText(mfeeamount.get(position));

    }

    @Override
    public int getItemCount() {
        return mfeetype.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView texttype,textamount;
        public MyViewHolder(View itemView) {
            super(itemView);
            texttype=(TextView)itemView.findViewById(R.id.Texttype);
            textamount=(TextView)itemView.findViewById(R.id.Textfee);
        }
    }
}
