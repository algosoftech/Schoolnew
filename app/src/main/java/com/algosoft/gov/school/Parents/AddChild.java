package com.algosoft.gov.school.Parents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.algosoft.gov.school.R;

public class AddChild extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        ImageView imageadd=(ImageView)findViewById(R.id.ImageAdd);
        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AddChild.this,SecondChild.class);
                startActivity(i);
            }
        });

        ImageView imageView=(ImageView)findViewById(R.id.Image_AddChild);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
