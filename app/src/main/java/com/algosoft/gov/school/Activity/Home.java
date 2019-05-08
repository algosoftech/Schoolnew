package com.algosoft.gov.school.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.algosoft.gov.school.Fragment.FacebookFragment;
import com.algosoft.gov.school.Fragment.HomeFragment;
import com.algosoft.gov.school.Fragment.ImageFragment;
import com.algosoft.gov.school.Fragment.LeaveFragment;
import com.algosoft.gov.school.Fragment.TeacherAttendanceFragment;
import com.algosoft.gov.school.Fragment.MyProfileFragment;
import com.algosoft.gov.school.Fragment.NewParentFragment;
import com.algosoft.gov.school.Fragment.FeedbackFragment;
import com.algosoft.gov.school.Fragment.RateFragment;
import com.algosoft.gov.school.Fragment.UpdateFragment;
import com.algosoft.gov.school.R;
import com.algosoft.gov.school.storage.PreferenceUtil;
import com.algosoft.gov.school.utility.KeyGenerationClass;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static View.OnClickListener myOnClickListener;
    private String type;
    private String backStateName;
    private Object getStringExtra;
    private String branchCode, userId, boardId;
    public static ActionBarDrawerToggle toggle;
    FragmentTransaction fragmentTransaction;
    String currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        branchCode = PreferenceUtil.getBranchCode(Home.this);
        userId = PreferenceUtil.getUserId(Home.this);
        boardId = PreferenceUtil.getDefaultBoardIdFromServer(Home.this);
        Log.e("ApiKey",""+ KeyGenerationClass.getEncryptedKey()+"\n Type: "+PreferenceUtil.getSelectedTypeFromServer(this)
                +"\n userId: "+PreferenceUtil.getUserId(this)+"\n boardId: "+PreferenceUtil.getDefaultBoardIdFromServer(this)
        +"\n classId: "+PreferenceUtil.getClassListFromServer(this)+"\n sectionId:"+PreferenceUtil.getSectionListFromServer(this)
        +"\n subjectList: "+PreferenceUtil.getSubjectListFromServer(this));
        Intent intent = getIntent();
        type = intent.getStringExtra("TypeSelected");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myOnClickListener = new MyOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.Home);
        if(PreferenceUtil.getSelectedTypeFromServer(this).equalsIgnoreCase("Teacher")){
            backStateName = "HomeFragment";
            displaySelectedFragment(new HomeFragment(), backStateName);
        }else if(PreferenceUtil.getSelectedTypeFromServer(this).equalsIgnoreCase("Parents")){
            backStateName = "parentFragment";
            displaySelectedFragment(new NewParentFragment(), backStateName);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.view_feedback) {
            Intent i = new Intent(Home.this, ViewFeedbackActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.Home) {
            if (PreferenceUtil.getSelectedTypeFromServer(this).equalsIgnoreCase("Teacher")) {
                backStateName = "HomeFragment";
                displaySelectedFragment(new HomeFragment(), backStateName);
                setTitle(item.getTitle());
            } else if (PreferenceUtil.getSelectedTypeFromServer(this).equalsIgnoreCase("Parents")) {
                backStateName = "parentFragment";
                displaySelectedFragment(new NewParentFragment(), backStateName);
                setTitle(item.getTitle());
            }
        } else if (id == R.id.Gallery) {
            fragment = new ImageFragment();
            backStateName = "ImageFragment";
            displaySelectedFragment(new ImageFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.MyPofile) {
            backStateName = "Profile";
            displaySelectedFragment(new MyProfileFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.OwnAttendance) {
            backStateName = "Teacher Attendance";
            displaySelectedFragment(new TeacherAttendanceFragment(), backStateName);
            setTitle(item.getTitle());
         } else if (id == R.id.feedback) {
            backStateName = "Feedback";
            displaySelectedFragment(new FeedbackFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.Rate) {
            backStateName = "Rate";
            displaySelectedFragment(new RateFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.Like) {
            backStateName = "Like";
            displaySelectedFragment(new FacebookFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.Update) {
            backStateName = "UpdateFragment";
            displaySelectedFragment(new UpdateFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.Leave) {
            backStateName = "LeaveFragment";
            displaySelectedFragment(new LeaveFragment(), backStateName);
            setTitle(item.getTitle());
        } else if (id == R.id.Logout) {
            final Dialog dialog = new Dialog(Home.this);
            dialog.setContentView(R.layout.alertbox);
            dialog.setTitle("Do you want to exit");
            Button dialogButton_yes = (Button) dialog.findViewById(R.id.Button_Yes);
            Button dialogButton_no = (Button) dialog.findViewById(R.id.Button_No);
            dialogButton_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            // if button is clicked, close the custom dialog
            dialogButton_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceUtil.clearPreferenceObject(Home.this);
                    Intent i = new Intent(Home.this, Login.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            });
            dialog.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyOnClickListener implements View.OnClickListener {
        public MyOnClickListener(Home home) {
        }
        @Override
        public void onClick(View v) {
        }
    }

    private void displaySelectedFragment(Fragment fragment, String backstack_name) {
        currentFrag= backstack_name;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(currentFrag.equalsIgnoreCase("HomeFragment")){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if(!currentFrag.equalsIgnoreCase("HomeFragment")){
            backStateName = "HomeFragment";
            displaySelectedFragment(new HomeFragment(), backStateName);
            setTitle("School App");
        }
    }
}
