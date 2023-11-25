package com.example.quizforkids.animalQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionManager;

import com.example.quizforkids.QuizContract;
import com.example.quizforkids.QuizDbHelper;
import com.example.quizforkids.R;
import com.example.quizforkids.UserContract;
import com.example.quizforkids.UserDbHelper;
import com.example.quizforkids.finishScreen.FinishScreen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FragmentPortraitAnimal extends Fragment {
    private int b = 0;
    private int currentQuestionIndex = 0;
    ImageView animal;
    ImageButton nextButton;
    ImageButton backButton;
    Button okButton;
    Button submit;
    EditText input;
    SharedPreferences sharedPreferences;
    QuizDbHelper dbHelper;
    UserDbHelper udbHelper;
    List<AnimalQuestion> cursor;
    int correct;
    private SQLiteDatabase mDatabase;
    private List<String> answerList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_animal_quiz, container, false);
        //inflate layout into the fragment -> fragment uses landscape_fragment

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        answerList.add(0, "");
        answerList.add(1, "");
        answerList.add(2, "");
        answerList.add(3, "");

        imageList.add(0, ".");
        imageList.add(1, ".");
        imageList.add(2, ".");
        imageList.add(3, ".");

        sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);


        submit = rootView.findViewById(R.id.quizButton2);
        input = rootView.findViewById(R.id.answerInput);
        okButton = rootView.findViewById(R.id.okButton);
        dbHelper = new QuizDbHelper(getActivity());
        udbHelper = new UserDbHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        boolean a = isDatabaseEmpty();

        if (a) {
            AnimalQuestions animalQuestions = new AnimalQuestions(getActivity());
            animalQuestions.addQuestions();
        }

        animal = rootView.findViewById(R.id.animalImage);
        nextButton = rootView.findViewById(R.id.next_button);
        backButton = rootView.findViewById(R.id.back_button);
        displayQuestionNext();

        backButton.setOnClickListener(view -> {

            if (currentQuestionIndex == 0) {
                return;
            }
            currentQuestionIndex -= 1;
            displayQuestionBack();
            input.setText(answerList.get(currentQuestionIndex));

        });

        nextButton.setOnClickListener(view -> {
            if (currentQuestionIndex == 3) {
                return;
            }
            currentQuestionIndex += 1;
            displayQuestionNext();
            input.setText(answerList.get(currentQuestionIndex));

        });

        okButton.setOnClickListener(view -> checkMethod());
        submit.setOnClickListener(view -> results());


        return rootView;
    }

    public void checkMethod() {
        String newInput = input.getText().toString();
        answerList.remove(currentQuestionIndex);
        answerList.add(currentQuestionIndex, newInput);

    }

    public boolean isDatabaseEmpty() {
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(*) FROM " + QuizContract.QuizEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }


    private void displayQuestionNext() {
        dbHelper = new QuizDbHelper(getActivity());

        if (b == 0) {
            cursor = dbHelper.getQuestions();
            b = 1;
        }

        int numQuestions = cursor.size();
        if (currentQuestionIndex < numQuestions) {
            AnimalQuestion question = cursor.get(currentQuestionIndex);
            // Display the question image
            String image = question.getImage();

            String answer = question.getAnswer();
            imageList.remove(currentQuestionIndex);
            imageList.add(currentQuestionIndex, answer);

            int resId = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            //animal.setImageResource(resId);
            int imageViewWidth = animal.getWidth();

            animal.animate()
                    .translationXBy(-imageViewWidth)
                    .setDuration(500)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Load the new photo into the ImageView
                            animal.setImageResource(resId);

                            // Reset the translation
                            animal.setTranslationX(imageViewWidth);

                            // Slide the new photo in from the right
                            animal.animate()
                                    .translationXBy(-imageViewWidth)
                                    .setDuration(500)
                                    .start();
                        }
                    })
                    .start();

        }
    }

    private void displayQuestionBack() {
        dbHelper = new QuizDbHelper(getActivity());

        if (b == 0) {
            cursor = dbHelper.getQuestions();
            b = 1;
        }

        int numQuestions = cursor.size();
        if (currentQuestionIndex < numQuestions) {
            AnimalQuestion question = cursor.get(currentQuestionIndex);
            // Display the question image
            String image = question.getImage();

            String answer = question.getAnswer();
            imageList.remove(currentQuestionIndex);
            imageList.add(currentQuestionIndex, answer);

            int resId = getResources().getIdentifier(image, "drawable", getContext().getPackageName());
            //animal.setImageResource(resId);
            int imageViewWidth = animal.getWidth();

            animal.animate()
                    .translationXBy(imageViewWidth)
                    .setDuration(500)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Load the new photo into the ImageView
                            animal.setImageResource(resId);

                            // Reset the translation
                            animal.setTranslationX(-imageViewWidth);

                            // Slide the new photo in from the right
                            animal.animate()
                                    .translationXBy(imageViewWidth)
                                    .setDuration(500)
                                    .start();
                        }
                    })
                    .start();
        }
    }

    private void addAnswer(){

        correct = 0;

        for (int i = 0; i < 4; i++) {
            if (answerList.get(i).equals(imageList.get(i))) {
                correct++;
            }
        }

        SQLiteDatabase mDatabase;
        UserDbHelper dbHelper = new UserDbHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault());
        String formattedDate = df.format(c);


        int wrong = 4 - correct;
        int total = correct *3 - wrong;

        ContentValues values = new ContentValues();
        // populate the database with animal quiz questions and answers
        values.put(UserContract.ScoreTable.COLUMN_USERNAME, user);
        values.put(UserContract.ScoreTable.COLUMN_SCORE, total);
        values.put(UserContract.ScoreTable.COLUMN_CATEGORY,"Animal");
        values.put(UserContract.ScoreTable.COLUMN_DATE,formattedDate);
        mDatabase.insert(UserContract.ScoreTable.TABLE_NAME, null, values);
    }

    private void results() {
    addAnswer();
        Intent i = new Intent(getActivity(), FinishScreen.class);
        i.putExtra("score", correct);
        i.putExtra(FinishScreen.EXTRA_MESSAGE, "Animal");
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);
    }
}
