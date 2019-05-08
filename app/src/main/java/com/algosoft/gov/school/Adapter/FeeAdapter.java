package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Parents.FeeMonthDetails;
import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * Created by abc on 24/11/2018.
 */

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.MyViewHolder>{
    private final ArrayList<String> nMonth;
    private Context mcontext;

    public FeeAdapter(Context context,ArrayList<String> Month) {

        this.nMonth = Month;
        mcontext=context;
    }

    @Override
    public FeeAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feemonth,parent,false);
        view.setOnClickListener(Home.myOnClickListener);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( FeeAdapter.MyViewHolder holder, int position) {
        holder.textmonth.setText(nMonth.get(position));

    }

    @Override
    public int getItemCount() {
        return nMonth.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textmonth;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textmonth=(TextView)itemView.findViewById(R.id.Textmonth);
            cardView=(CardView)itemView.findViewById(R.id.Month_fee);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), FeeMonthDetails.class);
                    mcontext.startActivity(intent);

                }
            });

        }
    }
}
