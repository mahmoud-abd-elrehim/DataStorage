package com.iti.mahmoud.datastorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Mahmoud on 3/1/2016.
 */
public class Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "name";
    public static final String CONTACTS_COLUMN_ID = "phone";
    public static final int version=1;
   public Helper(Context context){

       super(context,DATABASE_NAME,null,version);

   }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table user"+"(name text,phone text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public boolean insertContact  (User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        db.insert("user", null, contentValues);
        return true;
    }
    public Cursor getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from user where name ='"+name+"'", null );
        return res;
    }
}
