package com.example.quizforkids.frontPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.quizforkids.loginPage.LoginScreen;
import com.example.quizforkids.R;
import com.example.quizforkids.registerPage.RegisterScreen;

public class FragmentPortrait extends Fragment {


    // onCreateView() is called when the fragment’s layout is needed
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        //inflate layout into the fragment -> fragment uses landscape_fragment



        Button login = rootView.findViewById(R.id.loginButton);
        Button register = rootView.findViewById(R.id.registerButton);

        login.setOnClickListener(view -> {
            Intent a = new Intent(getActivity(), LoginScreen.class);
            startActivity(a);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });
        register.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), RegisterScreen.class);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
        });

        return rootView;
    }



}

