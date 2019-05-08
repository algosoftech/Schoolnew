package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.SendMessageToParentAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ClassDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.SectionDetail;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComposeMessage extends AppCompatActivity implements SendMessageToParentAdapter.Selectedinterface{
    private String branchCode,userId,boardId,type,classId,date,time,studentData,message;
    private ArrayList<ClassDetail> classlist;
    private ArrayList<SectionDetail>sectionlist;
    TextView textView;
    EditText editMessage;
    Spinner Class,Section;
    RecyclerView recyclerView;
    SendMessageToParentAdapter adapter;
    private String sectionId;
    private ArrayList<StudentDetail> studentlist;
    Map<String, List<Boolean>> map = new HashMap<String, List<Boolean>>();
    ImageButton sendMessage;
    HashMap<String,Boolean>checkedData=new HashMap<String, Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);



        branchCode=PreferenceUtil.getBranchCode(ComposeMessage.this);
        userId = PreferenceUtil.getUserId(ComposeMessage.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(ComposeMessage.this);
        type = PreferenceUtil.getSelectedTypeFromServer(ComposeMessage.this);
        date= KeyGenerationClass.getDate();
        time=KeyGenerationClass.getTime();

        editMessage=(EditText)findViewById(R.id.commenttext);

        Log.e("EditMessage",""+message);
        sendMessage=(ImageButton)findViewById(R.id.send);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkStatus.isNetworkAvailable(ComposeMessage.this)){
                    message=editMessage.getText().toString();
                    new SendMessage().execute(branchCode,type,userId,boardId,classId,sectionId,date,time,studentData,message);

                }else {
                    Toast.makeText(ComposeMessage.this,"Please Check your connection",Toast.LENGTH_LONG).show();

                }
            }
        });





        textView=(TextView) findViewById(R.id.TextView_selectAll);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetworkStatus.isNetworkAvailable(ComposeMessage.this)){
                    new AllStudentList().execute(branchCode,type,userId,boardId);
                }else {
                    Toast.makeText(ComposeMessage.this,"Please Check your connection",Toast.LENGTH_LONG).show();

                }
            }
        });



        recyclerView=(RecyclerView)findViewById(R.id.comment_rec_view);
        classlist = new ArrayList<>();

        Class=(Spinner)findViewById(R.id.SpinnerClass);
        Class.setPrompt("Select Class");

        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(classlist.size()>0){
                    String class_selected = parent.getSelectedItem() + "";
                    String name = classlist.get(position).getClassName();
                    classId = classlist.get(position).getClassId();

                    if(NetworkStatus.isNetworkAvailable(ComposeMessage.this)){
                        new getSectionList().execute(branchCode,type,userId,boardId,classId);
                    }else {
                        Toast.makeText(ComposeMessage.this,"Please Check your connection",Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Section=(Spinner)findViewById(R.id.SpinnerSection);
        Section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sectionlist.size()>0){
                    sectionId = sectionlist.get(position).getSectionId();

                    new StudentList().execute(branchCode,type,userId,boardId,classId,sectionId);
                }
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageButton btnBack= (ImageButton) findViewById(R.id.back_msg_comp);


        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        if(NetworkStatus.isNetworkAvailable(ComposeMessage.this)){
            new GetClassList().execute(branchCode,type,userId,boardId);


        }else {
            Toast.makeText(ComposeMessage.this,"Please Check your connection",Toast.LENGTH_LONG).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void addselected(String id, boolean state) {
        JSONArray jsonArray=new JSONArray();
        checkedData.put(id,state);
        List<String> valId = new ArrayList<String>(checkedData.keySet());
        for (int i=0;i<valId.size();i++){
            if (checkedData.get(valId.get(i))){
                jsonArray.put(valId.get(i));
                Log.e("ArrayData",""+jsonArray);
                studentData=jsonArray.toString();
            }
        }





        Log.e("StudentIdActivity",""+id+ " checkstate "+state);
        List<Boolean> valSetOne = new ArrayList<>();
        valSetOne.add(state);

        List<Boolean> valSetTwo = new ArrayList<>();
        valSetTwo.add(state);
        map.put(id,valSetOne);
        map.put(id,valSetTwo);
        Log.e("GetMapData",""+map);

//
//        String data = "";


    }

    public class StudentList extends AsyncTask<String,String,JSONObject>{


        private JSONObject jsonObject;
        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> data=new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("branchCode",strings[0]));
            data.add(new BasicNameValuePair("userType",strings[1]));
            data.add(new BasicNameValuePair("userId",strings[2]));
            data.add(new BasicNameValuePair("boardId",strings[3]));
            data.add(new BasicNameValuePair("classId",strings[4]));
            data.add(new BasicNameValuePair("sectionId",strings[5]));
            jsonObject=webServicesURL.getStudentListData(data);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("Messagelist",""+jsonObject);

            try {
                    if(jsonObject!=null) {
                        Toast.makeText(ComposeMessage.this, "" + jsonObject.get("message"), Toast.LENGTH_LONG).show();
                        Gson gson=new Gson();
                        ResponseClass responseClass=gson.fromJson(jsonObject.toString(),ResponseClass.class);
                        if(responseClass.getSuccess()==1){
                            studentlist = responseClass.getResult().getStudentDetailArrayList();
                            PreferenceUtil.setStudentListFromServer(ComposeMessage.this,gson.toJson(studentlist));
                            adapter= new SendMessageToParentAdapter(ComposeMessage.this,studentlist,ComposeMessage.this);
                            recyclerView.setAdapter(adapter);

                        }else {
                            Log.e("Ownlist",""+null);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



        }
    }
    public class GetClassList extends AsyncTask<String,String,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair>Classlist=new ArrayList<NameValuePair>();
            Classlist.add(new BasicNameValuePair("branchCode",strings[0]));
            Classlist.add(new BasicNameValuePair("userType",strings[1]));
            Classlist.add(new BasicNameValuePair("userId",strings[2]));
            Classlist.add(new BasicNameValuePair("boardId",strings[3]));
            JSONObject jsonObject=webServicesURL.getClassData(Classlist);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            Log.e("MessageClassData",""+jsonObject);
            try {
                if(jsonObject!=null){
                    Gson gson=new Gson();
                    ResponseClass responseClass=gson.fromJson(jsonObject.toString(),ResponseClass.class);
                    if(responseClass.getSuccess()==1){
                        classlist=responseClass.getResult().getClassDetailArrayList();
                        PreferenceUtil.setClassListFromServer(ComposeMessage.this,gson.toJson(classlist));
                        ArrayAdapter<ClassDetail> adapterCLASS = new ArrayAdapter<ClassDetail>(ComposeMessage.this, android.R.layout.simple_spinner_item,android.R.id.text1, classlist);
                        adapterCLASS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Class.setAdapter(adapterCLASS);
                    }
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public class getSectionList extends AsyncTask<String,String,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair>sectiondetail=new ArrayList<NameValuePair>();
            sectiondetail.add(new BasicNameValuePair("branchCode",strings[0]));
            sectiondetail.add(new BasicNameValuePair("userType",strings[1]));
            sectiondetail.add(new BasicNameValuePair("userId",strings[2]));
            sectiondetail.add(new BasicNameValuePair("boardId",strings[3]));
            sectiondetail.add(new BasicNameValuePair("classId",strings[4]));
            JSONObject jsonObject=webServicesURL.getSectionData(sectiondetail);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("SectionMessage",""+jsonObject);

            try {
                if(jsonObject!=null){
                    Gson gson=new Gson();
                    ResponseClass responseClass=gson.fromJson(jsonObject.toString(),ResponseClass.class);
                    if(responseClass.getSuccess()==1){
                        sectionlist=responseClass.getResult().getSectionDetailArrayList();
                        PreferenceUtil.setSectionListFromServer(ComposeMessage.this,gson.toJson(sectionlist));
                        ArrayAdapter<SectionDetail>adapterSECTION=new ArrayAdapter<SectionDetail>(ComposeMessage.this,android.R.layout.simple_spinner_item,android.R.id.text1,sectionlist);
                        adapterSECTION.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Section.setAdapter(adapterSECTION);
                    }else {

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class AllStudentList extends AsyncTask<String,String,JSONObject>{
        private String classIdlist,sectionIdlist;
        private ArrayList<StudentDetail> studentDetailArraylist;

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair>studentdata=new ArrayList<NameValuePair>();
            studentdata.add(new BasicNameValuePair("branchCode",strings[0]));
            studentdata.add(new BasicNameValuePair("userType",strings[1]));
            studentdata.add(new BasicNameValuePair("userId",strings[2]));
            studentdata.add(new BasicNameValuePair("boardId",strings[3]));
            JSONObject jsonObject=webServicesURL.getOwnStudentListData(studentdata);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("AllStudentdata",""+jsonObject);

            try {
                if(jsonObject!=null){
                    Gson gson=new Gson();
                    ResponseClass responseClass=gson.fromJson(jsonObject.toString(),ResponseClass.class);
                    if(responseClass.getSuccess()==1){
                        classIdlist=responseClass.getResult().getClassId();
                        sectionIdlist=responseClass.getResult().getSectionId();
                        studentDetailArraylist = responseClass.getResult().getStudentDetailArrayList();
                        PreferenceUtil.setOwnClassListFromServer(ComposeMessage.this,gson.toJson(classIdlist));
                        adapter= new SendMessageToParentAdapter(ComposeMessage.this,studentDetailArraylist, ComposeMessage.this);
                        recyclerView.setAdapter(adapter);


                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public class SendMessage extends AsyncTask<String,String,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair>usersend=new ArrayList<NameValuePair>();
            usersend.add(new BasicNameValuePair("branchCode",strings[0]));
            usersend.add(new BasicNameValuePair("userType",strings[1]));
            usersend.add(new BasicNameValuePair("userId",strings[2]));
            usersend.add(new BasicNameValuePair("boardId",strings[3]));
            usersend.add(new BasicNameValuePair("classId",strings[4]));
            usersend.add(new BasicNameValuePair("sectionId",strings[5]));
            usersend.add(new BasicNameValuePair("date",strings[6]));
            usersend.add(new BasicNameValuePair("time",strings[7]));
            usersend.add(new BasicNameValuePair("studentData",strings[8]));
            usersend.add(new BasicNameValuePair("message",strings[9]));
            JSONObject jsonObject=webServicesURL.SendMessageToParents(usersend);


            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            Log.e("SendMessage",""+jsonObject);
            try {
                if (jsonObject!=null){
                    Toast.makeText(ComposeMessage.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    Intent i=new Intent(ComposeMessage.this,Message.class);
                    startActivity(i);

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
