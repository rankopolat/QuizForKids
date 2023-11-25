package com.example.quizforkids.quizSelector;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quizforkids.R;

public class QuizSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

         setupFrag();

    }

    private void setupFrag(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //start of the fragment transaction
        //specify changes to be done
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscapeQuiz fragmentLandscape = new FragmentLandscapeQuiz();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape );
            //android.R.id.content - Activity's root view container
        }else {
            FragmentPortraitQuiz fragmentPortrait = new FragmentPortraitQuiz();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
        }
        fragmentTransaction.commit();
    }
}