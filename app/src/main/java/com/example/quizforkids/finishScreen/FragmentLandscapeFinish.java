package com.example.quizforkids.finishScreen;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.quizforkids.R;
import com.example.quizforkids.UserContract;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.userPage.UserPage;

public class FragmentLandscapeFinish extends Fragment {

    View rootView;
    public static final String EXTRA_MESSAGE = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        rootView = inflater.inflate(R.layout.landscape_finish, container, false);
        //inflate layout into the fragment -> fragment uses landscape_fragment

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");

        String category = getActivity().getIntent().getStringExtra(EXTRA_MESSAGE);

        TextView currentscore = rootView.findViewById(R.id.quizScore);
        Button home = rootView.findViewById(R.id.homeButton);


        int scores = getActivity().getIntent().getIntExtra("score",0);
        int wrong = 4 - scores;
        int total = scores *3 - wrong;

        SQLiteDatabase mDatabase;
        UserDbHelper dbHelper = new UserDbHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        int overall = dbHelper.returnScore(user);
        currentscore.setText("Well done "+user+"\n for completing the " +category+" quiz\n with " +wrong +" incorrect\n and " + scores
                +" correct \nfor a total of " + total + " points\n for this attempt \n\n Overall you have: \n " + overall +" points");

        home.setOnClickListener(view -> {

            Intent a = new Intent(getActivity(), UserPage.class);
            startActivity(a);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });



        return rootView;
    }
}
