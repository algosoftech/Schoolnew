package com.algosoft.gov.school.Fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Adapter.GalleryAdapter;
import com.algosoft.gov.school.Adapter.TakeAttendanceAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.TakeAttendanceActivity;
import com.algosoft.gov.school.response.GalleryDetails;
import com.algosoft.gov.school.response.LeaveTypeDetails;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.response.StudentDetail;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeaveFragment extends Fragment {
     EditText editText;
    private Calendar mycalendar;
    private int month;
    private int  dayOfMonth;
    private int year;
    DatePickerDialog datePickerDialog;
    private InputMethodManager inputMethodManager;
    DatePickerDialog mdatePickerDialog;
    EditText edit_reason,edit_address,edit_contact,edit_from,edit_to;
    private String branchCode,userId,boardId,type;
    private Spinner spinner;
    private ArrayList<LeaveTypeDetails> leavetypelist;
    private Button submit;
    public String leavetype,leavetypeid,usertype;
    private EditText fromdate_editText;
    private EditText editText_ToDate;
    private RadioGroup radioGroup,radioGroup1;
    private RadioButton rb1,rb2,rbb1,rbb2;
    public String fromleave;
    public String toleave;


    public LeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_leave, container, false);
        setHasOptionsMenu(true);
        branchCode =  PreferenceUtil.getBranchCode(getContext());
        userId = PreferenceUtil.getUserId(getContext());
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(getContext());
        type = PreferenceUtil.getSelectedTypeFromServer(getContext());
        leavetypelist = new ArrayList<>();
        submit=(Button)view.findViewById(R.id.Button_Submit);
        radioGroup = (RadioGroup)view.findViewById(R.id.Table_radio);
        radioGroup1 = (RadioGroup)view.findViewById(R.id.Table_radiotwo);
        editText_ToDate=(EditText)view.findViewById(R.id.Edit_SelectTo);
        fromdate_editText=(EditText)view.findViewById(R.id.Edit_SelectFrom);
        edit_reason=(EditText)view.findViewById(R.id.Edit_Reason);
        edit_address=(EditText)view.findViewById(R.id.Edit_Address);
        edit_contact=(EditText)view.findViewById(R.id.Edit_No);
        edit_from=(EditText)view.findViewById(R.id.Edit_SelectFrom);
        edit_to=(EditText)view.findViewById(R.id.Edit_SelectTo);
        rb1 = (RadioButton)view.findViewById(R.id.firsthalf_rb);
        rb2 = (RadioButton)view.findViewById(R.id.secondhalf_rb);
        rbb1 = (RadioButton)view.findViewById(R.id.firsthalf_rb1);
        rbb2 = (RadioButton)view.findViewById(R.id.secondhalf_rb1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.firsthalf_rb) {
                     fromleave = rb1.getText().toString();
                } else if(checkedId == R.id.secondhalf_rb) {
                    fromleave = rb2.getText().toString();
                }
                Toast.makeText(getContext(), fromleave,Toast.LENGTH_SHORT).show();
            }
        });


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.firsthalf_rb1) {
                    toleave = rbb1.getText().toString();
                } else if(checkedId == R.id.secondhalf_rb1) {
                    toleave = rbb2.getText().toString();
                }
                Toast.makeText(getContext(), toleave,Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!TextUtils.isEmpty(edit_reason.getText()))&&(!TextUtils.isEmpty(edit_address.getText()))
                        &&(!TextUtils.isEmpty(edit_contact.getText()))&&(!TextUtils.isEmpty(fromdate_editText.getText()))
                        &&(!TextUtils.isEmpty(editText_ToDate.getText()))) {

                    if (NetworkStatus.isNetworkAvailable(getContext())) {
                        String fromdate = fromdate_editText.getText().toString();
                        String todate = editText_ToDate.getText().toString();
                        String leavereason = edit_reason.getText().toString();
                        String addressOnleave = edit_address.getText().toString();
                        String contactOnleave = edit_contact.getText().toString();
                        Log.e("StringMobile",""+contactOnleave);
                        if(type.equalsIgnoreCase("teacher")) {
                            new leaveSendClass_teacher().execute(branchCode, type, userId, leavetypeid, fromdate, fromleave, todate, toleave, leavereason, addressOnleave, contactOnleave);
                        }else{
//                            new leaveSendClass_parent().execute(branchCode, type, userId, leavetypeid, fromdate, fromleave, todate, toleave, leavereason, addressOnleave, contactOnleave,childId);

                        }
                    }else{
                        Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(getActivity(), "Submitted Successfully", Toast.LENGTH_LONG).show();


                }


            }
        });


        fromdate_editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(LeaveFragment.this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                fromdate_editText.setText(day+"/"+(month+1)+"/"+year);

                            }
                        },year,month,dayOfMonth);
                datePickerDialog.show();

            }
        });

        editText_ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mycalender=Calendar.getInstance();
                year=mycalender.get(Calendar.YEAR);
                month=mycalender.get(Calendar.MONTH);
                dayOfMonth=mycalender.get(Calendar.DAY_OF_MONTH);
                mdatePickerDialog=new DatePickerDialog(LeaveFragment.this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                editText_ToDate.setText(day+"/"+(month+1)+"/"+year);
                            }
                        },year,month,dayOfMonth);
                mdatePickerDialog.show();
            }
        });

        spinner=(Spinner)view.findViewById(R.id.Leave_spinner);


        if (NetworkStatus.isNetworkAvailable(getContext())) {
            new LeaveListClass().execute(branchCode,type,userId);
        }else{
            Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(leavetypelist.size()>0){
                     leavetype = leavetypelist.get(position).getLeavesType();
                     leavetypeid = leavetypelist.get(position).getLeavesTypeId();
                     usertype = leavetypelist.get(position).getUserType();

                    Toast.makeText(getContext(), "Tpes"+leavetype+leavetypeid+usertype, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;

    }


    public class LeaveListClass extends AsyncTask<String, String,JSONObject>
    {


        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            JSONObject jsonObject=webServicesURL.getLeaveTypeList(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("LeaveList", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                       leavetypelist = responseClass.getResult().getLeaveTypeDetailsArrayList();
                        PreferenceUtil.setLeavetypeFromServer(getContext(),gson.toJson(leavetypelist));
                        ArrayAdapter<LeaveTypeDetails> adapter= new ArrayAdapter<LeaveTypeDetails>(getActivity(), android.R.layout.simple_spinner_item,leavetypelist);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }else {
                        Log.e("LeaveList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public class leaveSendClass_parent extends AsyncTask<String, String,JSONObject>
    {
        private String classIdlist,sectionIdlist;
        private ArrayList<StudentDetail> studentDetailArraylist;


        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();

            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("leavesTypeId", params[3]));
            userData.add(new BasicNameValuePair("fromDate", params[4]));
            userData.add(new BasicNameValuePair("fromLeave", params[5]));
            userData.add(new BasicNameValuePair("toDate", params[6]));
            userData.add(new BasicNameValuePair("toLeave", params[7]));
            userData.add(new BasicNameValuePair("leaveReason", params[8]));
            userData.add(new BasicNameValuePair("addressOnLeave", params[9]));
            userData.add(new BasicNameValuePair("contactOnLeave", params[10]));
            userData.add(new BasicNameValuePair("childId", params[11]));

            JSONObject jsonObject=webServicesURL.sendLeaveRequestData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("Sendleaveresponse", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
//                    if (responseClass.getSuccess() == 1) {
//                        classIdlist = responseClass.getResult().getClassId();
//                        sectionIdlist = responseClass.getResult().getSectionId();
//                        studentDetailArraylist = responseClass.getResult().getStudentDetailArrayList();
//                        Toast.makeText(getContext(), "Sendleaveresponse"+classIdlist, Toast.LENGTH_SHORT).show();
//
//                    }else {
//                        Log.e("OwnClassList", "NUll");
//                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public class leaveSendClass_teacher extends AsyncTask<String, String,JSONObject>
    {
        private String classIdlist,sectionIdlist;
        private ArrayList<StudentDetail> studentDetailArraylist;


        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();

            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("leavesTypeId", params[3]));
            userData.add(new BasicNameValuePair("fromDate", params[4]));
            userData.add(new BasicNameValuePair("fromLeave", params[5]));
            userData.add(new BasicNameValuePair("toDate", params[6]));
            userData.add(new BasicNameValuePair("toLeave", params[7]));
            userData.add(new BasicNameValuePair("leaveReason", params[8]));
            userData.add(new BasicNameValuePair("addressOnLeave", params[9]));
            userData.add(new BasicNameValuePair("contactOnLeave", params[10]));

            JSONObject jsonObject=webServicesURL.sendLeaveRequestData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("Sendleaveresponse", jsonObject.toString());
                    int success = Integer.parseInt(jsonObject.getString("success"));
                    String message = jsonObject.getString("message");
                    if(success==1) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(),Home.class);
                         i.putExtra("TypeSelected",type);
                        startActivity(i);
                        ((AppCompatActivity)getContext()).finish();

                    }else{
                        Toast.makeText(getContext(),  message, Toast.LENGTH_SHORT).show();
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
