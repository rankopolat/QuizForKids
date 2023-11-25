package com.example.quizforkids.cartoonQuiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.quizforkids.R;

public class cartoonQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_quiz);

        setupFrag();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu - adds items to the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help) {
            alertBuilderHelp();
        }
        return super.onOptionsItemSelected(item);
    }

    public void alertBuilderHelp(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Instructions");
        alertDialog.setMessage("Press your guess for each of the 4 images from the 3 options \n\nPress the right arrow when you have inputted your final guess for each image to save your answer\n\nThe left arrow will take you" +
                " back a question the right arrow will will take you to the next question\n\nAfter you have confirmed all your answers press submit to finish your quiz");

        alertDialog.setNegativeButton("Ok", (dialogInterface, i) -> dialogInterface.cancel());
        alertDialog.show();
    }


    private void setupFrag(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //start of the fragment transaction
        //specify changes to be done
        Configuration configInfo = getResources().getConfiguration();
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentLandscapeCartoon fragmentLandscape = new FragmentLandscapeCartoon();
            fragmentTransaction.replace(android.R.id.content, fragmentLandscape );
            //android.R.id.content - Activity's root view container
        }else {
            FragmentPortraitCartoon fragmentPortrait = new FragmentPortraitCartoon();
            fragmentTransaction.replace(android.R.id.content, fragmentPortrait);
        }
        fragmentTransaction.commit();
    }


}