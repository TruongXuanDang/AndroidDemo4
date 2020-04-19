package com.truongdx8.androidproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "User.db";
    public static int DB_VERSION = 1;

    public static String TABLE_USER = "USER_LIST";
    public static String user_id = "id";
    public static String user_name = "name";
    public static String user_gender = "gender";
    public static String user_description = "description";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_USER+" ( "+user_id+
                " INTEGER PRIMARY KEY autoincrement, "+user_name+
                " TEXT, "+user_gender+
                " TEXT, "+user_description+
                " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_USER;
        db.execSQL(sql);
        onCreate(db);
    }

    public String insertData(UserItem user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(user_name, user.getName());
        cv.put(user_gender, user.getGender());
        cv.put(user_description, user.getDescription());
        long isSuccess = db.insert(TABLE_USER,null,cv);
        if(isSuccess>0)
        {
            return "Success";
        }
        else
        {
            return "Failed";
        }

    }

    public String updateData(UserItem user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(user_name, user.getName());
        cv.put(user_gender, user.getGender());
        cv.put(user_description, user.getDescription());
        int isSuccess = db.update(TABLE_USER,cv,user_id+" = "+user.getId(),null);
        if(isSuccess>0)
        {
            return "Success";
        }
        else
        {
            return "Failed";
        }
    }

    public String deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int isSuccess = db.delete(TABLE_USER,user_id+" = "+id,null);

        if(isSuccess>0)
        {
            return "Success";
        }
        else
        {
            return "Failed";
        }
    }

    public UserItem getItem(int id)
    {
        UserItem user = new UserItem();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+ TABLE_USER +
                " WHERE " + user_id +
                " = " + id;
        Cursor c = db.rawQuery(sql,null);

        if(c.getCount()>0)
        {
            c.moveToFirst();

            String name = c.getString(c.getColumnIndex(user_name));
            int identity = c.getInt(c.getColumnIndex(user_id));
            String gender = c.getString(c.getColumnIndex(user_gender));
            String description = c.getString(c.getColumnIndex(user_description));
            user = new UserItem(identity,name,gender,description);

        };

        return user;
    }

    public List<UserItem> getData()
    {
        List<UserItem> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+ TABLE_USER;

        Cursor c = db.rawQuery(sql,null);

        if(c.getCount()>0)
        {
            c.moveToFirst();

            do {
                String name = c.getString(c.getColumnIndex(user_name));
                int id = c.getInt(c.getColumnIndex(user_id));
                String gender = c.getString(c.getColumnIndex(user_gender));
                String description = c.getString(c.getColumnIndex(user_description));
                UserItem user = new UserItem(id,name,gender,description);
                userList.add(user);
            }
            while (c.moveToNext());

        };

        return userList;
    }
}
