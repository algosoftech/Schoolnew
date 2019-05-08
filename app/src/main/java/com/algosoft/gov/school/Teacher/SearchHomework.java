package com.algosoft.gov.school.Teacher;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Fragment.LeaveFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.HomeWorkList;
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
import java.util.Calendar;
import java.util.List;

public class SearchHomework extends AppCompatActivity {
    TextView dateFrom,dateTo;
    EditText etTitle;
    Button btnSearch;
    private Calendar mycalendar;
    private int month;
    private int  dayOfMonth;
    private int year;
    DatePickerDialog datePickerDialog;
    private String branchCode,userId,boardId,type;
    private ArrayList<ClassDetail> classlist;
    public String classId;
    private ArrayList<SectionDetail> sectionlist;
    private ArrayList<SubjectDetail> subjectlist;
    public static ArrayList<HomeWorkList> homeWorkList= new ArrayList<HomeWorkList>();
    public String sectionId;
    public String subjectId;
    DatePickerDialog mdatePickerDialog;
    private Spinner spinner_subject,spinner_section,spinner_class;
    RelativeLayout mainLayout;
    LinearLayout progessLayout;
    ArrayList<String> Class_type = new ArrayList<>();
    ArrayList<String> Section_type = new ArrayList<>();
    ArrayList<String> Subject_type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_homework);

        branchCode =  PreferenceUtil.getBranchCode(SearchHomework.this);
        userId = PreferenceUtil.getUserId(SearchHomework.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(SearchHomework.this);
        type = PreferenceUtil.getSelectedTypeFromServer(SearchHomework.this);
        classlist = new ArrayList<>();
        sectionlist = new ArrayList<>();
        subjectlist = new ArrayList<>();

        Class_type.add(0,"Select Class");
        Section_type.add(0,"Select Section");
        Subject_type.add(0,"Select Subject");


        mainLayout= (RelativeLayout) findViewById(R.id.searchHwForm_container);
        progessLayout= (LinearLayout) findViewById(R.id.progess_layout);
        ImageView imageView=(ImageView)findViewById(R.id.Image_searchhome);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSearch= (Button) findViewById(R.id.btn_hw_search);
        etTitle= (EditText) findViewById(R.id.Edit_title);
        dateFrom=(TextView) findViewById(R.id.Edit_From);
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(SearchHomework.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int Nmonth= month+1;
                                dateFrom.setText(year + "-" + (Nmonth < 10 ? ("0" + Nmonth) : (Nmonth)) + "-" + (day < 10 ? ("0" + day) : (day)));

                            }
                        },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });

        dateTo=(TextView) findViewById(R.id.Edit_To);
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                mdatePickerDialog=new DatePickerDialog(SearchHomework.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int Nmonth= month;
                                dateTo.setText(year + "-" + (Nmonth < 10 ? ("0" + Nmonth) : (Nmonth)) + "-" + (day < 10 ? ("0" + day) : (day)));
                            }
                        },year,month,dayOfMonth);
                mdatePickerDialog.show();
            }
        });
         spinner_subject = (Spinner) findViewById(R.id.SUBJECT_spinner);
         spinner_subject.setPrompt("Select Subject");
         spinner_section = (Spinner) findViewById(R.id.SECTION_spinner);
         spinner_section.setPrompt("Select Section");
         spinner_class = (Spinner) findViewById(R.id.CLASS_spinner);
         spinner_class.setPrompt("Select Class");

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item, SUBJECT);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_subject.setAdapter(adapter);
//
//        ArrayAdapter<String> adapterSECTION = new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item,android.R.id.text1, SECTION);
//        adapterSECTION.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_section.setAdapter(adapterSECTION);
//
//        ArrayAdapter<String> adapterCLASS = new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item,android.R.id.text1, CLASS);
//        adapterCLASS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_class.setAdapter(adapterCLASS);

        spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String subject_selected = parent.getSelectedItem() + "";
                    String subject = subjectlist.get(position-1).getSubjectName();
                    subjectId = subjectlist.get(position-1).getSubjectId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new classListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
        }
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    private void validateFields() {
        String title= etTitle.getText().toString().trim();
        if(classId.isEmpty()){
            Toast.makeText(this, "Please select class!", Toast.LENGTH_SHORT).show();
        }else if(sectionId.isEmpty()){
            Toast.makeText(this, "Please select section!", Toast.LENGTH_SHORT).show();
        }else if(subjectId.isEmpty()){
            Toast.makeText(this, "Please select subject!", Toast.LENGTH_SHORT).show();
        }else if(!classId.isEmpty() && !sectionId.isEmpty() && !subjectId.isEmpty() || !dateFrom.getText().toString().isEmpty()|| !dateTo.getText().toString().isEmpty() || !etTitle.getText().toString().isEmpty()){
            new GetHomework().execute(branchCode,type,userId,boardId,classId,sectionId,subjectId, title);
        }else{
            Toast.makeText(this, "Please select class, section and subject!", Toast.LENGTH_SHORT).show();
        }
//        new GetHomework().execute(branchCode,type,userId,boardId,classId,sectionId,subjectId);
    }
    public class GetHomework extends AsyncTask<String, String,JSONObject>{
        @Override
        protected void onPreExecute() {
            btnSearch.setEnabled(false);
        }
        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("classId", strings[4]));
            userData.add(new BasicNameValuePair("sectionId", strings[5]));
            userData.add(new BasicNameValuePair("subjectId", strings[6]));
            userData.add(new BasicNameValuePair("title", strings[7]));
            JSONObject jsonObject=webServicesURL.getHomworkData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if(jsonObject != null) {
                Gson gson = new Gson();
                ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                if(responseClass.getSuccess()==1) {
                    btnSearch.setEnabled(true);
                    homeWorkList = responseClass.getResult().getHomeWorkList();
                    Log.e("GetHomeworkList", "" + responseClass.getResult().getHomeWorkList());
                    PreferenceUtil.setHomeworkListFromServer(SearchHomework.this,gson.toJson(homeWorkList));
                    Intent intent= new Intent(SearchHomework.this, DisplayHomework.class);
                    intent.putExtra("title","Homework List");
                    startActivity(intent);
                }
            }

        }
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
                        if(classlist.size()>0 && sectionlist.size()>0 && sectionlist.size()>0){
                            btnSearch.setEnabled(true);
                        }
                        if(progessLayout.isEnabled()){
                            progessLayout.setVisibility(View.GONE);
                            mainLayout.setVisibility(View.VISIBLE);
                        }
                        classlist = responseClass.getResult().getClassDetailArrayList();
                        Class_type.clear();
                        Class_type.add(0,"Select Class");
                        for (int i=0;i<classlist.size();i++){
                            String name=classlist.get(i).getClassName();
                            Class_type.add(name);
                        }
                        Log.e("GsonList",""+responseClass.getResult().getClassDetailArrayList()+"\n classlist size"+classlist.size());
//                        Toast.makeText(Syllabus.this, "ClassList"+classlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setClassListFromServer(SearchHomework.this,gson.toJson(classlist));
                        ArrayAdapter<String> adapterCLASS = new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item,android.R.id.text1, Class_type);
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
                        if(classlist.size()>0 && sectionlist.size()>0 && sectionlist.size()>0){
                            btnSearch.setEnabled(true);
                        }
                        sectionlist = responseClass.getResult().getSectionDetailArrayList();
                        Section_type.clear();
                        Section_type.add(0,"Select Section");
                        for(int i=0;i<sectionlist.size();i++){
                            String name=sectionlist.get(i).getSectionName();
                            Section_type.add(name);
                        }
//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSectionListFromServer(SearchHomework.this,gson.toJson(sectionlist));
                        ArrayAdapter<String> adapterSECTION = new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item,android.R.id.text1,Section_type);
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

                    if(classlist.size()>0 && sectionlist.size()>0 && sectionlist.size()>0){
                        btnSearch.setEnabled(true);
                    }
                    Log.e("SubjectList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        subjectlist = responseClass.getResult().getSubjectDetailArrayList();
                        Subject_type.clear();
                        Subject_type.add(0,"Select Subject");
                        for(int i=0;i<subjectlist.size();i++){
                            String name=subjectlist.get(i).getSubjectName();
                            Subject_type.add(name);
                        }
//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSubjectListFromServer(SearchHomework.this,gson.toJson(subjectlist));
                        ArrayAdapter<String> adapterSection= new ArrayAdapter<String>(SearchHomework.this, android.R.layout.simple_spinner_item,Subject_type);
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

}
