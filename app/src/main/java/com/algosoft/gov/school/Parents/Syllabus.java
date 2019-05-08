package com.algosoft.gov.school.Parents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

//import com.algosoft.gov.school.Adapter.SyllabusAdapter;
import com.algosoft.gov.school.Adapter.SyllabusAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Syllabus extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList Subject = new ArrayList<>(Arrays.asList("Hindi","English","Math","Science"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus2);

        ImageView imageView=(ImageView)findViewById(R.id.Image_backSyllabus);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerSyllabus);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new SyllabusAdapter(Syllabus.this,Subject);
        recyclerView.setAdapter(adapter);
    }
}
