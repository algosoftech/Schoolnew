package com.algosoft.gov.school.Teacher;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.algosoft.gov.school.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ListAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_attendance);

        CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.CircularBar);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.colorYellow));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorgray));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms
    }
}
