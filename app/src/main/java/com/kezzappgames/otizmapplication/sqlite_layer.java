package com.kezzappgames.otizmapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class sqlite_layer extends SQLiteOpenHelper {

    public static final String ROW_ID = "ID";
    public static final String ROW_NAME = "isim";
    public static final String ROW_SURNAME = "soyisim";



    public sqlite_layer(Context c){
        super(c,"kullanici",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql= "CREATE TABLE kullanici(id integer, isim text not null,soyisim text)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists kullanici");


    }


    public long ekleKullanici(kullanici k) {

        String isim = k.getIsim();
        String soyisim = k.getSoyisim();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        try{

            cv.put("isim",k.getIsim());
            cv.put("soyisim",k.getSoyisim());

        }catch (Exception e)
        {
            e.printStackTrace();
        }



        long id = db.insert("kullanici",null,cv);
        return id;

    }
    public List<String> VeriListele(String isim)
    {
        List<String> veriler = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        try{

            String sql2= "SELECT * FROM kullanici WHERE "+ROW_NAME+" LIKE '"+isim+"%'";

            Cursor cursor = db.rawQuery(sql2,null);

            int nameIx = cursor.getColumnIndex(ROW_NAME);
            int surnameIx = cursor.getColumnIndex(ROW_SURNAME);

            while (cursor.moveToNext())
            {
                veriler.add(cursor.getString(nameIx)+" - "+cursor.getString(surnameIx));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return veriler;
    }
    public void silKullanici(String isim)
    {
        try{
        SQLiteDatabase db = this.getReadableDatabase();
        String sql3= "DELETE FROM kullanici WHERE "+ROW_NAME+" = '"+isim+"'";
        db.execSQL(sql3);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
