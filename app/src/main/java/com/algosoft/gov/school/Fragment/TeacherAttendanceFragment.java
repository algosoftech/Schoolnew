package com.algosoft.gov.school.Fragment;


import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.TeacherAttendanceAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GetTeacherAttendanceResponse;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TeacherAttendanceFragment extends Fragment {
    private TextView dateTo, dateFrom;
    private Boolean isDateToSelected = false, isDateFromSelected = false;
    private int year2, month2, date2, year1, month1, date1;
    Calendar calendar = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date_listner, date_listner2;
    String branchCode, userId, boardId, userType;
    TeacherAttendanceAdapter adapter;
    RecyclerView recyclerView;
    Boolean defaultDate = false;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeItem(R.id.view_feedback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_teacher_attendance, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rec_view_attendance);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        dateFrom = (TextView) view.findViewById(R.id.date_from);
        dateTo = (TextView) view.findViewById(R.id.date_to);
        branchCode = PreferenceUtil.getBranchCode(getActivity());
        userId = PreferenceUtil.getUserId(getActivity());
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(getActivity());
        userType = PreferenceUtil.getSelectedTypeFromServer(getActivity());
        if (dateFrom.getText().toString().isEmpty() && dateTo.getText().toString().isEmpty()) {
            getDefaultDate();
        }
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromDate();
            }
        });
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToDate();
            }
        });
        date_listner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                year1 = year;
                month1 = month;
                date1 = dayOfMonth;
                dateFrom.setText(year + "-" + (month < 10 ? ("0" + month) : (month)) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth)));
                calendar.set(view.getYear(), view.getMonth(), view.getDayOfMonth());

                isDateFromSelected = true;
                if (dateTo.getText().toString().length() > 0) {
                    String fromDate = dateFrom.getText().toString();
                    String toDate = dateTo.getText().toString();
                    if (NetworkStatus.isNetworkAvailable(getActivity())) {
                        new GetTeacherAttendance().execute(branchCode, userType, userId, boardId, fromDate, toDate);
                    }else{
                        Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        date_listner2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                year2 = year;
                month2 = month;
                date2 = dayOfMonth;
                dateTo.setText(year + "-" + (month < 10 ? ("0" + month) : (month)) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth)));
                calendar2.set(view.getYear(), view.getMonth(), view.getDayOfMonth());
                isDateToSelected = true;
                if (dateFrom.getText().toString().length() > 0) {
                    String fromDate = dateFrom.getText().toString();
                    String toDate = dateTo.getText().toString();
                    if (NetworkStatus.isNetworkAvailable(getActivity())) {
                        new GetTeacherAttendance().execute(branchCode, userType, userId, boardId, fromDate, toDate);
                    }else{
                        Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        return view;
    }

    private void getDefaultDate() {
        defaultDate = true;
        String date[] = KeyGenerationClass.getDate().split("\\-+");
        String from = String.valueOf(Integer.parseInt(date[0])) + "-0" + String.valueOf(Integer.parseInt(date[1])) + "-01";
        Log.e("new date", "" + from);
        dateFrom.setText(from);
        dateTo.setText(KeyGenerationClass.getDate());
        if (NetworkStatus.isNetworkAvailable(getActivity())) {
            new GetTeacherAttendance().execute(branchCode, userType, userId, boardId, from, KeyGenerationClass.getDate());
        }else{
            Toast.makeText(getActivity(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getToDate() {
        if (isDateFromSelected) {
            String startDate = dateFrom.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(startDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                long millis = date.getTime();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date_listner2, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(millis);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        } else {
            Calendar maxcal = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            maxcal.set(Calendar.MONTH, mm);
            maxcal.set(Calendar.DAY_OF_MONTH, dd);
            maxcal.set(Calendar.YEAR, yy - 1);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date_listner2, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(maxcal.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }

    private void getFromDate() {
        Calendar mincal = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        mincal.set(Calendar.MONTH, mm);
        mincal.set(Calendar.DAY_OF_MONTH, dd);
        mincal.set(Calendar.YEAR, yy - 1);
        if (isDateToSelected ) {
            String toDate = dateTo.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                long millis = date.getTime();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date_listner, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(millis);
                datePickerDialog.getDatePicker().setMinDate(mincal.getTimeInMillis());
                if (defaultDate) {
                    datePickerDialog.getDatePicker().updateDate(yy, mm, 01);
                    defaultDate= false;
                }
                datePickerDialog.show();
            }
        }
        else {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date_listner, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(mincal.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            if (defaultDate) {
                datePickerDialog.getDatePicker().updateDate(yy, mm, 01);
                defaultDate= false;
            }
            datePickerDialog.show();
        }
    }

    public class GetTeacherAttendance extends AsyncTask<String, String, JSONObject> {
        ArrayList<GetTeacherAttendanceResponse.Result.AttendanceData> attendanceDetails;

        @Override
        protected JSONObject doInBackground(String... strings) {
            WebServicesURL webServicesURL = new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", strings[0]));
            userData.add(new BasicNameValuePair("userType", strings[1]));
            userData.add(new BasicNameValuePair("userId", strings[2]));
            userData.add(new BasicNameValuePair("boardId", strings[3]));
            userData.add(new BasicNameValuePair("fromDate", strings[4]));
            userData.add(new BasicNameValuePair("toDate", strings[5]));
            JSONObject jsonObject = webServicesURL.getTeacherAttendance(userData);

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                try {
                    Toast.makeText(getContext(), "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    Log.e("Result ", "" + jsonObject);
                    Gson gson = new Gson();
                    GetTeacherAttendanceResponse attendanceResponse = gson.fromJson(jsonObject.toString(), GetTeacherAttendanceResponse.class);
                    if (attendanceResponse.getSuccess() == 1) {
                        if (attendanceResponse.getResult() != null) {
                            attendanceDetails = new ArrayList<GetTeacherAttendanceResponse.Result.AttendanceData>();
                            attendanceDetails = (ArrayList<GetTeacherAttendanceResponse.Result.AttendanceData>) attendanceResponse.getResult().getAttendanceData();
                            adapter = new TeacherAttendanceAdapter(getActivity(), attendanceDetails);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
