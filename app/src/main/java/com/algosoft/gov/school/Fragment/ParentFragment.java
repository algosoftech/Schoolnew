package com.algosoft.gov.school.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algosoft.gov.school.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParentFragment extends Fragment {


    static View.OnClickListener myOnClickListener;

    public ParentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_parent, container, false);

//        recyclerView=(RecyclerView)view.findViewById(R.id.RecyclerParents);
//        recyclerView.setHasFixedSize(false);
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        adapter = new ParentsAdapter(ParentFragment.this,parentlist);
//        recyclerView.setAdapter(adapter);
        return view;
    }


}
