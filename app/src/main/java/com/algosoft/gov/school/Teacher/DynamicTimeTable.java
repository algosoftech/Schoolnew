package com.algosoft.gov.school.Teacher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.DynamicTimeAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.TimeTable;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DynamicTimeTable extends AppCompatActivity {

    TextView textaction;
    ImageButton back;
    RecyclerView recyclerView, recyclerView_subject;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10;
    ArrayList<String> period1, period2, period3, period4, period5, period6, period7, period8, period9, period10;
    TextView Dayname, subject;
    ArrayList<TextView> SubjectList;
    ArrayList<String> dayes;
    ArrayList<TimeTable> periodList = new ArrayList<>();
    ArrayList<String> SNO = new ArrayList<>();
    ArrayList<LinearLayout> rows = new ArrayList<>();
    LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    DynamicTimeAdapter adapter;
    private final int LEFTTYPE = 1;
    private final int RIGHTTYPE = 2;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_time_table);

        userId= PreferenceUtil.getUserId(DynamicTimeTable.this);


//        periodList.add("Monday");
//        periodList.add("Tuesday");
//        periodList.add("Wednesday");
//        periodList.add("Thursday");
//        periodList.add("Friday");
//        periodList.add("Saturday");
//        periodList.add("Saturday");

//
        dayes = new ArrayList<String>();

        dayes.add("Monday");
        dayes.add("Tuesday");
        dayes.add("Wednesday");
        dayes.add("Thursday");
        dayes.add("Friday");
        dayes.add("Saturday");

        back = (ImageButton) findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        if (NetworkStatus.isNetworkAvailable(DynamicTimeTable.this)){
            new GetTimeTable().execute(userId);
        }else {
            Toast.makeText(this,"Please check your network connection",Toast.LENGTH_LONG).show();
        }


        recyclerView_subject = (RecyclerView) findViewById(R.id.Recyclerview_subject);
        recyclerView_subject.setHasFixedSize(false);
        linearLayoutManager2 = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return super.canScrollHorizontally();
            }
        };
        recyclerView_subject.setLayoutManager(linearLayoutManager2);
        //right type







        linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DynamicTimeAdapter(DynamicTimeTable.this, dayes);
        recyclerView.setAdapter(adapter);


    }
    public class GetTimeTable extends AsyncTask<String,String, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> tabledata=new ArrayList<NameValuePair>();
            tabledata.add(new BasicNameValuePair("teacher_id",strings[0]));
            JSONObject jsonObject=webServicesURL.GetTeacherTimeTable(tabledata);
            return jsonObject;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("TimeTabledata",""+jsonObject);

            try {
                if(jsonObject!=null){
                    Gson gson=new Gson();
                    ResponseClass responseClass=gson.fromJson(jsonObject.toString(),ResponseClass.class);
                    if(responseClass.getSuccess()==1){
                        periodList=responseClass.getResult().getTeacherTimeTableList();
                        Log.e("Peroid list",""+periodList);
                        adapter = new DynamicTimeAdapter(DynamicTimeTable.this, periodList,true);
                        recyclerView_subject.setAdapter(adapter);



                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


