package com.example.carbonegy2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class MyDBHelper extends SQLiteOpenHelper {

    // Database version and name
    public static final int DATABASE_VERSION = 11;
    public static final String DATABASE_NAME = "myapp.db";
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_AVG_EMISSION = "average_emission";
    public static final String USER_CITY = "city";


    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , email TEXT NOT NULL UNIQUE, password TEXT NOT NULL, city TEXT NOT NULL, phone TEXT, average_emission INTEGER, total_emissions INTEGER, userGoal INTEGER)";
        db.execSQL(createUsersTable);

        String createEmissionsTable = "CREATE TABLE emissions (id INTEGER PRIMARY KEY, user_id INTEGER, date TEXT, value INTEGER)";
        db.execSQL(createEmissionsTable);

        String createSessionTable = "CREATE TABLE session (id INTEGER PRIMARY KEY AUTOINCREMENT, token TEXT NOT NULL, user_id INTEGER NOT NULL, expiration_date DATE NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id))";
        db.execSQL(createSessionTable);





    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Modify the database schema as needed here
        // using SQL ALTER TABLE and ALTER COLUMN statements
    }

    public boolean isValidUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id"}, "email = ? and password = ?", new String[]{email, password}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean addUser(String email, String password, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("city", city);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    public void updateUser(String email, String name, String avgEmission, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, name);
        values.put(USER_AVG_EMISSION, avgEmission);
        values.put(USER_PHONE, phone);
        db.update(USER_TABLE_NAME, values, USER_EMAIL + " = ?", new String[]{email});
        db.close();
    }







        public boolean addEmission(int user_id, int amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("value", amount);
        values.put("date", date);
        long result = db.insert("emissions", null, values);

        return result != -1;
    }

    public int getAverageEmission(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"average_emission"}, "email = ?", new String[]{email}, null, null, null);
        if(cursor.moveToFirst()) {
            int avgEmission = cursor.getInt(cursor.getColumnIndex("average_emission"));
            cursor.close();
            return avgEmission;
        }
        cursor.close();
        return 0;
    }



    public boolean updateUserEmission(String email, int average) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("average_emission", average);
        int result = db.update("users", values, "email=?", new String[]{email});
        return result > 0;
    }

    public String createSession(int user_id) {
        String token = "your_token_generation_logic";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("token", token);
        values.put("user_id", user_id);
        values.put("expiration_date", "your_token_expiration_date");
        long result = db.insert("session", null, values);
        return result != -1 ? token : null;
    }

    public boolean userExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id"}, "email = ?", new String[]{email}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {USER_ID, USER_NAME, USER_EMAIL, USER_AVG_EMISSION, USER_PHONE, USER_CITY};
        String selection = USER_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(USER_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public Cursor getUserDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"name", "phone","average_emission", "city"}, "email = ?", new String[]{email}, null, null, null);
        return cursor;
    }

    public void updateTotalEmissions() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE users SET total_emissions = (SELECT SUM(value) FROM emissions WHERE user_id = users.id);");
    }

    public void addUserGoal(String email, int goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userGoal", goal);
        db.update("users", values, "email = ?", new String[] {email});
        db.close();
    }
    public int getUserGoal(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[] {"userGoal"}, "email = ?", new String[] {email}, null, null, null);
        int goal = 0;
        if (cursor.moveToFirst()) {
            goal = cursor.getInt(cursor.getColumnIndex("userGoal"));
        }
        cursor.close();
        return goal;
    }


    public int getTotalEmissions(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"total_emissions"}, "email = ?", new String[]{email}, null, null, null);
        int totalEmissions = 0;
        if (cursor.moveToFirst()) {
            totalEmissions = cursor.getInt(cursor.getColumnIndex("total_emissions"));
        }
        cursor.close();
        return totalEmissions;
    }
    public List<EmissionRecord> getEmissionRecords(int userId) {
        List<EmissionRecord> emissionRecords = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("emissions", new String[]{"user_id", "date", "value"}, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int user_id = cursor.getInt(0);
                String date = cursor.getString(1);
                int value = cursor.getInt(2);

                EmissionRecord emissionRecord = new EmissionRecord(date, value);
                emissionRecords.add(emissionRecord);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return emissionRecords;
    }

    public int getUserIdFromEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"id"}, "email = ?", new String[]{email}, null, null, null);
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }



}
