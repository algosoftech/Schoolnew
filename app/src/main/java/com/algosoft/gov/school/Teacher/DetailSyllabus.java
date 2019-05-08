package com.algosoft.gov.school.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.AdapterSyllabusTeacher;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailSyllabus extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_syllabus);

        String syllabus = getIntent().getStringExtra("Syllabus");

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerSyllabus);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new AdapterSyllabusTeacher(DetailSyllabus.this,syllabus);
        recyclerView.setAdapter(adapter);


    }
}
