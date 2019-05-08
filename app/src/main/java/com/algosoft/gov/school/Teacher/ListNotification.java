package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.algosoft.gov.school.Adapter.ListNotificationAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ListNotification extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList namelist = new ArrayList<>(Arrays.asList("Kshitij Patel","Mukesh Kumar"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notification);

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new ListNotificationAdapter(ListNotification.this,namelist);
        recyclerView.setAdapter(adapter);


        ImageView imageView=(ImageView) findViewById(R.id.Image_time_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
