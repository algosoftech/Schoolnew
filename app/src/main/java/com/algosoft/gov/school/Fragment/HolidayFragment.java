package com.algosoft.gov.school.Fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.EventDataList;
import com.algosoft.gov.school.response.ExaminationDetails;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DefaultDayViewAdapter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidayFragment extends Fragment {


    private ArrayList<EventDataList> calender_Lists;
    ArrayList<ExaminationDetails> examlist = new ArrayList<>();

    public HolidayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_holiday, container, false);



        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendarView = (CalendarPickerView)v.findViewById(R.id.calendar_view1);
//        Date today = new Date();
//        calendarView.init(today, nextYear.getTime()).withSelectedDate(today);

        calendarView.setCustomDayView(new DefaultDayViewAdapter());
        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < 5; i++) {
            today.add(Calendar.DAY_OF_MONTH, 3);
            dates.add(today.getTime());
        }
        calendarView.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendarView.init(new Date(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDates(dates);

        String calenderList =  PreferenceUtil.getCalenderListFromServer(getActivity());
        Gson gson = new Gson();
        calender_Lists = (ArrayList<EventDataList>) gson.fromJson(calenderList, new TypeToken<ArrayList<EventDataList>>() {
        }.getType());

//        for(int i=0;i<calender_Lists.size();i++){
//            String eventId = calender_Lists.get(i).getEventId();
//            String eventTitle =  calender_Lists.get(i).getEventTitle();
//            String eventVenue = calender_Lists.get(i).getEventVenue();
//            String eventMessage = calender_Lists.get(i).getEventMessage();
//            String eventAbout = calender_Lists.get(i).getEventAbout();
//            String eventType = calender_Lists.get(i).getEventType();
//            String eventStart = calender_Lists.get(i).getEventStart();
//            String eventEnd = calender_Lists.get(i).getEventEnd();
//
//            if(eventType.equalsIgnoreCase("Examination")){
//                ExaminationDetails examdetail = new ExaminationDetails();
//                examdetail.setEventId(eventId);
//                examdetail.setEvenntTitle(eventTitle);
//                examdetail.setEventVenue(eventVenue);
//                examdetail.setEvenntMessage(eventMessage);
//                examdetail.setEventAbout(eventAbout);
//                examdetail.setEventStart(eventStart);
//                examdetail.setEvenntEnd(eventEnd);
//                examlist.add(examdetail);
//            }
//        }
        return v;
    }

}
