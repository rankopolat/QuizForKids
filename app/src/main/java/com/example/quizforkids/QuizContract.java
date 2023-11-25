package com.example.quizforkids;
import android.provider.BaseColumns;
public class QuizContract {
    private QuizContract(){}

    public static class QuizEntry implements BaseColumns {
        public static final String TABLE_NAME = "quiz";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_ANSWER = "answer";

    }

    public static class cartoonEntry implements BaseColumns{

        public static final String TABLE_NAME = "cartoon";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";



    }
}
