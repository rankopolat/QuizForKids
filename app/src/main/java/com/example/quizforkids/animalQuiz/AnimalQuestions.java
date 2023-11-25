package com.example.quizforkids.animalQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizforkids.QuizContract;
import com.example.quizforkids.QuizDbHelper;

public class AnimalQuestions {

    private QuizDbHelper dbHelper;

    public AnimalQuestions(Context context) {
        dbHelper = new QuizDbHelper(context);
    }
    public void addQuestions() {

        SQLiteDatabase mDatabase;
        mDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // populate the database with animal quiz questions and answers
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "lion");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "lion");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "elephant");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "elephant");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "giraffe");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "giraffe");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "hippo");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "hippo");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);


        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "cat");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "cat");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "dog");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "dog");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "kangaroo");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "kangaroo");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "snake");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "snake");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "cow");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "cow");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuizContract.QuizEntry.COLUMN_QUESTION, "What animal is this?");
        values.put(QuizContract.QuizEntry.COLUMN_ANSWER, "horse");
        values.put(QuizContract.QuizEntry.COLUMN_IMAGE, "horse");
        mDatabase.insert(QuizContract.QuizEntry.TABLE_NAME, null, values);

    }
}
