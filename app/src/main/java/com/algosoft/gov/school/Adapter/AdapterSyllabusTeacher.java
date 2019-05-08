package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * Created by abc on 05/12/2018.
 */

public class AdapterSyllabusTeacher extends RecyclerView.Adapter<AdapterSyllabusTeacher.MyViewHolder> {
    private final String mUnits;

    public AdapterSyllabusTeacher(Context context,String Units) {
        this.mUnits = Units;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.syllabusdetailteacher, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
       // holder.textunit.setText(Html.fromHtml(mUnits));
//                +"<style>p{display: inline;height: auto;max-width: 100%;}</style>" +"""<p>Lorem Ipsum&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<\/p>"));
        holder.webView.loadDataWithBaseURL(null,"<style>p{display: inline;height: auto;max-width: 100%;}</style>"+mUnits ,"text/html", "UTF-8",null);


//        String subject=mUnits.trim();
//        if(subject.endsWith("! ") || subject.endsWith("? ") || subject.endsWith(". ")) {
//            subject = subject + '\n';
//            Log.e("Subject",""+subject);

        //}



    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textunit;
        WebView webView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textunit=(TextView)itemView.findViewById(R.id.TextUnit);
            webView= (WebView) itemView.findViewById(R.id.WebView);
        }
    }
}
