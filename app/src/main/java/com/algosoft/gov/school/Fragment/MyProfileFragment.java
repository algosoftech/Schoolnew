package com.algosoft.gov.school.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Activity.Loginpassword;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {
TextInputEditText edit_name,edit_mobile,edit_email,edit_address;
    private String branchCode,userType,userId;
    private ProgressBar progressBar;

    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_profile, container, false);
        setHasOptionsMenu(true);
        Button buttonsave=(Button)view.findViewById(R.id.Button_Save);
        edit_name=(TextInputEditText)view.findViewById(R.id.Edit_Name);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        edit_address=(TextInputEditText)view.findViewById(R.id.EditAddress);
        edit_email=(TextInputEditText)view.findViewById(R.id.EditEmail);
        edit_mobile=(TextInputEditText)view.findViewById(R.id.EditMobile);
        branchCode =  PreferenceUtil.getBranchCode(getContext());
        userType = PreferenceUtil.getUserType(getContext());
        userId = PreferenceUtil.getUserId(getContext());


        if (NetworkStatus.isNetworkAvailable(getContext())) {
            progressBar.setVisibility(View.VISIBLE);
            new getProfileDetails().execute(branchCode,userType,userId);

        }else{
            Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail=edit_email.getText().toString().trim()+"";
                String strAddress=edit_address.getText().toString().trim()+"";
                String strName=edit_name.getText().toString().trim()+"";
                String strMobile=edit_mobile.getText().toString().trim()+"";
                if((!strName.isEmpty())&&(!strEmail.isEmpty())&&(!strAddress.isEmpty())&&(!strMobile.isEmpty()));
                {

                    if (NetworkStatus.isNetworkAvailable(getContext())) {
                        progressBar.setVisibility(View.VISIBLE);
                        new UpdateProfileDetails().execute(branchCode,userType,userId,strName,strMobile,strEmail,strAddress);

                    }else{
                        Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

                    }

//                    Toast.makeText(getActivity(),"Details saved Successfully",Toast.LENGTH_LONG).show();
                }

            }
        });
        
        return view;

    }

    public class getProfileDetails extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            JSONObject jsonObject=webServicesURL.getProfileData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("UserProfileDetails", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        String userId = responseClass.getResult().getUserData().getUserId();
                        String userFName = responseClass.getResult().getUserData().getUserFName();
                        String userMName = responseClass.getResult().getUserData().getUserMName();
                        String userEmail = responseClass.getResult().getUserData().getUserEmail();
                        String userPhone = responseClass.getResult().getUserData().getUserPhone();
                        String userAddress = responseClass.getResult().getUserData().getUserAddress();
                        String userLocality = responseClass.getResult().getUserData().getUserLocality();
                        String userCity = responseClass.getResult().getUserData().getUserCity();
                        String userState = responseClass.getResult().getUserData().getUserState();
                        String userZipcode = responseClass.getResult().getUserData().getUserZipcode();

                        PreferenceUtil.setUserProfileAddress(getActivity(),userAddress);
                        PreferenceUtil.setUserLocality(getActivity(),userLocality);
                        PreferenceUtil.setUserCity(getActivity(),userCity);
                        PreferenceUtil.setUserState(getActivity(),userState);
                        PreferenceUtil.setUserZipcode(getActivity(),userZipcode);

                        edit_name.setText(userFName);
                        edit_mobile.setText(userPhone);
                        edit_email.setText(userEmail);
                        edit_address.setText(userAddress);

                    }else {
                        Log.e("UserProfileDetails", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class UpdateProfileDetails extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("userFName", params[3]));
            userData.add(new BasicNameValuePair("userPhone", params[4]));
            userData.add(new BasicNameValuePair("userEmail", params[5]));
            userData.add(new BasicNameValuePair("userAddress", params[6]));

            userData.add(new BasicNameValuePair("userMName", "xyz"));
            userData.add(new BasicNameValuePair("userLName", "Singh"));
            userData.add(new BasicNameValuePair("userLocality", "Sector 62"));
            userData.add(new BasicNameValuePair("userCity", "Noida"));
            userData.add(new BasicNameValuePair("userState","UP"));
            userData.add(new BasicNameValuePair("userZipcode","201309"));
            JSONObject jsonObject=webServicesURL.updateProfileData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("UserProfileUpdDetails", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        Log.e("UserProfileUpdDetails", "Successfully Saved"+responseClass.getMessage());

//                        String userId = responseClass.getResult().getUserData().getUserId();
//                        String userFName = responseClass.getResult().getUserData().getUserFName();
//                        String userMName = responseClass.getResult().getUserData().getUserMName();
//                        String userEmail = responseClass.getResult().getUserData().getUserEmail();
//                        String userPhone = responseClass.getResult().getUserData().getUserPhone();
//                        String userAddress = responseClass.getResult().getUserData().getUserAddress();
//                        String userLocality = responseClass.getResult().getUserData().getUserLocality();
//                        String userCity = responseClass.getResult().getUserData().getUserCity();
//                        String userState = responseClass.getResult().getUserData().getUserState();
//                        String userZipcode = responseClass.getResult().getUserData().getUserZipcode();
//
//                        PreferenceUtil.setUserProfileAddress(getActivity(),userAddress);
//                        PreferenceUtil.setUserLocality(getActivity(),userLocality);
//                        PreferenceUtil.setUserCity(getActivity(),userCity);
//                        PreferenceUtil.setUserState(getActivity(),userState);
//                        PreferenceUtil.setUserZipcode(getActivity(),userZipcode);
//
//                        edit_name.setText(userFName);
//                        edit_mobile.setText(userPhone);
//                        edit_email.setText(userEmail);
//                        edit_address.setText(userAddress);

                    }else {
                        Log.e("UserProfileUpdDetails", "NUll"+responseClass.getMessage());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.view_feedback);
        item.setVisible(false);
    }
}
