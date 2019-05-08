package com.algosoft.gov.school.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Activity.Home;
import com.algosoft.gov.school.Activity.Login;
import com.algosoft.gov.school.Activity.Loginpassword;
import com.algosoft.gov.school.Adapter.Homeadapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.Teacher.TakeAttendanceActivity;
import com.algosoft.gov.school.response.MenuList;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    private ArrayList<String> nameslist;
    private Spinner spinner;
    public String selectedboard;
    private static final String[]items={ "Select Board","ICSE","CBSE","STATE BOARD"};
    private String fname;
    private TextView fnametv, tvAskAttendance;
    private ProgressBar progressBar;
    private ArrayList<MenuList> menulist = new ArrayList<>();
    LinearLayout layoutBeforeAttendance, layoutAfterAttendance;
    public static Boolean present= false;
    RadioGroup radioGroup_home;
    RadioButton btn_present, btn_absent;
    private String attendanceData, branchCode,userId, boardId, userType, date, time;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        present= PreferenceUtil.getIsTeacherPresent(getContext());

        branchCode = PreferenceUtil.getBranchCode(getActivity());
        userId = PreferenceUtil.getUserId(getActivity());
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(getActivity());
        userType = PreferenceUtil.getSelectedTypeFromServer(getActivity());
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        layoutBeforeAttendance=(LinearLayout) view.findViewById(R.id.before_attendance_layout);
        layoutAfterAttendance= (LinearLayout) view.findViewById(R.id.after_attendance_layout);
        fnametv =(TextView) view.findViewById(R.id.Text_Name);
        tvAskAttendance= (TextView) view.findViewById(R.id.ask_attendance);
        radioGroup_home= (RadioGroup) view.findViewById(R.id.radioGroup_home);
        btn_present= (RadioButton) view.findViewById(R.id.radio_yes);
        btn_absent= (RadioButton) view.findViewById(R.id.radio_no);
        recyclerView=(RecyclerView) view.findViewById(R.id.RecyclerHome);

        fname = PreferenceUtil.getUserFName(getContext());
        nameslist = new ArrayList<>();
        nameslist.add("Attendance");
        nameslist.add("Home Work");
        nameslist.add("Syllabus");
        nameslist.add("View Attendance");
        nameslist.add("Time Table");
        nameslist.add("Notice Board");
        nameslist.add("Message");
        nameslist.add("Notification");
        nameslist.add("Assignment");


        radioGroup_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                date= KeyGenerationClass.getDate();
                time= KeyGenerationClass.getTime();
                Log.e("onChecked",""+group.getCheckedRadioButtonId()+"radioBtnId: "+R.id.radio_yes);
                if(group.getCheckedRadioButtonId() ==R.id.radio_yes) {
                    attendanceData= "Present";
                    Log.e("dateTosend",""+"branchCode: "+branchCode+"\n userType: "+userType+"\n userId: "+userId+"\n boardId: "+boardId
                            +"\nDate: "+date+"\nTime: "+time+"\n data: "+attendanceData);
                    if (NetworkStatus.isNetworkAvailable(getActivity())) {
                        new SetTeacherAttendance().execute(branchCode, userType, userId, boardId, attendanceData , date, time);
                    }else{
                        Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }else if(group.getCheckedRadioButtonId() ==R.id.radio_no){
                    attendanceData= "Absent";
                    if (NetworkStatus.isNetworkAvailable(getActivity())) {
                        new SetTeacherAttendance().execute(branchCode, userType, userId, boardId, attendanceData , date, time);
                    }else{
                        Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
//        String boardlist = PreferenceUtil.getBoardList(getContext());
//        Toast.makeText(getContext(), "B"+boardlist, Toast.LENGTH_SHORT).show();
//        try {
//            JSONArray jsrray = new JSONArray(boardlist);
//            for(int i=0;i<jsrray.length();i++){
//              JSONObject result =   jsrray.getJSONObject(i);
//                result.getString("encrypt_id");
//                result.getString("board_name");
//                result.getString("board_short_name");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        spinner=(Spinner)view.findViewById(R.id.board_spinner);
//        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position==0){
//                }else {
//                    selectedboard = parent.getSelectedItem() + "";
//                    PreferenceUtil.setSelectedBoardFromServer(getContext(), selectedboard);
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setHasFixedSize(false);
        String type = PreferenceUtil.getUserType(getContext());
//        Toast.makeText(getContext(), "Type"+type, Toast.LENGTH_SHORT).show();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(PreferenceUtil.getAttendanceDate(getActivity()).equalsIgnoreCase(KeyGenerationClass.getDate()) && userType.equalsIgnoreCase("Teacher")){
            enableScreen();
        }else{
            disableScreen();
        }
        if (PreferenceUtil.getMenuListFromServer(getActivity()).isEmpty()) {
            if (NetworkStatus.isNetworkAvailable(getContext())) {
                progressBar.setVisibility(View.VISIBLE);
                new menuListClass().execute(type);
            }else{
                Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
            }
        }else {
            menulist.clear();
            String examListData=PreferenceUtil.getMenuListFromServer(getActivity());
            Gson gson=new Gson();
            menulist=(ArrayList<MenuList>) gson.fromJson(examListData,new TypeToken<ArrayList<MenuList>>(){}.getType());
            Homeadapter adapter1 = new Homeadapter(HomeFragment.this, menulist);
            recyclerView.setAdapter(adapter1);

        }
    }

    private void enableScreen() {
        String type = PreferenceUtil.getUserType(getContext());
        layoutAfterAttendance.setVisibility(View.VISIBLE);
        layoutBeforeAttendance.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAlpha(1);
        fnametv.setText("Welcome "+fname);
        Home.toggle.setDrawerIndicatorEnabled(true);

        if (PreferenceUtil.getMenuListFromServer(getActivity()).isEmpty()) {
            if (NetworkStatus.isNetworkAvailable(getContext())) {
                progressBar.setVisibility(View.VISIBLE);
                new menuListClass().execute(type);
            }else{
                Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
            }
        }else {
            menulist.clear();
            String examListData=PreferenceUtil.getMenuListFromServer(getActivity());
            Gson gson=new Gson();
            menulist=(ArrayList<MenuList>) gson.fromJson(examListData,new TypeToken<ArrayList<MenuList>>(){}.getType());
            Homeadapter adapter1 = new Homeadapter(HomeFragment.this, menulist);
            recyclerView.setAdapter(adapter1);

        }

    }

    private void disableScreen() {
        layoutBeforeAttendance.setVisibility(View.VISIBLE);
        layoutAfterAttendance.setVisibility(View.GONE);
        Home.toggle.setDrawerIndicatorEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        tvAskAttendance.setText("Hello "+fname+", are you present today? ");
    }

    public class menuListClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
                userData.add(new BasicNameValuePair("userType", params[0]));
                JSONObject jsonObject = webServicesURL.getMenuData(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                        Log.e("MenuList", jsonObject.toString());
                        Gson gson = new Gson();
                        ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                        if (responseClass.getSuccess() == 1) {

                            menulist = responseClass.getResult().getMenuListArrayList();
                            adapter = new Homeadapter(HomeFragment.this, menulist);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            PreferenceUtil.setMenuListFromServer(getActivity(), gson.toJson(menulist));
                        } else {
                            Log.e("MenuList", "NUll");
                        }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public class SetTeacherAttendance extends AsyncTask<String, String, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("attendanceData", strings[4]));
            userData.add(new BasicNameValuePair("date", strings[5]));
            userData.add(new BasicNameValuePair("time", strings[6]));
            JSONObject jsonObject = webServicesURL.saveTeacherAttendance(userData);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if(jsonObject != null) {
//            try {
//                if(jsonObject.getInt("success")==1){
                try {
                    Toast.makeText(getActivity(), "" + jsonObject.get("message"), Toast.LENGTH_SHORT).show();
                    Log.e("after_attendance", "" + jsonObject);
                    enableScreen();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (attendanceData.equalsIgnoreCase("Present")) {
                    PreferenceUtil.setIsTeacherPresent(getActivity(), true);
                } else if (attendanceData.equalsIgnoreCase("Absent")) {
                    PreferenceUtil.setIsTeacherPresent(getActivity(), true);
                }
                PreferenceUtil.setAttendanceDate(getActivity(), date);
                PreferenceUtil.setAttendanceTime(getActivity(), time);
//                    }
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
            }
        }
        }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.view_feedback);
        item.setVisible(false);
    }
}
