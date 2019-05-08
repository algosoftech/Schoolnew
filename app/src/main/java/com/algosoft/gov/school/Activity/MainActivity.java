package com.algosoft.gov.school.Activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.algosoft.gov.school.R;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        String android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("DeviceId",""+android_id);
//        Toast.makeText(this, "DeviceId"+android_id, Toast.LENGTH_SHORT).show();
        PreferenceUtil.setDeviceId(MainActivity.this,android_id);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if (!PreferenceUtil.getUserId(MainActivity.this).isEmpty()){
                        Intent intent=new Intent(MainActivity.this,Home.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };thread.start();
    }
}
