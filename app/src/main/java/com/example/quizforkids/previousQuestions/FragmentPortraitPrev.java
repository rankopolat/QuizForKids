package com.example.quizforkids.previousQuestions;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.quizforkids.R;
import com.example.quizforkids.UserDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FragmentPortraitPrev extends Fragment {

    List<String> attempts = new ArrayList<>();
    private com.example.quizforkids.UserDbHelper UserDbHelper;
    LinearLayout linLayout;
    View rootView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_previous_questions, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UserDbHelper = new UserDbHelper(getActivity());
        linLayout = rootView.findViewById(R.id.linLay);


        String user = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE).getString("username","");

        attempts = UserDbHelper.prevAttemptsAll(user);

        populate();

        Spinner spin = rootView.findViewById(R.id.sort_spinner);
        String[] sorted = new String[]{"Select All","Sort By Animal","Sort By Cartoon","Sort By Date DESC"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sorted);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String option = adapterView.getItemAtPosition(i).toString();

                switch (option) {
                    case "Sort By Animal":
                        attempts = UserDbHelper.prevAttemptsCategory(user, "Animal");
                        populate();

                        break;
                    case "Sort By Date DESC":
                        attempts = UserDbHelper.prevAttemptsDate(user);
                        populate();

                        break;
                    case "Sort By Cartoon":
                        attempts = UserDbHelper.prevAttemptsCategory(user, "Cartoon");
                        populate();

                        break;
                    default:
                        attempts = UserDbHelper.prevAttemptsAll(user);
                        populate();

                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }

    public void populate(){
        linLayout.removeAllViews();
        for(String a: attempts){
            TextView textview = new TextView(getActivity());
            String cata = a.split(" ",2)[0];
            textview.setText(a);
            textview.setTextSize(20);
            if(cata.equals("'Cartoon'")){
            textview.setBackgroundColor(Color.CYAN);}
            else{
                textview.setBackgroundColor(Color.rgb(255,87,34));
            }
           // textview.setBackgroundResource(R.drawable.rounded_text);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0, 0, 0, 25); // left, top, right, bottom
            textview.setLayoutParams(params);
            linLayout.addView(textview);}


    }

}
