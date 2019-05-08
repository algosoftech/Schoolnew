package com.algosoft.gov.school.Teacher;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.algosoft.gov.school.Fragment.LeaveFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.SectionDetail;
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

public class ViewAttendance extends AppCompatActivity {
    private Spinner Section_spinner,Class_spinner;
    private String branchCode,userId,boardId,type;
    public String sectionId;
    public String classId;
    private ArrayList<ClassDetail> classlist;
    private ArrayList<SectionDetail> sectionlist;
    private EditText fromdate_editText;
    private int month;
    private int  dayOfMonth;
    private int year;
    DatePickerDialog datePickerDialog;
    ArrayList<String> Class_type = new ArrayList<>();
    ArrayList<String> Section_type = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        branchCode =  PreferenceUtil.getBranchCode(ViewAttendance.this);
        userId = PreferenceUtil.getUserId(ViewAttendance.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(ViewAttendance.this);
        type = PreferenceUtil.getSelectedTypeFromServer(ViewAttendance.this);

        Class_type.add(0,"Select Class");
        Section_type.add(0,"Select Section");
        classlist = new ArrayList<>();
        sectionlist = new ArrayList<>();
        fromdate_editText=(EditText)findViewById(R.id.Edit_SelectFrom1);
        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new classListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

        Button button=(Button)findViewById(R.id.Button_ViewAttendance);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sc=Class_spinner.getSelectedItem().toString();
                String ss=Section_spinner.getSelectedItem().toString();
                if((!sc.equals("Select Class"))&&(!ss.equals("Select Section"))){
                    Intent i=new Intent(ViewAttendance.this,ViewAttendance2.class);
                    i.putExtra("ClassId",classId);
                    i.putExtra("SectionId",sectionId);
                    i.putExtra("Date",fromdate_editText.getText().toString());
                    startActivity(i);
                }
            }
        });

        Section_spinner=(Spinner)findViewById(R.id.Section_spinner);
        Section_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String section_selected = parent.getSelectedItem() + "";
                    String name = sectionlist.get(position-1).getSectionName();
                    sectionId = sectionlist.get(position-1).getSectionId();
                  //  Toast.makeText(ViewAttendance.this, ""+sectionId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Class_spinner=(Spinner)findViewById(R.id.Class_spinner);
        Class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    String class_selected = parent.getSelectedItem() + "";
                    String name = classlist.get(position-1).getClassName();
                    classId = classlist.get(position-1).getClassId();
                  //  Toast.makeText(ViewAttendance.this, ""+classId, Toast.LENGTH_SHORT).show();
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

        ImageView imageView=(ImageView)findViewById(R.id.Image_ViewAttendance);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fromdate_editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(ViewAttendance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
//                                fromdate_editText.setText(day+"-"+(month+1)+"-"+year);
                                fromdate_editText.setText(year+"-"+(month+1)+"-"+day);

                            }
                        },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });

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
                        Class_type.clear();
                        Class_type.add(0,"Select Class");
                        for (int i=0;i<classlist.size();i++){
                            String name=classlist.get(i).getClassName();
                            Class_type.add(name);
                        }
//                        Toast.makeText(Syllabus.this, "ClassList"+classlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setClassListFromServer(ViewAttendance.this,gson.toJson(classlist));
                        ArrayAdapter<String> adapterCLASS = new ArrayAdapter<String>(ViewAttendance.this, android.R.layout.simple_spinner_item,android.R.id.text1, Class_type);
                        adapterCLASS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Class_spinner.setAdapter(adapterCLASS);
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
                        Section_type.clear();
                        Section_type.add(0,"Select Section");
                        for(int i=0;i<sectionlist.size();i++){
                            String name=sectionlist.get(i).getSectionName();
                            Section_type.add(name);
                        }
//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSectionListFromServer(ViewAttendance.this,gson.toJson(sectionlist));
                        ArrayAdapter<String> adapterSECTION = new ArrayAdapter<String>(ViewAttendance.this, android.R.layout.simple_spinner_item,android.R.id.text1, Section_type);
                        adapterSECTION.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Section_spinner.setAdapter(adapterSECTION);

//                        ArrayAdapter<String> adapterSection= new ArrayAdapter<String>(ViewAttendance.this,
//                                android.R.layout.simple_spinner_item,Section);
//                        adapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        Section_spinner.setAdapter(adapterSection);

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
