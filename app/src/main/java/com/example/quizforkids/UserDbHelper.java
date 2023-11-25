package com.example.quizforkids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 3;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + UserContract.UserTable.TABLE_NAME + " ("
                + UserContract.UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserTable.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
                + UserContract.UserTable.COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, "
                + UserContract.UserTable.COLUMN_PASSWORD + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_USERS_TABLE);

        String SQL_CREATE_SCORES_TABLE =
                "CREATE TABLE " + UserContract.ScoreTable.TABLE_NAME + " (" +
                        UserContract.ScoreTable._ID + " INTEGER PRIMARY KEY," +
                        UserContract.ScoreTable.COLUMN_USERNAME + " TEXT NOT NULl," +
                        UserContract.ScoreTable.COLUMN_DATE + " TEXT NOT NULl," +
                        UserContract.ScoreTable.COLUMN_CATEGORY + " TEXT NOT NULl," +
                        UserContract.ScoreTable.COLUMN_SCORE + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_SCORES_TABLE);
    }


    public List<String> prevAttemptsAll(String user) {
        List<String> scores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserContract.ScoreTable.COLUMN_SCORE,
                UserContract.ScoreTable.COLUMN_CATEGORY,
                UserContract.ScoreTable.COLUMN_DATE

        };

        String selection = UserContract.ScoreTable.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {user};

        Cursor cursor = db.query(
                UserContract.ScoreTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
           String score = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_SCORE));
           String cata = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_DATE));
           scores.add("'"+cata + "' area - attempt started on " +date +" - points earned "+score);
        }
        cursor.close();
        return scores;
    }

    public List<String> prevAttemptsCategory(String user,String category) {
        List<String> scores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserContract.ScoreTable.COLUMN_SCORE,
                UserContract.ScoreTable.COLUMN_CATEGORY,
                UserContract.ScoreTable.COLUMN_DATE

        };

        String selection = UserContract.ScoreTable.COLUMN_USERNAME + " = ?" + " AND " +
                UserContract.ScoreTable.COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {user,category};

        Cursor cursor = db.query(
                UserContract.ScoreTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String score = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_SCORE));
            String cata = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_DATE));
            scores.add("'"+cata + "' area - attempt started on " +date +" - points earned "+score);
        }
        cursor.close();
        return scores;
    }

    public List<String> prevAttemptsDate(String user) {
        List<String> scores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserContract.ScoreTable.COLUMN_SCORE,
                UserContract.ScoreTable.COLUMN_CATEGORY,
                UserContract.ScoreTable.COLUMN_DATE

        };

        String selection = UserContract.ScoreTable.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {user};
        String sort = UserContract.ScoreTable.COLUMN_DATE + " DESC";

        Cursor cursor = db.query(
                UserContract.ScoreTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sort
        );

        while (cursor.moveToNext()) {
            String score = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_SCORE));
            String cata = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_CATEGORY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_DATE));
            scores.add("'"+cata + "' area - attempt started on " +date +" - points earned "+score);
        }
        cursor.close();
        return scores;
    }


    public int returnScore(String user){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserContract.ScoreTable.COLUMN_SCORE,
        };

        String selection = UserContract.ScoreTable.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {user};

        Cursor cursor = db.query(
                UserContract.ScoreTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int total = 0;

        while (cursor.moveToNext()) {
            String score = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.ScoreTable.COLUMN_SCORE));
            int a = Integer.parseInt(score);
            total += a;
        }
        cursor.close();
        return total;
    }

    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {UserContract.UserTable.COLUMN_USERNAME, UserContract.UserTable.COLUMN_PASSWORD};
        String selection = UserContract.UserTable.COLUMN_USERNAME + " = ? AND " + UserContract.UserTable.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UserContract.UserTable.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    public void updatePassword(String name, String newPassword){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserContract.UserTable.COLUMN_PASSWORD, newPassword);

        String selection = UserContract.UserTable.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { name };

        int rowsAffected = db.update(
                UserContract.UserTable.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();
    }

    public boolean checkUserAccount(String username, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {UserContract.UserTable.COLUMN_USERNAME, UserContract.UserTable.COLUMN_EMAIL};
        String selection = UserContract.UserTable.COLUMN_USERNAME + " = ? AND " + UserContract.UserTable.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {username, email};
        Cursor cursor = db.query(UserContract.UserTable.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.ScoreTable.TABLE_NAME);
        onCreate(db);
    }

    public void deleteDatabase(Context c){
        c.deleteDatabase(DATABASE_NAME);
    }

}
