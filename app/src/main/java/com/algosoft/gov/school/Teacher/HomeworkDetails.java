package com.algosoft.gov.school.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.algosoft.gov.school.R;
import com.bumptech.glide.Glide;

public class HomeworkDetails extends AppCompatActivity {
TextView tvDesc,tvSubject,tvSection, tvClass,tvStartDate, tvEndDate, imgCaption, toolbarTitle;
ImageView fileImage, fullScreenImg;
ImageButton btnBack;
ScrollView scrollView;
android.support.v7.widget.Toolbar toolbar;
Boolean isImageFull= false;
protected String title, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_details);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_hw);
        toolbarTitle= (TextView) findViewById(R.id.tb_title);
        btnBack= (ImageButton) findViewById(R.id.back_hwDetails);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });
        scrollView= (ScrollView) findViewById(R.id.scrollable_hwdetails);
        tvDesc=(TextView) findViewById(R.id.desc);
        tvSubject=(TextView) findViewById(R.id.subject_name);
        tvSection= (TextView) findViewById(R.id.section_name);
        tvClass= (TextView) findViewById(R.id.class_name);
        tvStartDate= (TextView) findViewById(R.id.startOn);
        tvEndDate= (TextView) findViewById(R.id.endOn);
        imgCaption= (TextView) findViewById(R.id.img_caption);
        fileImage= (ImageView) findViewById(R.id.image_file);
        fullScreenImg= (ImageView) findViewById(R.id.img_fullScreen);

        if(!getIntent().getExtras().isEmpty()){
            title=getIntent().getStringExtra("title");
            toolbarTitle.setText(title);
            tvDesc.setText(getIntent().getStringExtra("desc"));
            tvSubject.setText(getIntent().getStringExtra("subject"));
            tvClass.setText(getIntent().getStringExtra("class")+" ,");
            tvSection.setText(getIntent().getStringExtra("section"));
            tvStartDate.setText(getIntent().getStringExtra("startDate"));
            tvEndDate.setText(getIntent().getStringExtra("endDate"));
            if(getIntent().hasExtra("image")){
                url= getIntent().getStringExtra("image");
                Glide.with(this).load(url).into(fileImage);
                String[] imgName= url.split("\\/+");
                if(imgName.length>1) {
                    imgCaption.setText(imgName[6]);
                }
            }else{
                fileImage.setVisibility(View.GONE);
                imgCaption.setText("No Attachment available");
            }
        }
        fileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isImageFull=true;
                fullScreenImg.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                Glide.with(HomeworkDetails.this).load(url).into(fullScreenImg);
                toolbarTitle.setText(imgCaption.getText().toString());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(isImageFull){
            fullScreenImg.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            toolbarTitle.setText(title);
            isImageFull= false;
        }else {
            finish();
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(isImageFull){
            fullScreenImg.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            toolbarTitle.setText(title);
            isImageFull= false;
        }else {
            finish();
        }
    }
}
