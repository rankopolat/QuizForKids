package com.example.quizforkids.registerPage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.quizforkids.UserContract;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.loginPage.LoginScreen;

public class FragmentPortraitRegister extends Fragment {
    View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // onCreateView() is called when the fragment’s layout is needed
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_register_screen, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button registerUser = rootView.findViewById(R.id.createButton);
        registerUser.setOnClickListener(view -> registerUser());

        return rootView;
    }
    public void registerUser() {

        EditText passwordRepeat = rootView.findViewById(R.id.password2);
        EditText EmailEditText = rootView.findViewById(R.id.emailAddress);
        EditText UsernameEditText = rootView.findViewById(R.id.userName);
        EditText PasswordEditText = rootView.findViewById(R.id.password1);

        UserDbHelper dbHelper = new UserDbHelper(getActivity());
        SQLiteDatabase mDatabase = dbHelper.getWritableDatabase();
        String email = EmailEditText.getText().toString().trim();
        String username = UsernameEditText.getText().toString().trim();
        String password = PasswordEditText.getText().toString().trim();
        String password2 = passwordRepeat.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            EmailEditText.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            UsernameEditText.setError("Username is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            PasswordEditText.setError("Password is required");
            return;
        }

        ContentValues values = new ContentValues();

        if(password.equals(password2)) {

            values.put(UserContract.UserTable.COLUMN_EMAIL, email);
            values.put(UserContract.UserTable.COLUMN_USERNAME, username);
            values.put(UserContract.UserTable.COLUMN_PASSWORD, password);
        }
        else{
            passwordRepeat.setError("Password is not the same");
            return;
        }

        long newRowId = mDatabase.insert(UserContract.UserTable.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getActivity(), "User registered successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginScreen.class));
            getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);

        }
    }
}
