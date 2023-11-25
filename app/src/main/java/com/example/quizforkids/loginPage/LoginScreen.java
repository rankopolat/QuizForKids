package com.example.quizforkids.loginPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.quizforkids.R;


public class LoginScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        setupFrag();

    }
   private void setupFrag(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //start of the fragment transaction
        //specify changes to be done
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscapeLogin fragmentLandscape = new FragmentLandscapeLogin();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape );
            //android.R.id.content - Activity's root view container
        }else {
            FragmentPortraitLogin fragmentPortrait = new FragmentPortraitLogin();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
        }

        fragmentTransaction.commit();
    }
}