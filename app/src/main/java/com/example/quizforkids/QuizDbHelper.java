package com.example.quizforkids;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizforkids.animalQuiz.AnimalQuestion;
import com.example.quizforkids.cartoonQuiz.cartoonQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_QUIZ_TABLE = "CREATE TABLE " + QuizContract.QuizEntry.TABLE_NAME + " (" +
                QuizContract.QuizEntry.COLUMN_QUESTION + " TEXT NOT NULL," +
                QuizContract.QuizEntry.COLUMN_IMAGE + " TEXT NOT NULL UNIQUE," +
                QuizContract.QuizEntry.COLUMN_ANSWER + " TEXT NOT NULL )";

        db.execSQL(SQL_CREATE_QUIZ_TABLE);

        String SQL_CREATE_CARTOONQUIZ_TABLE = "CREATE TABLE " + QuizContract.cartoonEntry.TABLE_NAME + " (" +

                QuizContract.cartoonEntry.COLUMN_IMAGE + " TEXT NOT NULL UNIQUE," +
                QuizContract.cartoonEntry.COLUMN_OPTION1 + " TEXT NOT NULL," +
                QuizContract.cartoonEntry.COLUMN_OPTION2 + " TEXT NOT NULL," +
                QuizContract.cartoonEntry.COLUMN_OPTION3 + " TEXT NOT NULL," +
                QuizContract.cartoonEntry.COLUMN_ANSWER + " TEXT NOT NULL )";

        db.execSQL(SQL_CREATE_CARTOONQUIZ_TABLE);

    }

    public List<AnimalQuestion> getQuestions() {
        List<AnimalQuestion> questionList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {

                QuizContract.QuizEntry.COLUMN_QUESTION,
                QuizContract.QuizEntry.COLUMN_ANSWER,
                QuizContract.QuizEntry.COLUMN_IMAGE
        };

        Cursor cursor = db.query(
                QuizContract.QuizEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                "RANDOM()",
                "4"
        );


        while (cursor.moveToNext()) {

            String question = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.QuizEntry.COLUMN_QUESTION));
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.QuizEntry.COLUMN_ANSWER));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.QuizEntry.COLUMN_IMAGE));
            questionList.add(new AnimalQuestion(question, image, answer));

        }
        cursor.close();
        return questionList;
    }

    public List<cartoonQuestion> getCartoonQuestions() {

        List<cartoonQuestion> questionList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {

                QuizContract.cartoonEntry.COLUMN_ANSWER,
                QuizContract.cartoonEntry.COLUMN_IMAGE,
                QuizContract.cartoonEntry.COLUMN_OPTION1,
                QuizContract.cartoonEntry.COLUMN_OPTION2,
                QuizContract.cartoonEntry.COLUMN_OPTION3,
        };

        Cursor cursor = db.query(
                QuizContract.cartoonEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                "RANDOM()",
                "4"
        );


        while (cursor.moveToNext()) {

            String option1 = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.cartoonEntry.COLUMN_OPTION1));
            String option2 = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.cartoonEntry.COLUMN_OPTION2));
            String option3 = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.cartoonEntry.COLUMN_OPTION3));
            String answer = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.cartoonEntry.COLUMN_ANSWER));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(QuizContract.cartoonEntry.COLUMN_IMAGE));
            questionList.add(new cartoonQuestion(image,answer,option1,option2,option3));

        }
        cursor.close();
        return questionList;
    }


@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuizEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.cartoonEntry.TABLE_NAME);
        onCreate(db);
    }
    public void deleteDatabase(Context c){
        c.deleteDatabase(DATABASE_NAME);
    }
}
