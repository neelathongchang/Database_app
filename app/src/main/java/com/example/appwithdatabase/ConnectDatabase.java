package com.example.appwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectDatabase extends SQLiteOpenHelper {
    public static final String DatabaseName="info.db";

    public static final String TableName="tb_personal";

    public static final String Col1="id";
    public static final String Col2="name";
    public static final String Col3="surname";
    public static final String Col4="phone";
    public static final String Col5="email";

    public ConnectDatabase(Context context) {
        super(context,DatabaseName ,null,1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TableName+"(id integer primary key AUTOINCREMENT," +
                "name text," +
                "surname text," +
                "phone text," +
                "email text)");
    }

    public boolean insertData(String name ,String surname, String phone, String email){
        SQLiteDatabase SQLiteDatabase = this.getWritableDatabase();
        ContentValues ContentValues=new ContentValues();
//        ContentValues.put(Col1, id);
        ContentValues.put(Col2,name);
        ContentValues.put(Col3,surname);
        ContentValues.put(Col4,phone);
        ContentValues.put(Col5,email);

        long result= SQLiteDatabase.insert(TableName,null,ContentValues);

        return result != -1;
    }

    public boolean updateData(String id, String name, String surname, String phone, String email){
        SQLiteDatabase SQLiteDatabase = this.getWritableDatabase();
        ContentValues ContentValues=new ContentValues();
        ContentValues.put(Col2, name);
        ContentValues.put(Col3, surname);
        ContentValues.put(Col4, phone);
        ContentValues.put(Col5, email);
        Cursor cursor = SQLiteDatabase.rawQuery(" Select * from tb_personal where id=? ", new String[]{Integer.toString(Integer.parseInt(id))});
        if (cursor.getCount()>0){
            long result = SQLiteDatabase.update(TableName, ContentValues, "id=?", new String[]{Integer.toString(Integer.parseInt(id))});
            return result != -1;
        } else {
            return false;
        }
    }
// new String []{Integer.toString(id)}
    public boolean deleteData(String id){
        SQLiteDatabase SQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = SQLiteDatabase.rawQuery(" Select * from tb_personal where id=? ", new String[]{Integer.toString(Integer.parseInt(id))});
        if (cursor.getCount()>0){
            long result = SQLiteDatabase.delete(TableName, "id=?", new String[]{Integer.toString(Integer.parseInt(id))});
            return result != -1;
        } else {
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if EXISTS "+TableName);
        onCreate(sqLiteDatabase);
    }
}
