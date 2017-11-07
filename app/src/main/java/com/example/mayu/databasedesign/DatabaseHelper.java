package com.example.mayu.databasedesign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mayu on 06-11-2017.
 * github= "https://github.com/Mayu001"
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE Student_table(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Student_table");
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String firstName, String lastName, String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("FIRSTNAME",firstName);
        contentValues.put("LASTNAME",lastName);
        contentValues.put("MARKS",marks);
        long result=db.insert("Student_table",null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from Student_table",null);
        return res;

    }

    public boolean updateData(String id,String firstName, String lastName, String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("FIRSTNAME",firstName);
        contentValues.put("LASTNAME",lastName);
        contentValues.put("MARKS",marks);
        contentValues.put("ID",id);
        db.update("student_table",contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Integer rowsAffected=db.delete("Student_table","ID=?",new String[]{id});
        return rowsAffected;
    }
}
