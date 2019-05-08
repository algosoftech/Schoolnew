//package com.algosoft.gov.school.Teacher;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.algosoft.gov.school.Activity.Home;
//import com.algosoft.gov.school.R;
//import com.algosoft.gov.school.response.ClassDetail;
//import com.algosoft.gov.school.response.ResponseClass;
//import com.algosoft.gov.school.response.SectionDetail;
//import com.algosoft.gov.school.services.WebServicesURL;
//import com.algosoft.gov.school.storage.PreferenceUtil;
//import com.algosoft.gov.school.utility.NetworkStatus;
//import com.google.gson.Gson;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Attendance extends Activity implements AdapterView.OnItemSelectedListener {
//
//    private Spinner Section_spinner,Class_spinner;
//    private String branchCode,userId,boardId;
//    private String type;
//    private ArrayList<ClassDetail> classlist;
//    private ArrayList<SectionDetail> sectionlist;
//    private String classId;
//    public String sectionId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_attendance);
//        classlist =new ArrayList<>();
//        sectionlist = new ArrayList<>();
//        branchCode =  PreferenceUtil.getBranchCode(Attendance.this);
//        userId = PreferenceUtil.getUserId(Attendance.this);
//        boardId = PreferenceUtil.getDefaultBoardIdFromServer(Attendance.this);
//        type = PreferenceUtil.getSelectedTypeFromServer(Attendance.this);
//
//        ImageView imageView=(ImageView)findViewById(R.id.Image_AttendanceBack);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
//
//        Section_spinner=(Spinner)findViewById(R.id.Section_spinner);
//
//
//        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
//            new classListClass().execute(branchCode,type,userId,boardId);
//        }else{
//            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
//
//        }
//
//        Class_spinner=(Spinner)findViewById(R.id.Class_spinner);
//        Class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(classlist.size()>0) {
//                    String class_selected = parent.getSelectedItem() + "";
//                    String name = classlist.get(position).getClassName();
//                    classId = classlist.get(position).getClassId();
////            Toast.makeText(this, ""+name+idd, Toast.LENGTH_SHORT).show();
//
//                    if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
//                        new SectionListClass().execute(branchCode,type,userId,boardId,classId);
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        Section_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(sectionlist.size()>0) {
//                    String class_selected = parent.getSelectedItem() + "";
//                    String name = sectionlist.get(position).getSectionName();
//                    sectionId = sectionlist.get(position).getSectionId();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        Button button=(Button)findViewById(R.id.Button_okAttendance);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String st=Section_spinner.getSelectedItem().toString();
//                String ct=Class_spinner.getSelectedItem().toString();
//                if((!st.equals("Select Section"))||(!ct.equals("Select Class"))){
////                    Toast.makeText(Attendance.this,"select Section",Toast.LENGTH_LONG).show();
//                    Intent i=new Intent(Attendance.this,AttendanceOk.class);
//                    i.putExtra("Classid",classId);
//                    i.putExtra("Sectionid",sectionId);
//                    startActivity(i);
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
////    @Override
////    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////
////    }
//
//    public class classListClass extends AsyncTask<String, String,JSONObject>
//    {
//
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            WebServicesURL webServicesURL=new WebServicesURL();
//            List<NameValuePair> userData = new ArrayList<NameValuePair>();
//            userData.add(new BasicNameValuePair("branchCode", params[0]));
//            userData.add(new BasicNameValuePair("userType", params[1]));
//            userData.add(new BasicNameValuePair("userId", params[2]));
//            userData.add(new BasicNameValuePair("boardId", params[3]));
//            JSONObject jsonObject=webServicesURL.getClassData(userData);
//            return jsonObject;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            super.onPostExecute(jsonObject);
//            try
//            {
////                progressBar.setVisibility(View.INVISIBLE);
//                if (jsonObject!=null) {
//                    Log.e("ClassList", jsonObject.toString());
//                    Gson gson = new Gson();
//                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
//                    if (responseClass.getSuccess() == 1) {
//                        classlist = responseClass.getResult().getClassDetailArrayList();
//                        Toast.makeText(Attendance.this, "ClassList"+classlist, Toast.LENGTH_SHORT).show();
//                        PreferenceUtil.setClassListFromServer(Attendance.this,gson.toJson(classlist));
//                        ArrayAdapter<ClassDetail> adapterClass= new ArrayAdapter<ClassDetail>(Attendance.this, android.R.layout.simple_spinner_item,classlist);
//                        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        Class_spinner.setAdapter(adapterClass);
//                    }else {
//                        Log.e("ClassList", "NUll");
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    public class SectionListClass extends AsyncTask<String, String,JSONObject>
//    {
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            WebServicesURL webServicesURL=new WebServicesURL();
//            List<NameValuePair> userData = new ArrayList<NameValuePair>();
//            userData.add(new BasicNameValuePair("branchCode", params[0]));
//            userData.add(new BasicNameValuePair("userType", params[1]));
//            userData.add(new BasicNameValuePair("userId", params[2]));
//            userData.add(new BasicNameValuePair("boardId", params[3]));
//            userData.add(new BasicNameValuePair("classId", params[4]));
//            JSONObject jsonObject=webServicesURL.getSectionData(userData);
//            return jsonObject;
//        }
//
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            super.onPostExecute(jsonObject);
//            try
//            {
////                progressBar.setVisibility(View.INVISIBLE);
//                if (jsonObject!=null) {
//                    Log.e("SectionList", jsonObject.toString());
//                    Gson gson = new Gson();
//                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
//                    if (responseClass.getSuccess() == 1) {
//                        sectionlist = responseClass.getResult().getSectionDetailArrayList();
////                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
//                        PreferenceUtil.setSectionListFromServer(Attendance.this,gson.toJson(sectionlist));
//                        ArrayAdapter<SectionDetail> adapterSection= new ArrayAdapter<SectionDetail>(Attendance.this, android.R.layout.simple_spinner_item,sectionlist);
//                        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        Section_spinner.setAdapter(adapterSection);
//                    }else {
//                        Log.e("sectionlist", "NUll");
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//}
