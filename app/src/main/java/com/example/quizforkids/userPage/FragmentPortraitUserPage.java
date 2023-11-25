package com.example.quizforkids.userPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.quizforkids.R;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.previousQuestions.previousQuestions;
import com.example.quizforkids.quizSelector.QuizSelection;

public class FragmentPortraitUserPage extends Fragment {
    View rootView;
    SharedPreferences sharedPreferences;
    ImageButton quizButton;
    Button prevButton;
    TextView username;
    TextView scores;

    // onCreateView() is called when the fragment’s layout is needed
    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_user_page, container, false);
        sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        username = rootView.findViewById(R.id.usernameID);
        quizButton = rootView.findViewById(R.id.quizButton);
        prevButton = rootView.findViewById(R.id.prevButton);
        scores = rootView.findViewById(R.id.score);

        UserDbHelper dbHelper = new UserDbHelper(getActivity());
        int total = dbHelper.returnScore(user);

        scores.setText("" + total);
        username.setText("User: " + user);

        quizButton.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), QuizSelection.class);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });

        prevButton.setOnClickListener(view -> {
            Intent a = new Intent(getActivity(), previousQuestions.class);
            startActivity(a);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });

        return rootView;
    }



}
