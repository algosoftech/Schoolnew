package com.algosoft.gov.school.Fragment;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.NewsDetailActivity;
import com.algosoft.gov.school.Activity.ViewFeedbackActivity;
import com.algosoft.gov.school.Adapter.NewsDetailAdapter;
import com.algosoft.gov.school.Adapter.SentDetailAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.FeedBackListDetail;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SentFragment extends Fragment {
    private String branchCode,userId,boardId,type;
    private String feedbacktype,userType;
    private ArrayList<FeedBackListDetail> feedbacklist = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public SentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  =  inflater.inflate(R.layout.fragment_sent, container, false);
        try {
             feedbacktype = getArguments().getString("FeedbackType");
             branchCode = getArguments().getString("branchCode");
             userType = getArguments().getString("userType");
             userId = getArguments().getString("userId");
             boardId = getArguments().getString("boardId");

             recyclerView=(RecyclerView)v.findViewById(R.id.RecyclerSentdetails);
            recyclerView.setHasFixedSize(false);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (NetworkStatus.isNetworkAvailable(getActivity())) {

            new ViewFeedbackClass().execute(branchCode, userType, userId, boardId, "sent");
        }else{
            Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }
        return v;
    }



    public class ViewFeedbackClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("boardId", params[3]));
            userData.add(new BasicNameValuePair("feedbackType", params[4]));
            JSONObject jsonObject=webServicesURL.getFeedBackData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("FeedbackListS", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        feedbacklist = responseClass.getResult().getFeedBackListDetailArrayList();
                        PreferenceUtil.setFeedbackListFromServer(getContext(),gson.toJson(feedbacklist));
                        SentDetailAdapter adapter = new SentDetailAdapter(getActivity(), feedbacklist);
                        recyclerView.setAdapter(adapter);
                    }else {
                        Log.e("FeedbackList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
