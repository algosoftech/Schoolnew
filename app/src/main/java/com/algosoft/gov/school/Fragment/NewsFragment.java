//package com.algosoft.gov.school.Fragment;
//
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//
//import com.algosoft.gov.school.Adapter.NewsAdapter;
//import com.algosoft.gov.school.R;
//import com.algosoft.gov.school.response.NewsDetail;
//import com.algosoft.gov.school.response.ResponseClass;
//import com.algosoft.gov.school.response.SectionDetail;
//import com.algosoft.gov.school.services.WebServicesURL;
//import com.algosoft.gov.school.storage.PreferenceUtil;
//import com.algosoft.gov.school.utility.NetworkStatus;
//import com.google.gson.Gson;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class NewsFragment extends Fragment {
//    private RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView recyclerView;
//    private ArrayList<String> News;
//    private String branchCode,userId,boardId,type;
//
//
//    public NewsFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_news, container, false);
//        setHasOptionsMenu(true);
//        branchCode =  PreferenceUtil.getBranchCode(getContext());
//        userId = PreferenceUtil.getUserId(getContext());
//        boardId = PreferenceUtil.getDefaultBoardIdFromServer(getContext());
//        type = PreferenceUtil.getSelectedTypeFromServer(getContext());
//
//        News=new ArrayList<>();
//        News.add("Upcoming Sports Events");
//        News.add("Upcoming Competitions");
//        News.add("Upcoming School Events");
//        recyclerView=(RecyclerView)view.findViewById(R.id.RecyclerNews);
//        recyclerView.setHasFixedSize(false);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//
//        if (NetworkStatus.isNetworkAvailable(getContext())) {
//            new NewsListClass().execute(branchCode,type,userId);
//        }else{
//            Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
//
//        }
//        return view;
//
//    }
//
//    public class NewsListClass extends AsyncTask<String, String,JSONObject>
//    {
//        @Override
//        protected JSONObject doInBackground(String... params) {
//            WebServicesURL webServicesURL=new WebServicesURL();
//            List<NameValuePair> userData = new ArrayList<NameValuePair>();
//            userData.add(new BasicNameValuePair("branchCode", params[0]));
//            userData.add(new BasicNameValuePair("userType", params[1]));
//            userData.add(new BasicNameValuePair("userId", params[2]));
//            JSONObject jsonObject=webServicesURL.getNewsList(userData);
//            return jsonObject;
//        }
//
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            super.onPostExecute(jsonObject);
//            try
//            {
////                progressBar.setVisibility(View.INVISIBLE);
//                if (jsonObject!=null) {
//                    Log.e("NewsList", jsonObject.toString());
//                    Gson gson = new Gson();
//                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
//                    if (responseClass.getSuccess() == 1) {
//                        ArrayList<NewsDetail> newslist = responseClass.getResult().getNewsDetailArrayList();
////                        Toast.makeText(Attendance.this, "sectionlist"+sectionlist, Toast.LENGTH_SHORT).show();
//                        PreferenceUtil.setNewsListFromServer(getContext(),gson.toJson(newslist));
//                        adapter=new NewsAdapter(NewsFragment.this,newslist);
//                        recyclerView.setAdapter(adapter);
//
//                    }else {
//                        Log.e("sectionlist", "NUll");
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item=menu.findItem(R.id.view_feedback);
//        item.setVisible(false);
//    }
//}
