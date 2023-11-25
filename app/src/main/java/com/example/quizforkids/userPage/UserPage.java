package com.example.quizforkids.userPage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.quizforkids.R;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.frontPage.MainActivity;


public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        setupFrag();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                alertBuilder();
                break;
                
            case R.id.help:

                alertBuilderHelp();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        Intent logout = new Intent(this, MainActivity.class);
        startActivity(logout);
    }
    
    public void alertBuilder(){


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        String user = getSharedPreferences("UserName", Context.MODE_PRIVATE).getString("username","");

        UserDbHelper dbHelper = new UserDbHelper(this);
        int total = dbHelper.returnScore(user);

        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Your current overall score is " + total +"\nAre you sure you want to Logout ?");

        alertDialog.setPositiveButton("Logout", (dialogInterface, i) -> logout());

        alertDialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
        alertDialog.show();
    }

    public void alertBuilderHelp(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Help");
        alertDialog.setMessage("Click the Quiz Time image button to head to the quizzes \n\nClick previous attempts to display all of your past scores and dates");

        alertDialog.setNegativeButton("Ok", (dialogInterface, i) -> dialogInterface.cancel());
        alertDialog.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu - adds items to the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupFrag(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //start of the fragment transaction
        //specify changes to be done
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscapeUserPage fragmentLandscape = new FragmentLandscapeUserPage();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape );
            //android.R.id.content - Activity's root view container
        }else {
            FragmentPortraitUserPage fragmentPortrait = new FragmentPortraitUserPage();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
        }
        fragmentTransaction.commit();
    }
}