package com.algosoft.gov.school.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.GalleryImageActivity;
import com.algosoft.gov.school.Fragment.ImageFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GalleryDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by abc on 28/11/2018.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {
    private ImageFragment mcontext;
    private final ArrayList<GalleryDetails> mNameImage;
    private String galleryId;
//    private final ArrayList<String>mImage;

    public GalleryAdapter(ImageFragment context, ArrayList<GalleryDetails> NameImage) {
        mcontext=context;
        this.mNameImage = NameImage;
//        this.mImage = Image;
    }

    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.imageview,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( GalleryAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mNameImage.get(position).getGalleryName());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_menu_camera);
        requestOptions.error(R.drawable.ic_menu_camera);
        final String image = mNameImage.get(position).getGalleryImage();
        galleryId =  mNameImage.get(position).getGalleryId();


        Glide.with(mcontext)
                .setDefaultRequestOptions(requestOptions)
                .load(mNameImage.get(position).getGalleryImage()).into(holder.imagegallery);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(v.getContext(),GalleryImageActivity.class);
                mainIntent.putExtra("Image",image);
                mainIntent.putExtra("GalleryId",galleryId);
                mcontext.startActivity(mainIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNameImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardview;
        TextView textView;
        ImageView imagegallery;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.TextImage);
            imagegallery=(ImageView)itemView.findViewById(R.id.Imagegallery);
            cardview =  (CardView)itemView.findViewById(R.id.cardview);

        }
    }
}
