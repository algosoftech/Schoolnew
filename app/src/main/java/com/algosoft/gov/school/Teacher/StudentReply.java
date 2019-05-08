package com.algosoft.gov.school.Teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.algosoft.gov.school.Adapter.StudentReplyAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentReply extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList Replylist = new ArrayList<>(Arrays.asList("Manika","Osho"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reply);

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerReply);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new StudentReplyAdapter(StudentReply.this,Replylist);
        recyclerView.setAdapter(adapter);

        ImageView imageView=(ImageView)findViewById(R.id.Image_Reply_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
