package com.pooja.myproject.DatabaseActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pooja.myproject.Model.Eachrow;
import com.pooja.myproject.Model.FinishedEachrow;

import java.util.ArrayList;

/**
 * Created by Pooja on 1/21/2017.
 */

public class DBHelper
{
    Helper myhelper;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    ArrayList<Eachrow> eachrowArrayList;


    public DBHelper(Context context)
    {
        myhelper=new Helper(context);
        sqLiteDatabase=myhelper.getWritableDatabase();
        eachrowArrayList=new ArrayList<Eachrow>();
        contentValues=new ContentValues();
    }

    //Insert into database
      public long insertintodb(String title,String description,String date)throws SQLException
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(myhelper.KEY_TITLE,title);
        contentValues.put(myhelper.KEY_DESCRIPTION,description);
        contentValues.put(myhelper.KEY_DATE,date);
        contentValues.put(myhelper.KEY_STATUS,0);
        long result=sqLiteDatabase.insert(myhelper.TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    //get all details of the table and load into recycler view
    public ArrayList<Eachrow> getalldetails()
    {
        String[]columns={myhelper.KEY_ID,myhelper.KEY_TITLE,myhelper.KEY_DESCRIPTION,myhelper.KEY_DATE};
        Cursor cursor=sqLiteDatabase.query(myhelper.TABLE_NAME,columns,myhelper.KEY_STATUS + "=?",new String[]{String.valueOf(0)},null,null,null);
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndex(myhelper.KEY_ID));
            String title=cursor.getString(cursor.getColumnIndex(myhelper.KEY_TITLE));
            String description=cursor.getString(cursor.getColumnIndex(myhelper.KEY_DESCRIPTION));
            String date=cursor.getString(cursor.getColumnIndex(myhelper.KEY_DATE));

            Eachrow eachrow=new Eachrow(id,title,description,date);
            eachrowArrayList.add(eachrow);
        }
        return eachrowArrayList;

    }

    //update values to database
    public long updaterow(int id,String title,String desc,String date)
    {
        contentValues.put(myhelper.KEY_TITLE,title);
        contentValues.put(myhelper.KEY_DESCRIPTION,desc);
        contentValues.put(myhelper.KEY_DATE,date);
        long updateresult=sqLiteDatabase.update(myhelper.TABLE_NAME,contentValues,myhelper.KEY_ID + "=?",new String[]{String.valueOf(id)});
        return updateresult;
    }

    //update finished items

    public long updatestatus(int id)
    {
        contentValues.put(myhelper.KEY_STATUS,1);
        long updatestatus=sqLiteDatabase.update(myhelper.TABLE_NAME,contentValues,myhelper.KEY_ID + "=?",new String[]{String.valueOf(id)});
         return updatestatus;
    }

    public ArrayList<FinishedEachrow> getfinisheditems()
    {
        ArrayList<FinishedEachrow> finishedEachrows=new ArrayList<FinishedEachrow>();
        String []columns={myhelper.KEY_TITLE,myhelper.KEY_DATE,myhelper.KEY_ID};
        Cursor cursor=sqLiteDatabase.query(myhelper.TABLE_NAME,columns,myhelper.KEY_STATUS + "=?",new String[]{String.valueOf(1)},null,null,null);
        while(cursor.moveToNext())
        {
            String title=cursor.getString(cursor.getColumnIndex(myhelper.KEY_TITLE));
            String date=cursor.getString(cursor.getColumnIndex(myhelper.KEY_DATE));
            int id=cursor.getInt(cursor.getColumnIndex(myhelper.KEY_ID));
            FinishedEachrow finishedEachrow=new FinishedEachrow(id,title,date);
            finishedEachrows.add(finishedEachrow);
        }
        return finishedEachrows;
    }

//delete particular item from list
public long deleteitemfromfinishedlist(int id)
{
    long deleteresult=sqLiteDatabase.delete(myhelper.TABLE_NAME,myhelper.KEY_ID + "=?",new String[]{String.valueOf(id)});
    return deleteresult;

}

    //Delete all from finished list
    public long deleteall()
    {
        long delete=sqLiteDatabase.delete(myhelper.TABLE_NAME,myhelper.KEY_STATUS + "=?",new String[]{String.valueOf(1)});
        return delete;
    }

    //Database creation and upgrade
    public static class Helper extends SQLiteOpenHelper
    {
        private static final String DB_NAME="TODOLIST.db";
        private static final String TABLE_NAME="USER_LIST";
        private static final int VERSION=1;
        private static final String KEY_ID="ID";
        private static final String KEY_TITLE="TITLE";
        private static final String KEY_DESCRIPTION="DESCRIPTION";
        private static final String KEY_DATE="DATE";
        private static final String KEY_STATUS="STATUS";

        public Helper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String create_table="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_TITLE+" TEXT,"+KEY_DESCRIPTION+" TEXT,"+KEY_DATE+" TEXT,"+KEY_STATUS+" INTEGER);";
            sqLiteDatabase.execSQL(create_table);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
