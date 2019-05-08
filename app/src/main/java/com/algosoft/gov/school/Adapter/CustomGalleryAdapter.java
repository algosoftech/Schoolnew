package com.algosoft.gov.school.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.algosoft.gov.school.Fragment.ImageFragment;

/**
 * Created by abc on 27/11/2018.
 */

public class CustomGalleryAdapter extends BaseAdapter {
    private Context context;

    public CustomGalleryAdapter(Context c) {
        context = c;
//        this.image = image;
    }

    public CustomGalleryAdapter(ImageFragment imageFragment, int[] image) {

    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(context);
//        imageView.setImageResource(image[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(200,200));
        return imageView;
    }
}
