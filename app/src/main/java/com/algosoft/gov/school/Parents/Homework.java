package com.algosoft.gov.school.Parents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.algosoft.gov.school.Adapter.ParentHomeworkAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList sublist = new ArrayList<>(Arrays.asList("Hindi","English","Report","Math"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);

        ImageView imageView=(ImageView)findViewById(R.id.Image_home);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerHomework);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new ParentHomeworkAdapter(Homework.this,sublist);
        recyclerView.setAdapter(adapter);
    }
}
