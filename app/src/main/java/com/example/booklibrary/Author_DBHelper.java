package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//import Book_Api.Author;

public class Author_DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "AuthorDB";
    private static final String COLUMN_ID = "p_ID";
    private static final String COLUMN_VORNAME = "vorname";
    private static final String COLUMN_NACHNAME = "nachname";

    Author_DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VORNAME + " TEXT, " +
                COLUMN_NACHNAME + " TEXT );";
        db.execSQL(query);
    }
    //Test wenn nicht onOpen raus
    @Override
    public void onOpen(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VORNAME + " TEXT, " +
                COLUMN_NACHNAME + " TEXT );";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addAuthor(Author author){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_VORNAME, author.getVorname());
        cv.put(COLUMN_NACHNAME, author.getNachname());
        long result = db.insert(TABLE_NAME, null, cv);
        Log.i("HSKL", "Author_DBHelper => addAuthor => Vorname: " + author.getVorname() + ", Nachname: " + author.getNachname() + ", Result: " + result);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllAuthors() {
        Log.i("HSKL", "Author_DBHelper => readAllAuthors");
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Todo
    void updateData(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VORNAME, author.getVorname());
        values.put(COLUMN_NACHNAME, author.getNachname());

        String where = COLUMN_ID + "=?";
        String id = String.valueOf(author.getpId());
        String[] whereArg = new String[]{id};

        long result = db.update(TABLE_NAME, values, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Author_DBHelper -> update Data: Fehler beim Update des Elements");
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Author_DBHelper -> update Data: Element erfolgreich geupdated");
        }
        db.close();
    }

    //Todo
    void deleteAuthor(Author author) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = COLUMN_ID + "=?";
        String[] whereArg = new String[]{String.valueOf(author.getpId())};
        long result = db.delete(TABLE_NAME, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Fehler beim Löschen des Elements");
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Element erfolgreich gelöscht");
        }
    }

    //Todo
    Author findAuthorByName(String vorname, String nachname) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Test
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE vorname='"+vorname+"' AND nachname='"+nachname+"'", null);
        // Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_VORNAME + "=" + vorname + " AND " + COLUMN_NACHNAME + "=" + nachname, null);
        meinZeiger.moveToFirst();

        int iId = meinZeiger.getColumnIndex(COLUMN_ID);
        int iVorname = meinZeiger.getColumnIndex(COLUMN_VORNAME);
        int iNachname = meinZeiger.getColumnIndex(COLUMN_NACHNAME);
        Author ret = new Author(meinZeiger.getString(iVorname), meinZeiger.getString(iNachname), meinZeiger.getInt(iId));
        if(ret != null)
            return ret;
        else
            return null;
    }

    boolean AuthorExists(String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        String vorname = NameSplitter.SplitVorname(author);
        String nachname = NameSplitter.SplitNachname(author);
        //Test
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE vorname='"+vorname+"' AND nachname='"+nachname+"'", null);
        //Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_VORNAME + "=" + vorname + " AND " + COLUMN_NACHNAME + "=" + nachname, null);
        Log.i("HSKL", "Author_DBHelper => meinZeiger: " + meinZeiger.moveToFirst());
        return(meinZeiger.moveToFirst());
    }

    Author getAuthorByName(String vorname, String nachname) {
        SQLiteDatabase db = this.getWritableDatabase();
        //'"+title+"'"
        //Test
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE vorname='"+vorname+"' AND nachname='"+nachname+"'", null);
        //Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_VORNAME + "=" + vorname + " AND " + COLUMN_NACHNAME + "=" + nachname, null);
        if (meinZeiger.moveToFirst()) {

            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iVorname = meinZeiger.getColumnIndex(COLUMN_VORNAME);
            int iNachname = meinZeiger.getColumnIndex(COLUMN_NACHNAME);
            Author ret = new Author(meinZeiger.getString(iVorname), meinZeiger.getString(iNachname), meinZeiger.getInt(iId));
            Log.i("HSKL", "Author_DBHelper -> getAuthorByName: " + ret.toString() +  "\n");

            meinZeiger.close();
            return ret;
        } else {
            meinZeiger.close();
            Log.i("HSKL", "Book_DBHelper -> findByTitle: Keine Daten gefunden!");
            return null;
        }

    }

    public void logAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Log.i("HSKL", "--------Author_DBHelper -> logAllData--------\n\n");
        while (meinZeiger.moveToNext()){
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iVorname = meinZeiger.getColumnIndex(COLUMN_VORNAME);
            int iNachname = meinZeiger.getColumnIndex(COLUMN_NACHNAME);
            Log.i("HSKL", "Author_DBHelper -> logAllData: ID: " + meinZeiger.getInt(iId) + ", Vorname: " + meinZeiger.getString(iVorname) + ", Nachname: " + meinZeiger.getString(iNachname) + "\n");
        }
        Log.i("HSKL", "----------------------------------------------------");
    }
    public List<Author> getAllAuthorsAsList(){
        List<Author> ret = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        MyDatabaseHelper myDb = new MyDatabaseHelper(context);
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while(meinZeiger.moveToNext()){
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iVorname = meinZeiger.getColumnIndex(COLUMN_VORNAME);
            int iNachname = meinZeiger.getColumnIndex(COLUMN_NACHNAME);

            ret.add(new Author(meinZeiger.getString(iVorname), meinZeiger.getString(iNachname), meinZeiger.getInt(iId)));

        }
        return ret;
    }
    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String self_destroy = "DROP TABLE " + TABLE_NAME;
        db.execSQL(self_destroy);
    }
}
