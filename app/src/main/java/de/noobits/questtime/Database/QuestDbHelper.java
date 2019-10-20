package de.noobits.questtime.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import de.noobits.questtime.Database.QuestContract.Quest;

public class QuestDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "questDatabase";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Quest.TABLE_NAME + "(" +
                    Quest._ID + " INTEGER PRIMARY KEY," +
                    Quest.COLUMN_NAME_TITLE + " TEXT, " +
                    Quest.COLUMN_DATE + " TEXT)";

    private static final String  SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Quest.TABLE_NAME;

    public QuestDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteDatabase(){
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public long createNewQuest(String name){
        ContentValues values = new ContentValues();
        values.put(Quest.COLUMN_NAME_TITLE, name);
        values.put(Quest.COLUMN_DATE, new Date().toString());

        return this.getWritableDatabase().insert(Quest.TABLE_NAME, null, values);
    }

    public void deleteQuest(int _id){
        db.delete(Quest.TABLE_NAME, "WHERE " + Quest._ID + " = ?", new String[]{_id + ""});
    }
}
