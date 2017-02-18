package com.palletechnologies.consumercomplaintsboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suryapavan on 28/10/2016.
 */

public class ConsumerDataBase {

    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    public ConsumerDataBase(Context context){
        myHelper = new MyHelper(context,"consumer_complaints.db",null,1);
    }

    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    public void insertComplaints(String cname, String csub, String cdetails, int czip, String ccity, String cweb, String ccategory, String ccountry)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("company_name",cname);
        contentValues.put("complaint_subject",csub);
        contentValues.put("complaint_details",cdetails);
        contentValues.put("category",ccategory);
        contentValues.put("country",ccountry);
        contentValues.put("zip_code",czip);
        contentValues.put("city",ccity);
        contentValues.put("websites",cweb);
        sqLiteDatabase.insert("consumer_complaints",null,contentValues);
    }

    public void insertComments(String ccomment, String cdetails)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("comment", ccomment);
        contentValues.put("complaint_details", cdetails);
        sqLiteDatabase.insert("comments", null, contentValues);
    }

    public Cursor getComplaints()
    {
        Cursor cursor = sqLiteDatabase.query("consumer_complaints", null, null, null, null, null,null);
        return cursor;
    }
    public Cursor getComments(String cdetails)
    {
        Cursor cursor = sqLiteDatabase.query("comments",null,"complaint_details = ?",new String[]{cdetails},null,null,null);
        return cursor;
    }

    public void close()
    {
        sqLiteDatabase.close();
    }


    public class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table consumer_complaints(_id integer primary key, company_name text, complaint_subject text, " +
                    "complaint_details text, category text, country text, zip_code text, city text, websites text );");

            db.execSQL("create table comments(_id integer primary key,comment text,complaint_details text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
