package com.example.quizforkids;

import android.provider.BaseColumns;

public class UserContract {
    private UserContract() {}

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static class ScoreTable implements BaseColumns {

        public static final String TABLE_NAME = "scores";

        public static final String COLUMN_USERNAME = "username";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_SCORE = "score";

        public static final String COLUMN_CATEGORY = "category";




    }

}

