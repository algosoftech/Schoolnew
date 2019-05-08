package com.algosoft.gov.school.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Student;
import com.algosoft.gov.school.response.LogintypeDetail;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends Activity implements AdapterView.OnItemSelectedListener {
// private static final String[]items={ "Select Type","Teacher","Parents"};
    private Spinner spinner;
    private LinearLayout linearlogin,linearLayout;
    private EditText editId;
    private Button button;
    private ProgressBar progressBar;
    ArrayList<LogintypeDetail> type_list = new ArrayList<>();
    ArrayList<String> name_type = new ArrayList<>();
    private String selected_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        linearlogin=(LinearLayout)findViewById(R.id.Linear_login_ok);
        editId=(EditText)findViewById(R.id.Edit_SchoolNo);
        linearLayout=(LinearLayout)findViewById(R.id.liner_login);
        button=(Button)findViewById(R.id.Button_School);
        name_type.add("Select Type");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editId.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Enter your six digit school Id", Toast.LENGTH_SHORT).show();

                } else if(!editId.getText().toString().isEmpty()) {
                    if (NetworkStatus.isNetworkAvailable(Login.this)) {
                        disableScreen();
                        new CheckBranchCodeClass().execute(editId.getText().toString());
                    }else {
                        Toast.makeText(Login.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        spinner=(Spinner)findViewById(R.id.Login_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    private void disableScreen() {
        progressBar.setVisibility(View.VISIBLE);
        editId.setEnabled(false);
        button.setEnabled(false);

    }
    private void enableScreen() {
        progressBar.setVisibility(View.GONE);
        editId.setEnabled(true);
        button.setEnabled(true);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_type = name_type.get(position);
//        Toast.makeText(this, "Selected"+selected_type, Toast.LENGTH_SHORT).show();
        if(position == 0){

        }else {
            Intent intent = new Intent(Login.this, Loginpassword.class);
            intent.putExtra("TypeSelected",selected_type);
            PreferenceUtil.setSelectedTypeFromServer(Login.this, selected_type);
            startActivity(intent);
        }

//        if (position == 1) {
//            Intent intent = new Intent(Login.this, Loginpassword.class);
//            intent.putExtra("TypeSelected","Teacher");
//            startActivity(intent);
//
//        }else if (position==2){
//            Intent intent=new Intent(Login.this,Loginpassword.class);
//            intent.putExtra("TypeSelected","Parents");
//            startActivity(intent);
//
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onBackPressed(){
//        SharedPreferences settings = getApplicationContext().getSharedPreferences("UserId", Context.MODE_PRIVATE);
//        settings.edit().clear().commit();
//                    PreferenceUtil.clearPreferenceObject(Home.this);
//        Intent i = new Intent(Login.this, null);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
        finish();
//        final Dialog dialog = new Dialog(Login.this);
//        dialog.setContentView(R.layout.alertbox);
//        dialog.setTitle("Do you want to exit");
//        dialog.setCanceledOnTouchOutside(false);
//        Button dialogButton_yes = (Button) dialog.findViewById(R.id.Button_Yes);
//        Button dialogButton_no = (Button) dialog.findViewById(R.id.Button_No);
//        dialogButton_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialogButton_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                finish();
//            }
//        });
//        dialog.show();

    }


    public class CheckBranchCodeClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            JSONObject jsonObject=webServicesURL.checkSchoolCode(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                if (jsonObject!=null) {
                    Log.e("CheckBranchCode", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        enableScreen();
                    String branchCode = responseClass.getResult().getBranchCode();
                    PreferenceUtil.setBranchCode(Login.this,branchCode);
//                    Toast.makeText(Login.this, "Branch"+branchCode, Toast.LENGTH_SHORT).show();
                     linearlogin.setVisibility(View.GONE);
                     linearLayout.setVisibility(View.VISIBLE);
                     new GetLoginType().execute(editId.getText().toString());
                }else {
                        enableScreen();
                    Log.e("CheckBranchCode", "NUll");
                        Toast.makeText(Login.this, ""+responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
                }else {
                    enableScreen();
                    Log.e("CheckBranchCode", "NUll");
                    Toast.makeText(Login.this, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public class GetLoginType extends AsyncTask<String, String,JSONObject>
    {
        private ArrayList<LogintypeDetail> logintype;
        private String name;

        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            JSONObject jsonObject=webServicesURL.getLoginData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("getLoginType", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        type_list.addAll(responseClass.getResult().getLogintypeDetailArrayList());

                    }
                    for (int i = 0; i < type_list.size(); i++) {
                        String name = type_list.get(i).getName();
                        name_type.add(name);
                    }
                    ArrayAdapter<String>adapter= new ArrayAdapter<String>(Login.this, android.R.layout.simple_spinner_item,name_type);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                }else {
                    enableScreen();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
