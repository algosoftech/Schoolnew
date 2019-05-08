package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.R;

public class HomeWork extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        toolbar= (Toolbar) findViewById(R.id.Toolbar_Homework);
        ImageButton btnBack=(ImageButton)findViewById(R.id.back_hw);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button buttonhome=(Button)findViewById(R.id.Givehome);
        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeWork.this,GiveHomework.class);
                i.putExtra("title","Give Homework");
                startActivity(i);

            }
        });
        Button buttonsearch=(Button)findViewById(R.id.Searchhome);
        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeWork.this,SearchHomework.class);
                i.putExtra("title","Search Homework");
                startActivity(i);
                
            }
        });
    }
}
