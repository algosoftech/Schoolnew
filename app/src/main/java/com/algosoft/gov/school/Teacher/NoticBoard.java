package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Adapter.NewsAdapter;
import com.algosoft.gov.school.Adapter.NoticeBoardAdapter;
import com.algosoft.gov.school.Parents.NoticeBoard;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.NewsDetail;
import com.algosoft.gov.school.response.NoticeBoardList;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoticBoard extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList notice = new ArrayList<>(Arrays.asList("Exam Notice","Holiday Notice"));
    private String branchCode,userId,boardId,type;
    private TextView alerttv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_board);

        branchCode =  PreferenceUtil.getBranchCode(NoticBoard.this);
        userId = PreferenceUtil.getUserId(NoticBoard.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(NoticBoard.this);
        type = PreferenceUtil.getSelectedTypeFromServer(NoticBoard.this);

        alerttv = (TextView)findViewById(R.id.alert_tv);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerNotice);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ImageView imageView=(ImageView)findViewById(R.id.Image_Notice_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        if (NetworkStatus.isNetworkAvailable(NoticBoard.this)) {
            new NoticeListClass().execute(branchCode,type,userId,boardId);
        }else{
            Toast.makeText(NoticBoard.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }
    }

    public class NoticeListClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("boardId", params[3]));
            JSONObject jsonObject=webServicesURL.getNoticeList(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("NoticeList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        ArrayList<NoticeBoardList> newslist = responseClass.getResult().getNoticeBoardList();
//                        adapter=new NoticeBoardAdapter(NoticBoard.this,notice);
//                        recyclerView.setAdapter(adapter);
//                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
                        PreferenceUtil.setNewsListFromServer(NoticBoard.this,gson.toJson(newslist));
                        adapter=new NoticeBoardAdapter(NoticBoard.this,newslist);
                        recyclerView.setAdapter(adapter);

                    }else {
                        alerttv.setVisibility(View.VISIBLE);
                        Log.e("sectionlist", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
