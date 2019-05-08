package com.algosoft.gov.school.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algosoft.gov.school.Adapter.NewParentAdapter;
import com.algosoft.gov.school.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewParentFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<String> parentlist;
    public NewParentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_parent, container, false);
        parentlist = new ArrayList<>();
        parentlist.add("Attendance");
        parentlist.add("Home Work");
        parentlist.add("Syllabus");
        parentlist.add("My Profile");
        parentlist.add("Time Table");
        parentlist.add("Notice Board");
        parentlist.add("Message");
        parentlist.add("Notification");
//        parentlist.add("Add Child");
        parentlist.add("My Calender");
        parentlist.add("Result");
        parentlist.add("School");
        parentlist.add("Fees");
        parentlist.add("Teachers");
        parentlist.add("Bus Route");

        recyclerView=(RecyclerView)view.findViewById(R.id.RecyclernewParents);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter=new NewParentAdapter(NewParentFragment.this,parentlist);
        recyclerView.setAdapter(adapter);
        return view;

    }

}
