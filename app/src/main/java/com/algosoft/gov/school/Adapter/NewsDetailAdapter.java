package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.NewsDetail;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Monika on 1/8/2019.
 */

public class NewsDetailAdapter extends RecyclerView.Adapter<NewsDetailAdapter.MyViewHolder> {
    private final Context mcontext;
    private final NewsDetail mnewsdetail;

    public NewsDetailAdapter(Context context,NewsDetail newsdata) {
        mcontext = context;
        mnewsdetail = newsdata;
    }

    @NonNull
    @Override
    public NewsDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapternewsdetails,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDetailAdapter.MyViewHolder holder, int position) {
        holder.text_title.setText(mnewsdetail.getNewsTitle());
        holder.textnews1.setText(Html.fromHtml(mnewsdetail.getNewsContent()));
        holder.textnews2.setText(mnewsdetail.getNewsYear());
        String newsId = mnewsdetail.getNewsId();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_menu_camera);
        requestOptions.error(R.drawable.ic_menu_camera);
        Glide.with(mcontext)
                .setDefaultRequestOptions(requestOptions)
                .load(mnewsdetail.getNewsImage()).into(holder.news_image);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final CardView news_card;
        private final ImageView news_image;
        private final TextView text_title;
        TextView textnews,textnews1,textnews2,textnews3;
        public MyViewHolder(View itemView) {
            super(itemView);
            news_image=(ImageView)itemView.findViewById(R.id.news_image);
            textnews1=(TextView)itemView.findViewById(R.id.TextNews1);
            textnews2=(TextView)itemView.findViewById(R.id.TextNews2);
            text_title=(TextView)itemView.findViewById(R.id.title_tv);
            news_card = (CardView)itemView.findViewById(R.id.news_card);
        }
    }
}
