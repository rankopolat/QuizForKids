package com.example.quizforkids.cartoonQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizforkids.QuizContract;
import com.example.quizforkids.QuizDbHelper;

public class cartoonQuestions {
    private QuizDbHelper dbHelper;

    public cartoonQuestions(Context context) {
        dbHelper = new QuizDbHelper(context);
    }
    public void addQuestions() {

        SQLiteDatabase mDatabase;
        mDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // populate the database with cartoon quiz questions and answers
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Joker");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Robin");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Batman");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Batman");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "batman");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Aunt May");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Spiderman");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Gwen Stacy");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Spiderman");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "spider");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Carl Tennyson");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Gwen Tennyson");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Ben 10");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Ben 10");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "ben");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Tony Stark");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Justin Hammer");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "James Rhodes");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Tony Stark");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "iron");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Spongebob Squarepants");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Patrick");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Sandy Cheeks");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Spongebob Squarepants");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "sponge");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Bart Simpson");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Lisa Simpson");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Homer Simpson");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Homer Simpson");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "simp");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Bender");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Philip J. Fry");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Amy Wong");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Philip J. Fry");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "futur");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Arthur Read");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Binky Barnes");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Muffy Crosswire");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Arthur Read");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "arthur");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Zuko");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Katara");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Aang");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Aang");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "ava");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION1, "Sasuke");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION2, "Naruto");
        values.put(QuizContract.cartoonEntry.COLUMN_OPTION3, "Sakura");
        values.put(QuizContract.cartoonEntry.COLUMN_ANSWER, "Naruto");
        values.put(QuizContract.cartoonEntry.COLUMN_IMAGE, "naruto");
        mDatabase.insert(QuizContract.cartoonEntry.TABLE_NAME, null, values);


    }


}

