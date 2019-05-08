package com.algosoft.gov.school.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.algosoft.gov.school.Adapter.AssignmentAdapter;
import com.algosoft.gov.school.Adapter.HomeworkAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.HomeWorkList;
import com.algosoft.gov.school.storage.PreferenceUtil;

import java.util.ArrayList;

public class DisplayHomework extends AppCompatActivity {
RecyclerView recyclerView;
LinearLayoutManager layoutManager;
ArrayList<HomeWorkList> homeWorkLists= new ArrayList<HomeWorkList>();
HomeworkAdapter adapter;
AssignmentAdapter adapter2;
android.support.v7.app.ActionBar actionBar;
android.support.v7.widget.Toolbar toolbar;
TextView totalSearchResult, title;
ImageButton btnBack;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_homework);
        toolbar = (Toolbar) findViewById(R.id.toolbar_hw);
        btnBack= (ImageButton) findViewById(R.id.hw_assign_back);
        title= (TextView) findViewById(R.id.hw_assign_title);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });
        totalSearchResult= (TextView) findViewById(R.id.search_result);
        recyclerView= (RecyclerView) findViewById(R.id.recView_HwList);

        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(getIntent().getStringExtra("title").equalsIgnoreCase("Assignment List")){
            title.setText(getIntent().getStringExtra("title"));
            totalSearchResult.setText(SearchAssignment.assignmentList.size()+" search results found");
            adapter2 = new AssignmentAdapter(this, SearchAssignment.assignmentList);
            recyclerView.setAdapter(adapter2);
        }else if(getIntent().getStringExtra("title").equalsIgnoreCase("Homework List")){
            title.setText(getIntent().getStringExtra("title"));
            totalSearchResult.setText(SearchHomework.homeWorkList.size()+" search results found");
            adapter = new HomeworkAdapter(this, SearchHomework.homeWorkList);
            recyclerView.setAdapter(adapter);
        }
    }
}
