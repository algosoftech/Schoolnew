package com.algosoft.gov.school.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.algosoft.gov.school.Fragment.ReceivedFragment;
import com.algosoft.gov.school.Fragment.SentFragment;
import com.algosoft.gov.school.R;
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

public class ViewFeedbackActivity extends AppCompatActivity {
    private String branchCode,userId,boardId,type;
    private RadioGroup radiongroup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        progressBar = (ProgressBar)findViewById(R.id.sent_progressbar);
        branchCode =  PreferenceUtil.getBranchCode(ViewFeedbackActivity.this);
        userId = PreferenceUtil.getUserId(ViewFeedbackActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(ViewFeedbackActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(ViewFeedbackActivity.this);
        String startDate = "2019-01-01";
        progressBar.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("FeedbackType","Sent");
        bundle.putString("branchCode",branchCode);
        bundle.putString("userType",type);
        bundle.putString("userId",userId);
        bundle.putString("boardId",boardId);
        Fragment fr = new SentFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmenttransaction = fm.beginTransaction();
        fr.setArguments(bundle);
        fragmenttransaction.replace(R.id.fragment_place,fr);
        fragmenttransaction.commit();

        radiongroup = (RadioGroup)findViewById(R.id.radio_Group);
        radiongroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.sent_radio){
//                    Toast.makeText(ViewFeedbackActivity.this, "sent", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("FeedbackType","Sent");
                    bundle.putString("branchCode",branchCode);
                    bundle.putString("userType",type);
                    bundle.putString("userId",userId);
                    bundle.putString("boardId",boardId);
                    Fragment fr = new SentFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmenttransaction = fm.beginTransaction();
                    fr.setArguments(bundle);
                    fragmenttransaction.replace(R.id.fragment_place,fr);
                    fragmenttransaction.commit();

                }

                if(checkedId==R.id.received_radio){
//                    Toast.makeText(ViewFeedbackActivity.this, "received", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("FeedbackType","Received");
                    bundle.putString("branchCode",branchCode);
                    bundle.putString("userType",type);
                    bundle.putString("userId",userId);
                    bundle.putString("boardId",boardId);
                    Fragment fr = new ReceivedFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmenttransaction = fm.beginTransaction();
                    fr.setArguments(bundle);
                    fragmenttransaction.replace(R.id.fragment_place,fr);
                    fragmenttransaction.commit();
                }
            }
        });



    }



}
