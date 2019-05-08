package com.algosoft.gov.school.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.GalleryImageAdapter;
import com.algosoft.gov.school.Adapter.NewsDetailAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GalleryImage;
import com.algosoft.gov.school.response.NewsDetail;
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

public class NewsDetailActivity extends AppCompatActivity {

    private String branchCode,userId,boardId,type;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerNewsdetails);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(NewsDetailActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        branchCode =  PreferenceUtil.getBranchCode(NewsDetailActivity.this);
        userId = PreferenceUtil.getUserId(NewsDetailActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(NewsDetailActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(NewsDetailActivity.this);
        String newsid = getIntent().getStringExtra("NewsId");
        if (NetworkStatus.isNetworkAvailable(NewsDetailActivity.this)) {
            new NewsDetailClass().execute(branchCode, type, userId, newsid);
        }else{
            Toast.makeText(NewsDetailActivity.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }
    }

    public class NewsDetailClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("newsId", params[3]));
            JSONObject jsonObject=webServicesURL.getNewsDetail(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("NewsDetail", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        NewsDetail newsdata = responseClass.getResult().getNewsdata();

                        NewsDetailAdapter adapter = new NewsDetailAdapter(NewsDetailActivity.this, newsdata);
                        recyclerView.setAdapter(adapter);

                    }else {
                        Log.e("LeaveList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
