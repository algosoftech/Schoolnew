package com.algosoft.gov.school.Activity;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GalleryImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout linearLayout;
    ArrayList<String> sliderlist;
    String slider;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
//        getSupportActionBar().setTitle("Filter Images");
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

        sliderlist=new ArrayList<String>();
        sliderlist = (ArrayList<String>) getIntent().getStringArrayListExtra("ImageList");
        Log.e("ImageFilterList",""+sliderlist);
        viewPager=(ViewPager)findViewById(R.id.ViewPager_Image);

        linearLayout=(LinearLayout)findViewById(R.id.Linear_Image);

        viewPager.setAdapter(new ViewPagerAdapter(sliderlist));
        displayProductImage(sliderlist);


    }

    private void displayProductImage(final ArrayList<String> slider) {
        dots=new ImageView[slider.size()];
        for (int i=0;i<slider.size();i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

//            dots[i].setLayoutParams(params);
            linearLayout.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ative_dots));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<slider.size();i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ative_dots));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ViewPagerAdapter extends PagerAdapter{
        ArrayList<String>msliderlist= new ArrayList<>();

        public ViewPagerAdapter(ArrayList<String> sliderlist) {
            this.msliderlist=sliderlist;
        }

        @Override
        public int getCount() {
            return (null != msliderlist) ? msliderlist.size() : 0;
        }

        @Override
        public boolean isViewFromObject( View view,  Object object) {
            return  view==object;
        }

        @Override
        public Object instantiateItem( ViewGroup container, int position) {
            LayoutInflater layoutInflater=LayoutInflater.from(FilterActivity.this);
            ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.sliderimage,container,false);
            ImageView imageView=(ImageView)viewGroup.findViewById(R.id.ImageView);
            Picasso.with(getApplicationContext())
                    .load(msliderlist.get(position))
                    .into(imageView);


            container.addView(viewGroup);

            return viewGroup;

        }

        @Override
        public void destroyItem( ViewGroup container, int position,  Object object) {
            container.removeView((RelativeLayout) object);

        }


    }
}
