package com.algosoft.gov.school.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.algosoft.gov.school.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookFragment extends Fragment {
    WebView facebook;


    public FacebookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_facebook, container, false);
        facebook=(WebView)v.findViewById(R.id.WebView_Facebook);
        facebook.getSettings().setJavaScriptEnabled(true);
        facebook.setWebViewClient(new WebViewClient());
        facebook.loadUrl("https://www.facebook.com/");

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.view_feedback);
        item.setVisible(false);
    }

}
