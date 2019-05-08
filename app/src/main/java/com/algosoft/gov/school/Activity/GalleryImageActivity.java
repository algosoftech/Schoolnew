package com.algosoft.gov.school.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.algosoft.gov.school.Adapter.GalleryImageAdapter;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.response.GalleryImage;
import com.algosoft.gov.school.response.ResponseClass;
import com.algosoft.gov.school.services.WebServicesURL;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.NetworkStatus;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryImageActivity extends AppCompatActivity {
ImageView imageView;
    private ImageView img;
    private String branchCode,userId,boardId,type;
    private String galleryId;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        branchCode =  PreferenceUtil.getBranchCode(GalleryImageActivity.this);
        userId = PreferenceUtil.getUserId(GalleryImageActivity.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(GalleryImageActivity.this);
        type = PreferenceUtil.getSelectedTypeFromServer(GalleryImageActivity.this);
        img = (ImageView)findViewById(R.id.image_view);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_menu_camera);
        requestOptions.error(R.drawable.ic_menu_camera);
        String image = getIntent().getStringExtra("Image");
        galleryId = getIntent().getStringExtra("GalleryId");
//        Toast.makeText(this, ""+galleryId, Toast.LENGTH_SHORT).show();

        recyclerView=(RecyclerView)findViewById(R.id.RecyclerGalleryImage);
        recyclerView.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(GalleryImageActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(GalleryImageActivity.this, 4));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Glide.with(GalleryImageActivity.this)
                .setDefaultRequestOptions(requestOptions)
                .load(image).into(img);


        if (NetworkStatus.isNetworkAvailable(GalleryImageActivity.this)) {
            new GalleryDetailClass().execute(branchCode, type, userId, galleryId);
        }else{
            Toast.makeText(GalleryImageActivity.this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();

        }

    }

    public class GalleryDetailClass extends AsyncTask<String, String,JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {
            WebServicesURL webServicesURL=new WebServicesURL();
            List<NameValuePair> userData = new ArrayList<NameValuePair>();
            userData.add(new BasicNameValuePair("branchCode", params[0]));
            userData.add(new BasicNameValuePair("userType", params[1]));
            userData.add(new BasicNameValuePair("userId", params[2]));
            userData.add(new BasicNameValuePair("galleryId", params[3]));
            JSONObject jsonObject=webServicesURL.getGalleryDetail(userData);
            return jsonObject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            try
            {
//                progressBar.setVisibility(View.INVISIBLE);
                if (jsonObject!=null) {
                    Log.e("GalleryDetail", jsonObject.toString());
                    Gson gson = new Gson();
                    ResponseClass responseClass = gson.fromJson(jsonObject.toString(), ResponseClass.class);
                    if (responseClass.getSuccess() == 1) {
                        String galleryid = responseClass.getResult().getGalleryId();
                        String galleryyear = responseClass.getResult().getGalleryYear();
                        String galleryname = responseClass.getResult().getGalleryName();
                        ArrayList<GalleryImage> galleryimage = responseClass.getResult().getGalleryImage();
                        GalleryImageAdapter adapter = new GalleryImageAdapter(GalleryImageActivity.this, galleryimage,galleryid,galleryyear,galleryname);
                        recyclerView.setAdapter(adapter);

                    }else {
                        Log.e("LeaveList", "NUll");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
