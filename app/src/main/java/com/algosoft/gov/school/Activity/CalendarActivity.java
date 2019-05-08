package com.algosoft.gov.school.Activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.Homeadapter;
import com.algosoft.gov.school.Fragment.EventFragment;
import com.algosoft.gov.school.Fragment.ExaminationFragment;
import com.algosoft.gov.school.Fragment.HolidayFragment;
import com.algosoft.gov.school.Fragment.HomeFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.EventDataList;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private String branchCode,userId,boardId,type;
    private ArrayList<EventDataList> calenderlist;

    ImageView back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        back=(ImageView)findViewById(R.id.Image_Calender);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        branchCode =  PreferenceUtil.getBranchCode(CalendarActivity.this);
        userId = PreferenceUtil.getUserId(CalendarActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(CalendarActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(CalendarActivity.this);
        String startDate = "2019-01-01";


        if (NetworkStatus.isNetworkAvailable(CalendarActivity.this)) {
//            progressBar.setVisibility(View.VISIBLE);
            new CalenderListClass().execute(branchCode, type, userId, startDate, boardId);
        }else{
            Toast.makeText(CalendarActivity.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }
    }



    public void selectFrag(View view) {
        Fragment fr = null;
        if(view == findViewById(R.id.holiday_tv)) {
            fr = new HolidayFragment();
        }else if(view == findViewById(R.id.events_tv)){
            fr = new EventFragment();
        }else if(view == findViewById(R.id.exaination_tv)){
            fr = new ExaminationFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, fr);
        fragmentTransaction.commit();
    }


    public class CalenderListClass extends AsyncTask<String, String,JSONObject>

    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("startDate", params[3]));
            userData.add(new BasicNameValuePair("boardId", params[4]));
            JSONObject jsonObject=webServicesURL.getCalenderData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("CalenderList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        calenderlist = responseClass.getResult().getEventDataListArrayList();
                        PreferenceUtil.setCalenderListFromServer(CalendarActivity.this,gson.toJson(calenderlist));
                    }else {
                        Log.e("CalenderList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
