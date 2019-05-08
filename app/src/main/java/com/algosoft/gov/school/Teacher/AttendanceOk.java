package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.AttendanceAdapter;
import com.algosoft.gov.school.Fragment.LeaveFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.LeaveTypeDetails;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.StudentDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttendanceOk extends AppCompatActivity {
    TextView textpresent,textabsent;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList StudentNames = new ArrayList<>(Arrays.asList("Kshitij Patel", "Mukesh Kumar", "Onam", "Tanya"));
    ArrayList RollNo = new ArrayList<>(Arrays.asList("01", "02", "03", "04"));
    private String classId,sectionId;
    private String branchCode,userId,boardId,type;
    private ArrayList<StudentDetail> studentlist;

    @Override
    public boolean onSupportNavigateUp() {
//        backConfirm();
//        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_ok);
        studentlist = new ArrayList<>();
        classId =  getIntent().getStringExtra("Classid");
        sectionId = getIntent().getStringExtra("Sectionid");
        branchCode =  PreferenceUtil.getBranchCode(AttendanceOk.this);
        userId = PreferenceUtil.getUserId(AttendanceOk.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(AttendanceOk.this);
        type = PreferenceUtil.getSelectedTypeFromServer(AttendanceOk.this);


        ImageView imageView=(ImageView)findViewById(R.id.Image_Attendance);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerAttendance);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        if (NetworkStatus.isNetworkAvailable(AttendanceOk.this)) {
            new LeaveListClass().execute(branchCode,type,userId,boardId,classId,sectionId);
        }else{
            Toast.makeText(AttendanceOk.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

    }

    private void backConfirm() {
    }

    public class LeaveListClass extends AsyncTask<String, String,JSONObject>
    {




        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("boardId", params[3]));
            userData.add(new BasicNameValuePair("classId", params[4]));
            userData.add(new BasicNameValuePair("sectionId", params[5]));

            JSONObject jsonObject=webServicesURL.getStudentListData(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("StudentList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        studentlist = responseClass.getResult().getStudentDetailArrayList();
                        PreferenceUtil.setStudentListFromServer(AttendanceOk.this,gson.toJson(studentlist));
                        adapter = new AttendanceAdapter(AttendanceOk.this,studentlist);
                        recyclerView.setAdapter(adapter);

                    }else {
                        Log.e("LeaveList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
