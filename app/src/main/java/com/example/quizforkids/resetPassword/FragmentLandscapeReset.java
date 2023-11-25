package com.example.quizforkids.resetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.quizforkids.R;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.loginPage.LoginScreen;

public class FragmentLandscapeReset extends Fragment {
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // onCreateView() is called when the fragment’s layout is needed
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.landscape_reset, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button resetPass = rootView.findViewById(R.id.createButton);
        resetPass.setOnClickListener(view -> resetPassword());

        return rootView;
    }

    public void resetPassword() {

        EditText userName = rootView.findViewById(R.id.userName);
        EditText email = rootView.findViewById(R.id.emailAddress);
        EditText passwords = rootView.findViewById(R.id.password1);
        EditText password2 = rootView.findViewById(R.id.password2);
        UserDbHelper UserDbHelper = new UserDbHelper(getContext());

        String username = userName.getText().toString();
        String emailadd = email.getText().toString();
        String password = passwords.getText().toString();
        String passwordTwo = password2.getText().toString();

        if (TextUtils.isEmpty(emailadd)) {
            email.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            userName.setError("Username is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwords.setError("Password is required");
            return;
        }

        if (password.equals(passwordTwo)) {

            if (UserDbHelper.checkUserAccount(username, emailadd)) {

                UserDbHelper.updatePassword(username, password);
                Intent a = new Intent(getActivity(), LoginScreen.class);
                startActivity(a);
                getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);

            }
            else {
                Toast.makeText(getActivity(), "Invalid username or email", Toast.LENGTH_SHORT).show();
            }

        } else {
            password2.setError("Password is not the same");
        }
    }
}
