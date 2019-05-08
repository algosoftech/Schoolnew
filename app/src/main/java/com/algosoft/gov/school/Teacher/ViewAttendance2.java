package com.algosoft.gov.school.Teacher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.ViewAttendanceAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.AttendanceDetail;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
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

public class ViewAttendance2 extends AppCompatActivity {
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private LinearLayoutManager linearLayoutManager;
    private String classId,sectionId,date;
    private String branchCode,userId,boardId,type;
    private TextView datetext;
    private ArrayList<AttendanceDetail> attendancelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance2);
        attendancelist = new ArrayList<>();
        datetext = (TextView)findViewById(R.id.date_tv);
        branchCode =  PreferenceUtil.getBranchCode(ViewAttendance2.this);
        userId = PreferenceUtil.getUserId(ViewAttendance2.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(ViewAttendance2.this);
        type = PreferenceUtil.getSelectedTypeFromServer(ViewAttendance2.this);

        classId =  getIntent().getStringExtra("ClassId");
        sectionId =  getIntent().getStringExtra("SectionId");
        date = getIntent().getStringExtra("Date");
        datetext.setText(date);
//        Toast.makeText(this, "Detail"+classId+sectionId+date, Toast.LENGTH_SHORT).show();
        ImageView imageView=(ImageView)findViewById(R.id.Image_ViewAttendance);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerStudentAttendance);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new attendanceListClass().execute(branchCode,type,userId,boardId,classId,sectionId,date,"All");
        }else{
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

    }

    public class attendanceListClass extends AsyncTask<String, String,JSONObject>
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
            userData.add(new BasicNameValuePair("date", params[6]));
            userData.add(new BasicNameValuePair("attendanceType", params[7]));
            JSONObject jsonObject=webServicesURL.getAttendanceData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("AttendanceList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        attendancelist = responseClass.getResult().getAttendanceDetailArrayList();
                        PreferenceUtil.setClassListFromServer(ViewAttendance2.this,gson.toJson(attendancelist));
                        adapter=new ViewAttendanceAdapter(ViewAttendance2.this,attendancelist);
                        recyclerView.setAdapter(adapter);
                    }else {
                        Log.e("ClassList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
