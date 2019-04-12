package com.example.assignment1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "UserID";
    private static final String COLUMN_NAME = "UserName";
    private static final String COLUMN_LNAME = "UserLname";
    private static final String COLUMN_EMAIL = "UserEmail";
    private static final String COLUMN_DOB = "UserDob";
    private static final String COLUMN_GENDER = "UserGender";
    MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) { super(context, DATABASE_NAME, factory, DATABASE_VERSION); }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_NAME + " TEXT ," +
                COLUMN_LNAME + " TEXT ," +
                COLUMN_EMAIL + " TEXT ," +
                COLUMN_DOB + " TEXT ," +
                COLUMN_GENDER + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public  String loadHandler() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getUserID());
        values.put(COLUMN_NAME, user.getUserName());
        values.put(COLUMN_LNAME, user.getUserLname());
        values.put(COLUMN_EMAIL, user.getUserEmail());
        values.put(COLUMN_DOB, user.getUserDob());
        values.put(COLUMN_GENDER, user.getUserGender());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public User findHandler(int id) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserID(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setUserLname(cursor.getString(2));
            user.setUserEmail(cursor.getString(3));
            user.setUserDob(cursor.getString(4));
            user.setUserGender(cursor.getString(5));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }
}