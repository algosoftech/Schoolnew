package com.algosoft.gov.school.Teacher;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.algosoft.gov.school.Adapter.MessageAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.MessageList;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ArrayList<MessageList> messageList;
    FloatingActionButton fab_msg;
    public static String messageType;
    String branchCode, userType, userId, boardId;
    Button tabSent, tabRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ImageButton btnBack=(ImageButton) findViewById(R.id.btn_back_message);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        messageList= new ArrayList<MessageList>();
        fab_msg= (FloatingActionButton) findViewById(R.id.fab_compose_msg);
        tabSent= (Button) findViewById(R.id.tab_sent);
        tabRec= (Button) findViewById(R.id.tab_rec);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerMessage);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        branchCode= PreferenceUtil.getBranchCode(this);
        userType= PreferenceUtil.getUserType(this);
        userId= PreferenceUtil.getUserId(this);
        boardId= PreferenceUtil.getDefaultBoardIdFromServer(this);
        messageType="received";
        new GetMessage().execute( branchCode, userType, userId, boardId, messageType);


        fab_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Message.this,ComposeMessage.class);
                startActivity(intent);
            }
        });

        tabSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSent.setTextColor(Color.parseColor("#FFFFFF"));
                tabRec.setTextColor(Color.parseColor("#55efefef"));
                messageType="sent";
                new GetMessage().execute( branchCode, userType, userId, boardId, messageType);

            }
        });
        tabRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabRec.setTextColor(Color.parseColor("#FFFFFF"));
                tabSent.setTextColor(Color.parseColor("#55efefef"));
                messageType="received";
                new GetMessage().execute( branchCode, userType, userId, boardId, messageType);
            }
        });
    }
    public class GetMessage extends AsyncTask<String, String, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("messageType", strings[4]));
            JSONObject jsonObject=webServicesURL.getMessageList(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.e("Message Response",""+jsonObject);
            try {
                Gson gson= new Gson();
                ResponseClass responseClass= gson.fromJson(jsonObject.toString(), ResponseClass.class);
                if(responseClass.getSuccess()==1){
                    messageList= responseClass.getResult().getMessageList();
                    adapter = new MessageAdapter(Message.this,messageList);
                    recyclerView.setAdapter(adapter);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
