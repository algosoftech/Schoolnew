package com.algosoft.gov.school.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.NewsDetailActivity;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.NoticBoard;
import com.algosoft.gov.school.response.NewsDetail;
import com.algosoft.gov.school.response.NoticeBoardList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abc on 04/12/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private final ArrayList<NoticeBoardList> mNews;
    private CircleImageView news_image;
    private NoticBoard mcontext;
    private String newsId;

    public NewsAdapter(NoticBoard context, ArrayList<NoticeBoardList>News) {
        this.mcontext = context;
        this.mNews=News;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.textnews1.setText(mNews.get(position).getNoticeBoardDate());
        holder.textnews2.setText(mNews.get(position).getNoticeBoardMessage());
        newsId = mNews.get(position).getNoticeBoardId();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_menu_camera);
        requestOptions.error(R.drawable.ic_menu_camera);
        Glide.with(mcontext)
                .setDefaultRequestOptions(requestOptions)
                .load(mNews.get(position).getNoticeBoardImage()).into(news_image);

//        holder.news_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(),NewsDetailActivity.class);
//                i.putExtra("NewsId",newsId);
//                mcontext.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CardView news_card;
        TextView textnews,textnews1,textnews2,textnews3;
        public MyViewHolder(View itemView) {
            super(itemView);

            news_image=(CircleImageView)itemView.findViewById(R.id.news_image);
            textnews1=(TextView)itemView.findViewById(R.id.TextNews1);
            textnews2=(TextView)itemView.findViewById(R.id.TextNews2);
            news_card = (CardView)itemView.findViewById(R.id.news_card);



            
        }
    }
}
