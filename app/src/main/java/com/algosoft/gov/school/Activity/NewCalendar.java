package com.algosoft.gov.school.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.algosoft.gov.school.Adapter.NewCalendarAdapter;
import com.algosoft.gov.school.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewCalendar extends AppCompatActivity {
    private static final String TAG = "NewCalendar";
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForApi = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private CompactCalendarView compactCalendarView;
    private String currentMonth,currentDay,currentYear,changeMonth,mformat;
    public String[] currentArray;
    public String[] mutableArray;
    public String delimeter = "-";
    public String delimeter1= "/";
    List<String> mutableBookings;
    private ArrayList<Date> dateList;
    TextView textviewTitle,tvMonthTitle;
    private Date date1;
    List<Event> events;
    private RecyclerView recyclerView_calendar;
    private ArrayList<String> totalEventList=new ArrayList<String>();
    private ArrayList<String> EventDate=new ArrayList<String>();
    private NewCalendarAdapter adapter;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calendar);

        imageView=(ImageView)findViewById(R.id.Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        totalEventList.add("Mothers Day");
        totalEventList.add("Labour Day");
        totalEventList.add("Buddha purnima");
        totalEventList.add("Akshaya tritiya");

        EventDate.add("01/05/2019");
        EventDate.add("18/05/2019");
        EventDate.add("07/05/2019");


        tvMonthTitle=(TextView)findViewById(R.id.TextView_Month);
        recyclerView_calendar=(RecyclerView)findViewById(R.id.RecyclerView_Calendar);
        recyclerView_calendar.setHasFixedSize(false);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView_calendar.setLayoutManager(manager);
        adapter=new NewCalendarAdapter(this,totalEventList,EventDate);
        recyclerView_calendar.setAdapter(adapter);


        compactCalendarView=(CompactCalendarView)findViewById(R.id.calendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.displayOtherMonthDays(false);

        //totalEventList = new ArrayList<>();
        //monthlyEventList = new ArrayList<>();
       //commonList = new ArrayList<>();
        dateList = new ArrayList<>();
        mutableBookings = new ArrayList<>();
        events = new ArrayList<>();

        tvMonthTitle.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                tvMonthTitle.setText(dateFormatForMonth.format(dateClicked));
                List<Event> bookingsFromMap = compactCalendarView.getEvents(dateClicked);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                tvMonthTitle.setText(dateFormatForMonth.format(firstDayOfNewMonth));

                String scroldate = dateFormatForApi.format(firstDayOfNewMonth);

                currentArray = scroldate.split(delimeter);
                currentYear = currentArray[0];
                currentMonth = currentArray[1];
                currentDay = currentArray[2];

            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        tvMonthTitle.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
    }

    public long getTimeStampData(String mdate){
        // String str_date="21-03-2019";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(mdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " +date.getTime());
        return date.getTime();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
