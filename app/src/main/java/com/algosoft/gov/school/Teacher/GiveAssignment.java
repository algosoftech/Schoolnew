package com.algosoft.gov.school.Teacher;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.SectionDetail;
import com.algosoft.gov.school.response.SubjectDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GiveAssignment extends AppCompatActivity  {
    Spinner spinner_subject,spinner_section,spinner_class;
    private int month, dayOfMonth, year;
    DatePickerDialog datePickerDialog,mdatePickerDialog;
    private ArrayList<ClassDetail> classlist;
    private ArrayList<SectionDetail> sectionlist;
    private ArrayList<SubjectDetail> subjectlist;
    public String sectionId, subjectId, classId, branchCode, userId, boardId,type,imageString;
    private TextView editFrom, editTo, imageText;
    private ImageView imgCapture, btnBack;
    private EditText content_et,tile_et;
    private Button btnAddAssign;
    ScrollView scrollView;
    final int REQ=1;
    final int CAMERA_REQUEST=2;
    Bitmap photo;
    Boolean isImage= false;
    String imageName;
    File pictureFile;
    ArrayList<String> Class_type = new ArrayList<>();
    ArrayList<String> Section_type = new ArrayList<>();
    ArrayList<String> Subject_type = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_assignment);

        ImageButton btnBack=(ImageButton) findViewById(R.id.back_giveAssign);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        scrollView= (ScrollView) findViewById(R.id.scrollview_giveAssign);
        spinner_subject = (Spinner) findViewById(R.id.sub_spinner);
        spinner_section = (Spinner) findViewById(R.id.sec_spinner);
        spinner_class = (Spinner) findViewById(R.id.class_spinner);
        content_et=(EditText)findViewById(R.id.assign_desc);
        editFrom=(TextView)findViewById(R.id.from_date);
        editTo=(TextView) findViewById(R.id.to_date);
        tile_et=(EditText)findViewById(R.id.assign_title);
        imgCapture=(ImageView) findViewById(R.id.img_capture_assign);
        imageText= (TextView)findViewById(R.id.image_text);
        btnAddAssign=(Button)findViewById(R.id.btn_add_assign);




        branchCode =  PreferenceUtil.getBranchCode(GiveAssignment.this);
        userId = PreferenceUtil.getUserId(GiveAssignment.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(GiveAssignment.this);
        type = PreferenceUtil.getSelectedTypeFromServer(GiveAssignment.this);

        classlist = new ArrayList<>();
        sectionlist = new ArrayList<>();
        subjectlist = new ArrayList<>();

        Class_type.add(0,"Select Class");
        Section_type.add(0,"Select Section");
        Subject_type.add(0,"Select Subject");
        editFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(GiveAssignment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int Nmonth= month+1;
                                editFrom.setText(year + "-" + (Nmonth < 10 ? ("0" + Nmonth) : (Nmonth)) + "-" + (day < 10 ? ("0" + day) : (day)));
                            }
                        },year,month,dayOfMonth);
                datePickerDialog.show();
            }
        });

        editTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                mdatePickerDialog=new DatePickerDialog(GiveAssignment.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                int Nmonth= month+1;
                                editTo.setText(year + "-" + (Nmonth < 10 ? ("0" + Nmonth) : (Nmonth)) + "-" + (day < 10 ? ("0" + day) : (day)));
                            }
                        },year,month,dayOfMonth);
                mdatePickerDialog.show();
            }
        });
        if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
            new GiveAssignment.classListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

        btnAddAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
        imageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });
        imgCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
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

        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position >0) {
                    String class_selected = parent.getSelectedItem() + "";
                    String name = Class_type.get(position-1);
                    classId = classlist.get(position-1).getClassId();
                    Log.e("ClassSIze",""+classId);
                    if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        new GiveAssignment.SectionListClass().execute(branchCode,type,userId,boardId,classId);
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
                    try {
                        String section_selected = parent.getSelectedItem() + "";
                        String name = sectionlist.get(position-1).getSectionName();
                        sectionId = sectionlist.get(position-1).getSectionId();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                        new GiveAssignment.SubjectListClass().execute(branchCode,type,userId,boardId,classId,sectionId);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void validateFields() {
        String title = tile_et.getText().toString();
        String fromDate = editFrom.getText().toString();
        String toDate = editTo.getText().toString();
        String content = content_et.getText().toString();
        if(classlist.size()>0 && subjectlist.size()>0 && sectionlist.size()>0){
            if(classId.isEmpty() || sectionId.isEmpty() || subjectId.isEmpty()){
                Toast.makeText(this, "Please select class, section and subject!", Toast.LENGTH_SHORT).show();
            }else if(title.isEmpty()){
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
            }else if(fromDate.isEmpty() || toDate.isEmpty()){
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
            }else if(content.isEmpty() && photo == null){
                Toast.makeText(this, "Please provide description or take image of Assignment!", Toast.LENGTH_SHORT).show();
            }else {
                if (NetworkStatus.isNetworkAvailable(getApplicationContext())) {
                    Log.e("DataToSendAssignment", "" + "\n BrCode:" + branchCode + "\n type:" + type + "\n userId:" + userId
                            + "\n boardId: " + boardId + "\n classId: " + classId + "sectionId: " + sectionId + "\n subjectId:" + subjectId +
                            "\n title:" + title + "\n from:" + fromDate + "\n to:" + toDate + "\n content:" + content);
                    new GiveAssignment.SendAssignmentClass().execute(branchCode, type, userId, boardId, classId, sectionId, subjectId, title, fromDate, toDate, content);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
        }


    }

    private void checkCameraPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openCamera();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},REQ);
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                isImage= true;
                photo = (Bitmap) data.getExtras().get("data");
                saveImage(photo);
                imgCapture.setImageBitmap(photo);
                imageText.setText(imageName);
            }
        }
    }

    private void saveImage(Bitmap finalBitmap) {
        pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("File","Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("error in file", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("error in file", "Error accessing file: " + e.getMessage());
        }

    }
    private  File getOutputMediaFile(){
        Log.e("Dir",""+ Environment.getExternalStorageDirectory());
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/DCIM/Camera/HomeworkFiles");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        imageName = "Homework_"+ Calendar.getInstance().getTimeInMillis() +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + imageName);
        String str = mediaFile.toString();
        Log.e("File to string",""+str+"\n media file:"+mediaFile);
        return mediaFile;
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
                        Class_type.add(0,"Select class");
                        for (int i = 0; i < classlist.size(); i++) {
                            String name = classlist.get(i).getClassName();
                            Class_type.add(name);
                        }
//                        Toast.makeText(Syllabus.this, "ClassList"+classlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setClassListFromServer(GiveAssignment.this,gson.toJson(classlist));
                        ArrayAdapter<String> adapterCLASS = new ArrayAdapter<String>(GiveAssignment.this, android.R.layout.simple_spinner_item,android.R.id.text1, Class_type);
                        //ArrayAdapter<ClassDetail> adapterCLASS = new ArrayAdapter<ClassDetail>(GiveAssignment.this, android.R.layout.simple_spinner_item,android.R.id.text1, Class_type);

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
                        Section_type.clear();
                        Section_type.add(0,"Select section");
                        for (int i = 0; i < sectionlist.size(); i++) {
                            String name = sectionlist.get(i).getSectionName();
                            Section_type.add(name);
                        }
//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSectionListFromServer(GiveAssignment.this,gson.toJson(sectionlist));
                        ArrayAdapter<String> adapterSECTION = new ArrayAdapter<String>(GiveAssignment.this, android.R.layout.simple_spinner_item, Section_type);
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
                        Subject_type.clear();
                        Subject_type.add(0,"Select subject");
                        for (int i = 0; i < subjectlist.size(); i++) {
                            String name = subjectlist.get(i).getSubjectName();
                            Subject_type.add(name);
                        }

//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setSubjectListFromServer(GiveAssignment.this,gson.toJson(subjectlist));
                        ArrayAdapter<String> adapterSection= new ArrayAdapter<String>(GiveAssignment.this, android.R.layout.simple_spinner_item,Subject_type);
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


    public class SendAssignmentClass extends AsyncTask<String, String,String>
    {

        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg; charset=utf-8");
        private String result;
        private String url ="http://sms.algosoftech.in/api/teacher/setAssignment";
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog = new ProgressDialog(getApplicationContext());
//            dialog.setMessage("Updating, please wait.");
//            dialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            WebServicesURL webServicesURL = new WebServicesURL();
            try {
                RequestBody requestBody;
                if (photo == null) {
                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("branchCode", params[0])
                            .addFormDataPart("userType", params[1])
                            .addFormDataPart("userId", params[2])
                            .addFormDataPart("boardId", params[3])
                            .addFormDataPart("classId", params[4])
                            .addFormDataPart("sectionId", params[5])
                            .addFormDataPart("subjectId", params[6])
                            .addFormDataPart("title", params[7])
                            .addFormDataPart("fromDate", params[8])
                            .addFormDataPart("toDate", params[9])
                            .addFormDataPart("content", params[10])
                            .build();
                } else {
                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("assignmentFile", pictureFile.getName(), RequestBody.create(MEDIA_TYPE_PNG, pictureFile))
                            .addFormDataPart("branchCode", params[0])
                            .addFormDataPart("userType", params[1])
                            .addFormDataPart("userId", params[2])
                            .addFormDataPart("boardId", params[3])
                            .addFormDataPart("classId", params[4])
                            .addFormDataPart("sectionId", params[5])
                            .addFormDataPart("subjectId", params[6])
                            .addFormDataPart("title", params[7])
                            .addFormDataPart("fromDate", params[8])
                            .addFormDataPart("toDate", params[9])
                            .addFormDataPart("content", params[10])
                            .build();
                }
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Apikey", KeyGenerationClass.getEncryptedKey())
                        .addHeader("Apidate",KeyGenerationClass.getDate())
                        .post(requestBody)
                        .build();

                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build();


                Response response = client.newCall(request).execute();
                result = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("AssignmentResponse", ""+jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (!jsonObject.isEmpty()) {
                    JSONObject jobject = new JSONObject(jsonObject);
                    Log.e("AssignmentResponse", ""+jobject);
                    if (jobject.getInt("success")==1) {
                        Toast.makeText(GiveAssignment.this, ""+jobject.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(GiveAssignment.this, ""+jobject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if ( v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.") ) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if ( x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom() )
                hideKeyboard(GiveAssignment.this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if ( activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null ) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
