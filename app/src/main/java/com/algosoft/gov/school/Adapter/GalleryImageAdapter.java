package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.FilterActivity;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GalleryImage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Algosoft on 1/4/2019.
 */

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.MyViewHolder> {
    private final Context mcontext;
    ArrayList<GalleryImage> gallery_image;
    private final String ga_id,ga_year,ga_name;
    ArrayList<String>Imagelist=new ArrayList<String>();

    public GalleryImageAdapter(Context context, ArrayList<GalleryImage> mgalleryimage,String g_id,String g_year,String g_name) {
        mcontext = context;
        gallery_image = mgalleryimage;
        ga_id = g_id;
        ga_year = g_year;
        ga_name = g_name;
    }

    @NonNull
    @Override
    public GalleryImageAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( GalleryImageAdapter.MyViewHolder holder, int position) {
        String imageurl = gallery_image.get(position).getImage();
        Imagelist.add(gallery_image.get(position).getImage());

        holder.textView.setText(ga_name);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_menu_camera);
        requestOptions.error(R.drawable.ic_menu_camera);

        Picasso.with(mcontext)
                .load(imageurl)
                .into(holder.imagegallery);


//        Glide.with(mcontext)
//                .setDefaultRequestOptions(requestOptions)
//                .load(imageurl).into(holder.imagegallery);
        holder.imagegallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mcontext, FilterActivity.class);

                i.putExtra("ImageList",Imagelist);
                mcontext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gallery_image.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private final CardView cardview;
        TextView textView;
        ImageView imagegallery;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.TextImage);
            imagegallery=(ImageView)itemView.findViewById(R.id.Imagegallery);
            cardview =  (CardView)itemView.findViewById(R.id.cardview);


        }


    }
}
