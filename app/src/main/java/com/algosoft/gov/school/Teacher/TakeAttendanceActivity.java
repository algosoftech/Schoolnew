package com.algosoft.gov.school.Teacher;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.TakeAttendanceAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.StudentDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendanceActivity extends AppCompatActivity {

    private String branchCode, userId, boardId, type, classId, sectionId, date, time, attendanceData;
    private RecyclerView recyclerView;
    private Button submit;
    private TakeAttendanceAdapter adapter;
    TextView tv_date, tv_total;
    private ImageView backBtn;
    private Button btn_cancel;
    Boolean isSubmit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance3);
        branchCode = PreferenceUtil.getBranchCode(TakeAttendanceActivity.this);
        userId = PreferenceUtil.getUserId(TakeAttendanceActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(TakeAttendanceActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(TakeAttendanceActivity.this);
        tv_date= (TextView) findViewById(R.id.tv_date);
        tv_date.setText(KeyGenerationClass.getDate());
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerAttendancee);
        submit = (Button) findViewById(R.id.done_btn);
        backBtn = (ImageView) findViewById(R.id.btn_AttendanceBack);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        branchCode = PreferenceUtil.getBranchCode(TakeAttendanceActivity.this);
        userId = PreferenceUtil.getUserId(TakeAttendanceActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(TakeAttendanceActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(TakeAttendanceActivity.this);

        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new classListClass().execute(branchCode, type, userId, boardId);
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backConfirm();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backConfirm();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = new JSONArray();
                String data = "";
                List<StudentDetail> stList = ((TakeAttendanceAdapter)adapter).getStudentist();
                for (int i = 0; i < stList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    StudentDetail singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {
                        try {
                            jsonObject.put(singleStudent.getStudentId(), "Present");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        data = data + "\n" + singleStudent.getStudentName();
                        Log.e("Student id & name", "" + singleStudent.getStudentId().toString());
                    }else{
                        try {
                            jsonObject.put(singleStudent.getStudentId(), "Absent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    jsonArray.put(jsonObject);
                }
//                Toast.makeText(TakeAttendanceActivity.this, "Selected Students: \n" + data, Toast.LENGTH_LONG).show();

                classId = PreferenceUtil.getClassListFromServer(TakeAttendanceActivity.this);
                Log.e("class Id",""+classId);
                sectionId = PreferenceUtil.getSectionListFromServer(TakeAttendanceActivity.this);
                date = KeyGenerationClass.getDate();
                time = KeyGenerationClass.getTime();
                attendanceData = jsonArray.toString();
                Log.e("Attendance data",""+attendanceData);

//                JSONArray jsonArray = [{"2018100006" : "Present"},{"2018100007" : "Absent"},{"2018100008" : "Present"};
//                attendanceData=[{"2018100006" : "Present"},{"2018100007" : "Absent"},{"2018100008" : "Present"};
                if(attendanceData.isEmpty()){
                    Toast.makeText(TakeAttendanceActivity.this, "Please set attendance of students!", Toast.LENGTH_SHORT).show();
                }else {
                    new SubmitAttendance().execute(branchCode, type, userId, boardId, classId, sectionId, date, time, attendanceData);
                }
            }
        });
    }

    private void backConfirm() {
        finish();
    }

    public class classListClass extends AsyncTask<String, String, JSONObject> {
        private String classIdlist, sectionIdlist;
        private ArrayList<StudentDetail> studentDetailArraylist;
        private JSONObject jsonObject;

         TextView tv_total= (TextView) findViewById(R.id.tv_total);

        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL = new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
                userData.add(new BasicNameValuePair("branchCode", params[0]));
                userData.add(new BasicNameValuePair("userType", params[1]));
                userData.add(new BasicNameValuePair("userId", params[2]));
                userData.add(new BasicNameValuePair("boardId", params[3]));
                jsonObject = webServicesURL.getOwnStudentListData(userData);
                Log.d("Json to pass1", "" + jsonObject);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                if (jsonObject != null) {
//                    Toast.makeText(TakeAttendanceActivity.this, "" +jsonObject.get("message"), Toast.LENGTH_SHORT).show();
                    Log.e("OwnStudentList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        classIdlist = responseClass.getResult().getClassId();
                        sectionIdlist = responseClass.getResult().getSectionId();
                        studentDetailArraylist = responseClass.getResult().getStudentDetailArrayList();
                        tv_total.setText(responseClass.getResult().getStudentDetailArrayList().size()+" students");
//                        Toast.makeText(TakeAttendanceActivity.this, "OwnStudentList" + classIdlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setOwnClassListFromServer(TakeAttendanceActivity.this, gson.toJson(classIdlist));
                        adapter = new TakeAttendanceAdapter(TakeAttendanceActivity.this, studentDetailArraylist, classIdlist, sectionIdlist);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("OwnClassList", "NUll");
                    }
                }else{
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class SubmitAttendance extends  AsyncTask<String, String, JSONObject>{
        private JSONObject jsonObject;
        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL = new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("classId", strings[4]));
            userData.add(new BasicNameValuePair("sectionId", strings[5]));
            userData.add(new BasicNameValuePair("date", strings[6]));
            userData.add(new BasicNameValuePair("time", strings[7]));
            userData.add(new BasicNameValuePair("attendanceData", strings[8]));
            jsonObject = webServicesURL.saveStudentAttendance(userData);
            Log.d("Json to pass2", "" + jsonObject);
            return jsonObject;
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                if (jsonObject != null) {
                    isSubmit = false;
                    Toast.makeText(TakeAttendanceActivity.this,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
