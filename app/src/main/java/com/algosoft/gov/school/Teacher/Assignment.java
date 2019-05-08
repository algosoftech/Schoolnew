package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.R;

public class Assignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

//        Toolbar toolbar=(Toolbar)findViewById(R.id.Toolbar_Assignment);
//        setSupportActionBar(toolbar);
//        Drawable drawable= ResourcesCompat.getDrawable(this.getResources(),R.drawable.back_black_24dp,null);
//        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(drawable);



        ImageButton btnBack=(ImageButton) findViewById(R.id.back_assign);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button button=(Button)findViewById(R.id.GiveAssignment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Assignment.this,GiveAssignment.class);
//                i.putExtra("title","Give Assignment");
                startActivity(i);

            }
        });

        Button button1=(Button)findViewById(R.id.SearchAssignment);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Assignment.this,SearchAssignment.class);
//                i.putExtra("title","Search Assignment");
                startActivity(i);

            }
        });
    }
}
