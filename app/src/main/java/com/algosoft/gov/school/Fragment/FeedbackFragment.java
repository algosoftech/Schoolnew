package com.algosoft.gov.school.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.ViewFeedbackActivity;
import com.algosoft.gov.school.Adapter.TakeAttendanceAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.AttendanceOk;
import com.algosoft.gov.school.Teacher.Syllabus;
import com.algosoft.gov.school.Teacher.TakeAttendanceActivity;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.SectionDetail;
import com.algosoft.gov.school.response.StudentDetail;
import com.algosoft.gov.school.response.SubjectDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment {
    Spinner spinner_teacher,spinner_class,spinner_subject;
    private String branchCode,userId,boardId,type;
    public String studentId;
    RadioButton radio_one,radio_two,radio_three,Radio_one,
        Radio_two,Radio_three,Know_one,Know_two,Know_three,radi_one,radi_two,radi_three;
    private ArrayList<SectionDetail> sectionlist;
    private Spinner spinner_student,spinner_class1,spinner_section1,spinner_section;
    private LinearLayout studentlayout;
    private LinearLayout teacherlayout;
    private ArrayList<ClassDetail> classlist;
    private ArrayList<StudentDetail> studentDetailArraylist;
    private Button studentFeedback_submit;
    public String classId;
    public String sectionId;
    private EditText feedback_text;
    private String current_date,current_time;
    private ProgressBar progress;
    private RadioGroup radiongroup;


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feedback, container, false);
        studentDetailArraylist = new ArrayList<>();
        classlist = new ArrayList<>();
        sectionlist = new ArrayList<>();

        progress =  (ProgressBar)view.findViewById(R.id.progressbar);
        branchCode =  PreferenceUtil.getBranchCode(getContext());
        userId = PreferenceUtil.getUserId(getContext());
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(getContext());
        type = PreferenceUtil.getSelectedTypeFromServer(getContext());

        studentlayout = (LinearLayout)view.findViewById(R.id.student_layout);
        teacherlayout = (LinearLayout)view.findViewById(R.id.teacherlayout);

        if(type.equalsIgnoreCase("Teacher")){
            studentlayout.setVisibility(View.VISIBLE);
        }else if(type.equalsIgnoreCase("Parent")){
            teacherlayout.setVisibility(View.VISIBLE);
        }

        Button buttonsubmit=(Button)view.findViewById(R.id.Buttonsubmit);
        studentFeedback_submit=(Button)view.findViewById(R.id.Buttonsubmit1);

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Submitted Successfully",Toast.LENGTH_LONG).show();
            }
        });

        spinner_class=(Spinner)view.findViewById(R.id.spinner_Class);
        spinner_section=(Spinner)view.findViewById(R.id.spinner_section);
        spinner_teacher=(Spinner)view.findViewById(R.id.spinner_teacher);

        spinner_student=(Spinner)view.findViewById(R.id.spinner_student1);
        spinner_class1=(Spinner)view.findViewById(R.id.spinner_Class1);
        spinner_section1=(Spinner)view.findViewById(R.id.spinner_section1);

        feedback_text = (EditText)view.findViewById(R.id.feedback_et);

        radio_one=(RadioButton)view.findViewById(R.id.Radiovery);
        radio_two=(RadioButton)view.findViewById(R.id.RadioGood);
        radio_three=(RadioButton)view.findViewById(R.id.RadioPoor);
        Radio_one=(RadioButton)view.findViewById(R.id.Know_one);
        Radio_two=(RadioButton)view.findViewById(R.id.Know_two);
        Radio_three=(RadioButton)view.findViewById(R.id.Know_three);
        Know_one=(RadioButton)view.findViewById(R.id.Class_one);
        Know_two=(RadioButton)view.findViewById(R.id.Class_two);
        Know_three=(RadioButton)view.findViewById(R.id.Class_three);
        radi_one=(RadioButton)view.findViewById(R.id.Sharing_one);
        radi_two=(RadioButton)view.findViewById(R.id.Sharing_two);
        radi_three=(RadioButton)view.findViewById(R.id.Sharing_three);

        radio_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_one.setChecked(true);
                radio_two.setChecked(false);
                radio_three.setChecked(false);
            }
        });
        radio_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_one.setChecked(false);
                radio_two.setChecked(true);
                radio_three.setChecked(false);
            }
        });
        radio_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_one.setChecked(false);
                radio_two.setChecked(false);
                radio_three.setChecked(true);
            }
        });

        Radio_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radio_one.setChecked(true);
                Radio_two.setChecked(false);
                Radio_three.setChecked(false);

            }
        });
        Radio_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radio_one.setChecked(false);
                Radio_two.setChecked(true);
                Radio_three.setChecked(false);
            }
        });
        Radio_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radio_one.setChecked(false);
                Radio_two.setChecked(false);
                Radio_three.setChecked(false);
            }
        });
        Know_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Know_one.setChecked(true);
                Know_two.setChecked(false);
                Know_three.setChecked(false);
            }
        });
        Know_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Know_one.setChecked(false);
                Know_two.setChecked(true);
                Know_three.setChecked(false);
            }
        });
        Know_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Know_one.setChecked(false);
                Know_two.setChecked(false);
                Know_three.setChecked(true);
            }
        });
        radi_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radi_one.setChecked(true);
                radi_two.setChecked(false);
                radi_three.setChecked(false);

            }
        });radi_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radi_one.setChecked(false);
                radi_two.setChecked(true);
                radi_three.setChecked(false);
            }
        });
        radi_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radi_one.setChecked(false);
                radi_two.setChecked(false);
                radi_three.setChecked(true);
            }
        });


        if (NetworkStatus.isNetworkAvailable(getContext())) {
            progress.setVisibility(View.VISIBLE);
            new studentListClass().execute(branchCode,type,userId,boardId);
            new classListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }


        spinner_student.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentId = studentDetailArraylist.get(position).getStudentId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_class1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               classId = classlist.get(position).getClassId();
                if (NetworkStatus.isNetworkAvailable(getContext())) {
                    new SectionListClass().execute(branchCode,type,userId,boardId,classId);
                }else{
                    Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_section1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectionId = sectionlist.get(position).getSectionId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        current_date = sdf.format(c.getTime());
        current_time = sdf1.format(c.getTime());

        studentFeedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedback_text.getText().toString();
                if (NetworkStatus.isNetworkAvailable(getContext())) {
                    progress.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.VISIBLE);
                    new Submit_studentFeedback().execute(branchCode,type,userId,boardId,classId,sectionId,current_date,current_time,studentId,feedback);
                }else{
                    Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }

    public class studentListClass extends AsyncTask<String, String,JSONObject>
    {
        private String classIdlist,sectionIdlist;

        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("boardId", params[3]));
            JSONObject jsonObject=webServicesURL.getOwnStudentListData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("OwnStudentList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        classIdlist = responseClass.getResult().getClassId();
                        sectionIdlist = responseClass.getResult().getSectionId();
                        studentDetailArraylist = responseClass.getResult().getStudentDetailArrayList();

                        ArrayAdapter<StudentDetail> adapterteacher=new ArrayAdapter<StudentDetail>(getActivity(),android.R.layout.simple_spinner_item,studentDetailArraylist);
                        adapterteacher.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_teacher.setAdapter(adapterteacher);

                        ArrayAdapter<StudentDetail> adapterteacher1=new ArrayAdapter<StudentDetail>(getActivity(),android.R.layout.simple_spinner_item,studentDetailArraylist);
                        adapterteacher1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_student.setAdapter(adapterteacher1);

                    }else {
                        Log.e("OwnClassList", "NUll");
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
                        PreferenceUtil.setSectionListFromServer(getContext(),gson.toJson(sectionlist));

                        ArrayAdapter<SectionDetail> adapterSECTION = new ArrayAdapter<SectionDetail>(getContext(), android.R.layout.simple_spinner_item,android.R.id.text1, sectionlist);
                        adapterSECTION.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_section1.setAdapter(adapterSECTION);

                        ArrayAdapter<SectionDetail> adaptersection=new ArrayAdapter<SectionDetail>(getContext(),android.R.layout.simple_spinner_item,sectionlist);
                        adaptersection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_subject.setAdapter(adaptersection);


                    }else {
                        Log.e("sectionlist", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
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
                progress.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("ClassList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        classlist = responseClass.getResult().getClassDetailArrayList();
                        PreferenceUtil.setClassListFromServer(getContext(),gson.toJson(classlist));

                        ArrayAdapter<ClassDetail> adapterCLASS = new ArrayAdapter<ClassDetail>(getContext(), android.R.layout.simple_spinner_item,android.R.id.text1, classlist);
                        adapterCLASS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_class.setAdapter(adapterCLASS);

                        ArrayAdapter<ClassDetail> adapterCLASS1 = new ArrayAdapter<ClassDetail>(getContext(), android.R.layout.simple_spinner_item,android.R.id.text1, classlist);
                        adapterCLASS1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_class1.setAdapter(adapterCLASS1);

                    }else {
                        Log.e("ClassList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public class Submit_studentFeedback extends AsyncTask<String, String,JSONObject>
    {
        private String classIdlist,sectionIdlist;
        private ArrayList<StudentDetail> studentDetailArraylist;


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
            userData.add(new BasicNameValuePair("time", params[7]));
            userData.add(new BasicNameValuePair("studentId", params[8]));
            userData.add(new BasicNameValuePair("message", params[9]));
            JSONObject jsonObject=webServicesURL.submitFeedbackData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                progress.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("SubmitFeedbackResponse", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        String feedbackId = responseClass.getResult().getFeedbackId();
                        feedback_text.setText("");
                        Toast.makeText(getContext(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
