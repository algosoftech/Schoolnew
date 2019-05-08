package com.algosoft.gov.school.Teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.SectionDetail;
import com.algosoft.gov.school.response.SubjectDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Syllabus extends Activity implements AdapterView.OnItemSelectedListener {

//    private static final String SUBJECT []={"Select Subject","Hindi","English","Math","Science","Social Science","Drawing"};
    private static final String SECTION[]={"Select Section","A","B","C"};
    private static final String CLASS[]={"Select Class","1","2","3","4","5","6","7","8","9","10"};
    Spinner spinner_class,spinner_subject,spinner_section;
    private String branchCode,userId,boardId,type;
    private ArrayList<ClassDetail> classlist;
    public String classId;
    private Button button;
    private ArrayList<SectionDetail> sectionlist;
    private ArrayList<SubjectDetail> subjectlist;
    public String sectionId;
    private String subjectId;
    private ArrayList<String>ClassList=new ArrayList<>();
    private ArrayList<String>SectionList=new ArrayList<>();
    private ArrayList<String>SubjectList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        branchCode =  PreferenceUtil.getBranchCode(Syllabus.this);
        userId = PreferenceUtil.getUserId(Syllabus.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(Syllabus.this);
        type = PreferenceUtil.getSelectedTypeFromServer(Syllabus.this);
        classlist = new ArrayList<>();
        sectionlist = new ArrayList<>();
        subjectlist = new ArrayList<>();



        ClassList.add(0,"Select Class");
        SectionList.add(0,"Select Section");
        SubjectList.add(0,"Select Subject");
        button=(Button)findViewById(R.id.Button_Syllabus);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          button.setEnabled(false);

//                                          new Handler().postDelayed(new Runnable() {
////                                              @Override
////                                              public void run() {
////                                                  button.setEnabled(true);
////
////                                              }
////                                          },2000);

                String ss=spinner_section.getSelectedItem().toString();
                String sc=spinner_class.getSelectedItem().toString();
                String SS=spinner_subject.getSelectedItem().toString();
                if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    new SyllabusListClass().execute(branchCode,type,userId,boardId,classId,sectionId,subjectId);
                }else{
                    Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                }
//                if((!ss.equals("Select Section"))&&(!sc.equals("Select Class"))&&(!SS.equals("Select Subject"))){
//
////
//                }

            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.Image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new classListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

         spinner_subject = (Spinner) findViewById(R.id.SUBJECT_spinner);
         spinner_section = (Spinner) findViewById(R.id.SECTION_spinner);
         spinner_class = (Spinner) findViewById(R.id.CLASS_spinner);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Syllabus.this, android.R.layout.simple_spinner_item, SUBJECT);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_subject.setAdapter(adapter);




        spinner_section.setOnItemSelectedListener(this);
        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String class_selected = parent.getSelectedItem() + "";
                    String name = classlist.get(position-1).getClassName();
                    classId = classlist.get(position-1).getClassId();
                    if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        new SectionListClass().execute(branchCode,type,userId,boardId,classId);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String section_selected = parent.getSelectedItem() + "";
                    String name = sectionlist.get(position-1).getSectionName();
                    sectionId = sectionlist.get(position-1).getSectionId();
                    if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        new SubjectListClass().execute(branchCode,type,userId,boardId,classId,sectionId);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String section_selected = parent.getSelectedItem() + "";
                    String name = subjectlist.get(position-1).getSubjectName();
                    subjectId = subjectlist.get(position-1).getSubjectId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class classListClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("boardId", params[3]));
            JSONObject jsonObject=webServicesURL.getClassData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("ClassList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        classlist = responseClass.getResult().getClassDetailArrayList();
                        ClassList.clear();
                        ClassList.add(0,"Select Class");
                        for (int i=0;i<classlist.size();i++){
                            String name=classlist.get(i).getClassName();
                            ClassList.add(name);

                        }
//                        Toast.makeText(Syllabus.this, "ClassList"+classlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setClassListFromServer(Syllabus.this,gson.toJson(classlist));
                        ArrayAdapter<String> adapterCLASS = new ArrayAdapter<String>(Syllabus.this, android.R.layout.simple_spinner_item, ClassList);
                        adapterCLASS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_class.setAdapter(adapterCLASS);

                    }else {
                        Log.e("ClassList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public class SectionListClass extends AsyncTask<String, String,JSONObject>
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
            JSONObject jsonObject=webServicesURL.getSectionData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("SectionList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        sectionlist = responseClass.getResult().getSectionDetailArrayList();
                        SectionList.clear();
                        SectionList.add(0,"Select Section");
                        for (int i=0;i<sectionlist.size();i++){
                          String section=sectionlist.get(i).getSectionName();
                          SectionList.add(section);
                      }


//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSectionListFromServer(Syllabus.this,gson.toJson(sectionlist));
                        ArrayAdapter<String> adapterSECTION = new ArrayAdapter<String>(Syllabus.this, android.R.layout.simple_spinner_item, SectionList);
                        adapterSECTION.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_section.setAdapter(adapterSECTION);

                    }else {
                        Log.e("sectionlist", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class SubjectListClass extends AsyncTask<String, String,JSONObject>
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
            JSONObject jsonObject=webServicesURL.getSubjectData(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("SubjectList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        subjectlist = responseClass.getResult().getSubjectDetailArrayList();
                        SubjectList.clear();
                        SubjectList.add(0,"Select Subject");
                        for (int i=0;i<subjectlist.size();i++){
                            String subject=subjectlist.get(i).getSubjectName();
                            SubjectList.add(subject);
                        }

//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSubjectListFromServer(Syllabus.this,gson.toJson(subjectlist));
                        ArrayAdapter<String> adapterSection= new ArrayAdapter<String>(Syllabus.this, android.R.layout.simple_spinner_item,SubjectList);
                        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_subject.setAdapter(adapterSection);

                    }else {
                        Log.e("sectionlist", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public class SyllabusListClass extends AsyncTask<String, String,JSONObject>
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
            userData.add(new BasicNameValuePair("subjectId", params[6]));
            JSONObject jsonObject=webServicesURL.getSyllabusData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("SyllabusList", jsonObject.toString());
                    button.setEnabled(true);
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONObject syllabusdata = result.getJSONObject("syllabusData");
                    String syllabus = syllabusdata.getString("syllabus");

                    Intent i=new Intent(Syllabus.this,DetailSyllabus.class);
                    i.putExtra("Syllabus",syllabus);
                    startActivity(i);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
