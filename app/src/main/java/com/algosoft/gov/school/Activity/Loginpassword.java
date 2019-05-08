package com.algosoft.gov.school.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.TeacherAttendanceAdapter;
import com.algosoft.gov.school.Fragment.TeacherAttendanceFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.BoardDetails;
import com.algosoft.gov.school.response.ChildDetails;
import com.algosoft.gov.school.response.GetTeacherAttendanceResponse;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Loginpassword extends AppCompatActivity {
    private static final String[] items = {"Select Type", "Teacher", "Parents", "Hostel"};
    private static Button VISIBLE;
    private String type;
    private static Button button2;
    private TextInputEditText textName;
    private TextInputEditText textPassword;
    private Button button;
    private ProgressBar progressBar;
    String Token;

    //    public static final String ITEM_ID = "item_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpassword);

        Token = FirebaseInstanceId.getInstance().getToken();
        Log.e("TokenId",""+Token);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textName = (TextInputEditText) findViewById(R.id.Edit_Username);
        textPassword = (TextInputEditText) findViewById(R.id.Edit_Password);

        button2 = (Button) findViewById(R.id.LoginButton2);
        Intent intent = getIntent();
        type = intent.getStringExtra("TypeSelected");
//        Toast.makeText(this, ""+type, Toast.LENGTH_SHORT).show();
        PreferenceUtil.setUserType(Loginpassword.this, type);

        if (type.equalsIgnoreCase("Parents")) {
            button2.setVisibility(View.VISIBLE);

        }
        button = (Button) findViewById(R.id.LoginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public String deviceId;
            public String branchCode;

            @Override
            public void onClick(View v) {
                if (textName.getText().toString().isEmpty() || textPassword.getText().toString().isEmpty()) {
                    Toast.makeText(Loginpassword.this, "Please fill details", Toast.LENGTH_SHORT).show();
                } else {
                    branchCode = PreferenceUtil.getBranchCode(Loginpassword.this);
                    deviceId = PreferenceUtil.getDeviceId(Loginpassword.this);

                    if (NetworkStatus.isNetworkAvailable(Loginpassword.this)) {
                        disableScreen();
                        new UserLoginClass().execute(branchCode, type, textName.getText().toString(), textPassword.getText().toString(), deviceId, "Android");
                    } else {
                        Toast.makeText(Loginpassword.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void disableScreen() {
        progressBar.setVisibility(View.VISIBLE);
        textName.setEnabled(false);
        textPassword.setEnabled(false);
    }

    private void enableScreen() {
        progressBar.setVisibility(View.GONE);
        textName.setEnabled(true);
        textPassword.setEnabled(true);
        button2.setEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }


    public class UserLoginClass extends AsyncTask<String, String, JSONObject> {
        private ArrayList<BoardDetails> userBoardList;
        private ArrayList<ChildDetails> userChildList;
        private JSONObject result;

        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL = new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userEmail", params[2]));
            userData.add(new BasicNameValuePair("userPassword", params[3]));
            userData.add(new BasicNameValuePair("deviceId", params[4]));
            userData.add(new BasicNameValuePair("deviceType", params[5]));
            JSONObject jsonObject = webServicesURL.userLogin(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try {
                if (jsonObject != null) {
                    Log.e("LoginResponse", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        result = jsonObject.getJSONObject("result");
                        JSONObject userData = result.getJSONObject("userData");
                        JSONArray boardlist = userData.getJSONArray("boardList");
                        PreferenceUtil.setBoardList(Loginpassword.this, boardlist.toString());
                        String schoolId = responseClass.getResult().getUserData().getSchoolId();
                        String schoolName = responseClass.getResult().getUserData().getSchoolName();
                        String branchId = responseClass.getResult().getUserData().getBranchId();
                        String branchCode = responseClass.getResult().getUserData().getBranchCode();
                        String branchName = responseClass.getResult().getUserData().getBranchName();
                        String userType = responseClass.getResult().getUserData().getUserType();
                        String userId = responseClass.getResult().getUserData().getUserId();
                        String employeeid = responseClass.getResult().getUserData().getEmployeeId();
                        String userFirstName = responseClass.getResult().getUserData().getUserFName();
                        String userMiddleName = responseClass.getResult().getUserData().getUserMName();
                        String userLastName = responseClass.getResult().getUserData().getUserLName();
                        String userEmail = responseClass.getResult().getUserData().getUserEmail();
                        String userPhone = responseClass.getResult().getUserData().getUserPhone();
                        String userLastLogin = responseClass.getResult().getUserData().getUserLastLogin();

                        String userBoardId = responseClass.getResult().getUserData().getBoardId();
                        String userBoardName = responseClass.getResult().getUserData().getBoardName();
                        String userChildId = responseClass.getResult().getUserData().getChildId();
                        String userChildName = responseClass.getResult().getUserData().getChildName();
                        String userChildClassId = responseClass.getResult().getUserData().getChildClassId();
                        String userChildClass = responseClass.getResult().getUserData().getChildClass();
                        String userChildSectionId = responseClass.getResult().getUserData().getChildSectionId();
                        String userChildSection = responseClass.getResult().getUserData().getChildSection();

                        userBoardList = responseClass.getResult().getUserData().getBoardList();
                        userChildList = responseClass.getResult().getUserData().getChildList();

                        PreferenceUtil.setSchoolId(Loginpassword.this, schoolId);
                        PreferenceUtil.setSchoolName(Loginpassword.this, schoolName);
                        PreferenceUtil.setBranchId(Loginpassword.this, branchId);
                        PreferenceUtil.setBranchCode(Loginpassword.this, branchCode);
                        PreferenceUtil.setBranchName(Loginpassword.this, branchName);
                        PreferenceUtil.setUserType(Loginpassword.this, userType);
                        PreferenceUtil.setUserId(Loginpassword.this, userId);
                        PreferenceUtil.setEmployeeId(Loginpassword.this, employeeid);
                        PreferenceUtil.setUserFName(Loginpassword.this, userFirstName);
                        PreferenceUtil.setUserMName(Loginpassword.this, userMiddleName);
                        PreferenceUtil.setUserLName(Loginpassword.this, userLastName);
                        PreferenceUtil.setUserEmail(Loginpassword.this, userEmail);
                        PreferenceUtil.setUserPhone(Loginpassword.this, userPhone);
                        PreferenceUtil.setUserLastlogin(Loginpassword.this, userLastLogin);
                        PreferenceUtil.setDefaultBoardIdFromServer(Loginpassword.this, userBoardId);
                        checkAttendance(branchCode,type,userId,userBoardId);
                        String deviceId = PreferenceUtil.getDeviceId(Loginpassword.this);
                        new UpdateDeviceId().execute(branchCode, type, userId, deviceId, "Android");
                    } else {
                        enableScreen();
                        Log.e("LoginResponse", "NUll");
                        Toast.makeText(Loginpassword.this, "" + responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void checkAttendance(String branchCode, String userType,String userId, String boardId) {
        if (PreferenceUtil.getSelectedTypeFromServer(this).equalsIgnoreCase("Teacher")) {
                String fromDate= KeyGenerationClass.getDate();
                String toDate= KeyGenerationClass.getDate();
                new GetTeacherAttendance().execute(branchCode, userType, userId, boardId, fromDate, toDate);
            } else {

            }
        }

    public class GetTeacherAttendance extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL = new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("fromDate", strings[4]));
            userData.add(new BasicNameValuePair("toDate", strings[5]));
            JSONObject jsonObject = webServicesURL.getTeacherAttendance(userData);

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                Log.e("Result Attendance", "" + jsonObject);
                Gson gson = new Gson();
                GetTeacherAttendanceResponse attendanceResponse = gson.fromJson(jsonObject.toString(), GetTeacherAttendanceResponse.class);
                if (attendanceResponse.getSuccess() == 1) {
                    if (attendanceResponse.getResult() != null) {
                        String attendance= attendanceResponse.getResult().getAttendanceData().get(0).getAttendanceType();
                        if(attendance.equalsIgnoreCase("Present")){
                            PreferenceUtil.setIsTeacherPresent(Loginpassword.this, true);
                        } else if (attendance.equalsIgnoreCase("Absent")) {
                            PreferenceUtil.setIsTeacherPresent(Loginpassword.this, true);
                        }
                        PreferenceUtil.setAttendanceDate(Loginpassword.this, KeyGenerationClass.getDate());
                        PreferenceUtil.setAttendanceTime(Loginpassword.this, KeyGenerationClass.getTime());
                        }
                    }else{
                    try {
                        Log.e("Error_attendance check",""+jsonObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                }
            }
        }

    public class UpdateDeviceId extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("deviceId", params[3]));
            userData.add(new BasicNameValuePair("deviceType", params[4]));
            JSONObject jsonObject=webServicesURL.deviceIdUpdate(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                if (jsonObject!=null) {
                    Log.e("CheckDeviceIdUpdate", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        enableScreen();
                        Intent i = new Intent(Loginpassword.this, Home.class);
                        i.putExtra("TypeSelected", type);
                        startActivity(i);
                    }else {
                        enableScreen();
                        Toast.makeText(Loginpassword.this, ""+responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("CheckDeviceIdUpdate", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
