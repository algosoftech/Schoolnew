package com.algosoft.gov.school.Parents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.Adapter.FeeAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Fee extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList Month = new ArrayList<>(Arrays.asList("January","February","March","April","May","June","July","August","September"
            ,"October","November","December"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);

        ImageView imageView=(ImageView)findViewById(R.id.Image_fee);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.Recyclerfee);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new FeeAdapter(Fee.this,Month);
        recyclerView.setAdapter(adapter);

        TextView textView=(TextView)findViewById(R.id.Textreceipt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Fee.this,FeeDetails.class);
                startActivity(i);

            }
        });

    }
}
