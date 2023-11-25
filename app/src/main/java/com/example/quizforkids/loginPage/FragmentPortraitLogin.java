package com.example.quizforkids.loginPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.example.quizforkids.R;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.userPage.UserPage;
import com.example.quizforkids.resetPassword.resetPassword;


public class FragmentPortraitLogin extends Fragment {

    View rootView;


    // onCreateView() is called when the fragment’s layout is needed
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

            rootView = inflater.inflate(R.layout.landscape_login, container, false);
            //inflate layout into the fragment -> fragment uses landscape_fragment

             Toolbar toolbar = rootView.findViewById(R.id.toolbar);
             toolbar.setTitle("");
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Button LoginButton = rootView.findViewById(R.id.loginButtons);
            LoginButton.setOnClickListener(view -> loginAttempt());

            Button resetButton = rootView.findViewById(R.id.button2);
            resetButton.setOnClickListener(view -> {
                Intent a = new Intent(getActivity(),resetPassword.class);
                startActivity(a);
                getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);

            });
            return rootView;
        }


        private void loginAttempt(){
            EditText userName = rootView.findViewById(R.id.usernameInput);
            EditText password = rootView.findViewById(R.id.passInput);
            UserDbHelper UserDbHelper = new UserDbHelper(getActivity());

            String username = userName.getText().toString();
            String password1 = password.getText().toString();

            SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",username);
            editor.apply();

            if (UserDbHelper.checkUserCredentials(username, password1)) {
                Intent intent = new Intent(getActivity(), UserPage.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);

            } else {
                Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

