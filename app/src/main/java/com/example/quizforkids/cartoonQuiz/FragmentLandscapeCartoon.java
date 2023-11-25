package com.example.quizforkids.cartoonQuiz;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

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

public class FragmentLandscapeCartoon extends Fragment {
    int b = 0;
    int currentQuestionIndex = 0;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    ImageButton nextButton1;
    ImageButton backButton2;
    Button submitButton;
    ImageView cartoon;
    QuizDbHelper dbHelper;
    RadioGroup radGroup;
    UserDbHelper udbHelper;
    int score = 0;
    List<cartoonQuestion> cursor = new ArrayList<>();
    List<Integer> rbutton = new ArrayList<>();
    List<String> imageList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.landscape_cartoon_quiz, container, false);

        score = 0;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageList.add(0, ".");
        imageList.add(1, ".");
        imageList.add(2, ".");
        imageList.add(3, ".");

        rbutton.add(0, -1);
        rbutton.add(1, -1);
        rbutton.add(2, -1);
        rbutton.add(3, -1);

        option1 = rootView.findViewById(R.id.select1);
        option2 = rootView.findViewById(R.id.select2);
        option3 = rootView.findViewById(R.id.select3);
        cartoon = rootView.findViewById(R.id.cartoonImage);
        submitButton = rootView.findViewById(R.id.quizButton3);
        submitButton.setOnClickListener(view -> results());

        radGroup = rootView.findViewById(R.id.RGroup);
        dbHelper = new QuizDbHelper(getActivity());
        udbHelper = new UserDbHelper(getActivity());
        SQLiteDatabase mDatabase = dbHelper.getWritableDatabase();

        String count = "SELECT count(*) FROM cartoon";
        Cursor mcursor = mDatabase.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);

        if (icount > 0) {

            displayQuestionForward();
        } else {
            cartoonQuestions cartoonQuestions = new cartoonQuestions(getActivity());
            cartoonQuestions.addQuestions();
            displayQuestionForward();
        }

        nextButton1 = rootView.findViewById(R.id.next_button2);
        backButton2 = rootView.findViewById(R.id.back_button2);


        nextButton1.setOnClickListener(view -> {
            checker();
        });

        backButton2.setOnClickListener(view -> {

            if (currentQuestionIndex == 0) {
                return;
            }
            currentQuestionIndex -= 1;
            displayQuestionBack();

            if (rbutton.get(currentQuestionIndex) == 0) {
                radGroup.check(R.id.select1);
            } else if (rbutton.get(currentQuestionIndex) == 1) {
                radGroup.check(R.id.select2);
            } else if (rbutton.get(currentQuestionIndex) == 2) {
                radGroup.check(R.id.select3);
            }
        });
        return rootView;
    }

    private void checker() {


        if (option1.isChecked()) {

            rbutton.remove(currentQuestionIndex);
            rbutton.add(currentQuestionIndex, 0);
            imageList.remove(currentQuestionIndex);
            imageList.add(currentQuestionIndex, option1.getText().toString());

        } else if (option2.isChecked()) {

            rbutton.remove(currentQuestionIndex);
            rbutton.add(currentQuestionIndex, 1);
            imageList.remove(currentQuestionIndex);
            imageList.add(currentQuestionIndex, option2.getText().toString());

        } else if (option3.isChecked()) {

            rbutton.remove(currentQuestionIndex);
            rbutton.add(currentQuestionIndex, 2);
            imageList.remove(currentQuestionIndex);
            imageList.add(currentQuestionIndex, option3.getText().toString());

        }

        if (currentQuestionIndex != 3) {
            currentQuestionIndex += 1;
        } else {
            return;
        }

        if (rbutton.get(1) == -1) {
            radGroup.clearCheck();
        } else if (rbutton.get(2) == -1) {
            radGroup.clearCheck();
        } else if (rbutton.get(3) == -1) {
            radGroup.clearCheck();
        }

        if (rbutton.get(currentQuestionIndex) == 0) {
            radGroup.check(R.id.select1);
        } else if (rbutton.get(currentQuestionIndex) == 1) {
            radGroup.check(R.id.select2);
        } else if (rbutton.get(currentQuestionIndex) == 2) {
            radGroup.check(R.id.select3);
        }

        displayQuestionForward();
    }

    private void displayQuestionForward() {

        if (b == 0) {
            cursor = dbHelper.getCartoonQuestions();
            b = 1;
        }

        int numQuestions = cursor.size();
        if (currentQuestionIndex < numQuestions) {
            cartoonQuestion question = cursor.get(currentQuestionIndex);

            // Display the question image
            int resId = getResources().getIdentifier(question.getImage(), "drawable", getContext().getPackageName());
            int imageViewWidth = cartoon.getWidth();

            cartoon.animate()
                    .translationXBy(-imageViewWidth)
                    .setDuration(500)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Load the new photo into the ImageView
                            cartoon.setImageResource(resId);

                            // Reset the translation
                            cartoon.setTranslationX(imageViewWidth);

                            // Slide the new photo in from the right
                            cartoon.animate()
                                    .translationXBy(-imageViewWidth)
                                    .setDuration(500)
                                    .start();
                        }
                    })
                    .start();


            option1.setText(question.getOption1());
            animateRadioForward(option1);
            option2.setText(question.getOption2());
            animateRadioForward(option2);
            option3.setText(question.getOption3());
            animateRadioForward(option3);


        }
    }

    private void addAnswer() {

        score = 0;
        if (cursor.get(0).getAnswer().equals(imageList.get(0))) {
            score += 1;
        }
        if (cursor.get(1).getAnswer().equals(imageList.get(1))) {
            score += 1;
        }
        if (cursor.get(2).getAnswer().equals(imageList.get(2))) {
            score += 1;
        }
        if (cursor.get(3).getAnswer().equals(imageList.get(3))) {
            score += 1;
        }

        SQLiteDatabase mDatabase;
        UserDbHelper dbHelper = new UserDbHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserName", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username", "");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault());
        String formattedDate = df.format(c);


        int wrong = 4 - score;
        int total = score * 3 - wrong;

        ContentValues values = new ContentValues();
        // populate the database with animal quiz questions and answers
        values.put(UserContract.ScoreTable.COLUMN_USERNAME, user);
        values.put(UserContract.ScoreTable.COLUMN_SCORE, total);
        values.put(UserContract.ScoreTable.COLUMN_CATEGORY, "Cartoon");
        values.put(UserContract.ScoreTable.COLUMN_DATE, formattedDate);
        mDatabase.insert(UserContract.ScoreTable.TABLE_NAME, null, values);
    }

    private void displayQuestionBack() {


        int numQuestions = cursor.size();
        if (currentQuestionIndex < numQuestions) {
            cartoonQuestion question = cursor.get(currentQuestionIndex);

            // Display the question image
            int resId = getResources().getIdentifier(question.getImage(), "drawable", getContext().getPackageName());
            int imageViewWidth = cartoon.getWidth();

            cartoon.animate()
                    .translationXBy(imageViewWidth)
                    .setDuration(500)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Load the new photo into the ImageView
                            cartoon.setImageResource(resId);

                            // Reset the translation
                            cartoon.setTranslationX(-imageViewWidth);

                            // Slide the new photo in from the right
                            cartoon.animate()
                                    .translationXBy(imageViewWidth)
                                    .setDuration(500)
                                    .start();
                        }
                    })
                    .start();


            option1.setText(question.getOption1());
            animateRadioBack(option1);
            option2.setText(question.getOption2());
            animateRadioBack(option2);
            option3.setText(question.getOption3());
            animateRadioBack(option3);


        }
    }

    public void animateRadioForward(RadioButton a) {
        int textViewWidth = a.getWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(a, "translationX", textViewWidth, 0);
        animator.setDuration(1000); // Set the duration of the animation
        animator.start();
    }

    public void animateRadioBack(RadioButton a) {
        int textViewWidth = a.getWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(a, "translationX", -textViewWidth, 0);
        animator.setDuration(1000); // Set the duration of the animation
        animator.start();
    }


    private void results() {

        addAnswer();
        Intent i = new Intent(getActivity(), FinishScreen.class);
        i.putExtra("score", score);
        i.putExtra(FinishScreen.EXTRA_MESSAGE, "Cartoon");
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.slide_out, R.anim.slid_in);


    }
}
