package com.example.quizforkids.previousQuestions;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quizforkids.R;

public class previousQuestions extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_questions);

      setupFrag();

    }

    private void setupFrag(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //start of the fragment transaction
        //specify changes to be done
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscapePrev fragmentLandscape = new FragmentLandscapePrev();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape );
            //android.R.id.content - Activity's root view container
        }else {
            FragmentPortraitPrev fragmentPortrait = new FragmentPortraitPrev();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
        }

        fragmentTransaction.commit();
    }

}