package com.example.quizforkids.quizSelector;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.quizforkids.R;
import com.example.quizforkids.animalQuiz.animalQuiz;
import com.example.quizforkids.cartoonQuiz.cartoonQuiz;

public class FragmentPortraitQuiz extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_quiz_selection, container, false);
        //inflate layout into the fragment -> fragment uses landscape_fragment

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button cartoonButton = rootView.findViewById(R.id.cartoonButton);
        Button animalButton = rootView.findViewById(R.id.animalButton);

        cartoonButton.setOnClickListener(view -> {
            Intent a = new Intent(getActivity(), cartoonQuiz.class);
            startActivity(a);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });
        animalButton.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), animalQuiz.class);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);

        });

        return rootView;
    }
}
