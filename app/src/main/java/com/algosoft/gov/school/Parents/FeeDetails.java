package com.algosoft.gov.school.Parents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.algosoft.gov.school.Adapter.SchoolfeeAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FeeDetails extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList feetype = new ArrayList<>(Arrays.asList("Admission fee","Uniform fee","Annual fee"));
    ArrayList feeamount = new ArrayList<>(Arrays.asList("4356","2334","323323","34244"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);
        ImageView imageView=(ImageView)findViewById(R.id.Image_feedetails) ;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.Recyclerfeestructure);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new SchoolfeeAdapter(FeeDetails.this,feeamount,feetype);
        recyclerView.setAdapter(adapter);

    }
}
