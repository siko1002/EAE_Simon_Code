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
//import Book_Api.Book;

public class Book_DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "BookDB";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "p_ID";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_READ = "book_read";

    Book_DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    //Test wenn nicht onOpen raus
    @Override
    public void onOpen(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " INTEGER, " +
                COLUMN_PAGES + " INTEGER, " +
                COLUMN_READ + " BOOLEAN);";
        db.execSQL(query);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " INTEGER, " +
                COLUMN_PAGES + " INTEGER, " +
                COLUMN_READ + " BOOLEAN);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
/*
    void addBook(String title, String author, int pages) {
        Log.i("HSKL", "Book_DBHelper => addBook aufgerufen => Title: " + title + ", Author: " + author + ", pages: " + pages);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        Author temp;
        if(!author_dbHelper.AuthorExists(author)){
            author_dbHelper.addAuthor(author);
            temp = author_dbHelper.getAuthorByName(NameSplitter.SplitVorname(author), NameSplitter.SplitNachname(author));
        }else{
            if(author_dbHelper.findAuthorByName("Max", "Mustermann") != null) {
                temp = author_dbHelper.getAuthorByName("Max", "Mustermann");
            }else{
                temp = new Author("Max", "Mustermann");
            }
        }

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, temp.getpId());
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME, null, cv);
        Log.i("HSKL", "Book_DBHelper => addBook => Title: " + title + ", Author: " + author + ", Pages: " + pages + ", Result: " + result);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }
*/
void addBook(String title, String author, int pages) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    cv.put(COLUMN_TITLE, title);
    cv.put(COLUMN_AUTHOR, author);
    cv.put(COLUMN_PAGES, pages);
    long result = db.insert(TABLE_NAME, null, cv);
    if (result == -1) {
        Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
    }

}

    Cursor readAllBooks() {
        Log.i("HSKL" , "Book_DBHelper => readAllBooks");
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String title, Author author, String pages, String new_title) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, new_title);
        //Neu
        if(author == null){
            values.put(COLUMN_AUTHOR, "");
        }else {
            values.put(COLUMN_AUTHOR, author.getVorname() + " " + author.getNachname());
        }
        //neu ende
        values.put(COLUMN_PAGES, pages);

        String where = COLUMN_ID + "=?";
        String id = findByTitle(title);

        Log.i("JETZT", "Book_DBHelper -> update Data: ID: " + id + ", Buchtitel: " + title + ", Author: " + author + ", Seiten: " + pages + "\n");

        String[] whereArg = new String[]{id};

        long result = db.update(TABLE_NAME, values, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> update Data: Fehler beim Update des Elements");
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> update Data: Element erfolgreich geupdated");
        }
        db.close();
    }


    void deleteOne(String title) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = COLUMN_TITLE + "=?";
        String[] whereArg = new String[]{title};
        long result = db.delete(TABLE_NAME, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Fehler beim Löschen des Elements");
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Element erfolgreich gelöscht");
        }
    }

    String findByTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        //'"+title+"'"
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE book_title='" + title + "'", null);
        if(meinZeiger.moveToFirst()) {

            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
            int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
            int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);

            Log.i("HSKL", "Book_DBHelper -> findByTitle: ID: " + meinZeiger.getString(iId) + ", Buchtitel: " + meinZeiger.getString(iTitle) + ", Author: " + meinZeiger.getString(iAuthor) + ", Seiten: " + meinZeiger.getString(iPages) + "\n");

            String ret = meinZeiger.getString(iId);
            meinZeiger.close();
            return ret;
        }
        else{
            meinZeiger.close();
            Log.i("HSKL", "Book_DBHelper -> findByTitle: Keine Daten gefunden!");
            return null;
        }
    }

    public void logAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Log.i("HSKL", "--------Book_DBHelper -> logAllData--------\n\n");
        while(meinZeiger.moveToNext()){
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
            int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
            int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);
            Log.i("HSKL", "Book_DBHelper -> logAllData: ID: " + meinZeiger.getString(iId) + ", Buchtitel: " + meinZeiger.getString(iTitle) + ", Author: " + meinZeiger.getString(iAuthor) + ", Seiten: " + meinZeiger.getString(iPages) + "\n");
        }
        Log.i("HSKL", "----------------------------------------------------");
    }
    public List<Book> getAllBooksAsList(){
        List<Book> ret = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        MyDatabaseHelper myDb = new MyDatabaseHelper(context);
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while(meinZeiger.moveToNext()){
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
            int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
            int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);
            ret.add(new Book(meinZeiger.getString(iTitle),
                    myDb.getAuthorByName(NameSplitter.SplitVorname(meinZeiger.getString(iAuthor)),  NameSplitter.SplitNachname(meinZeiger.getString(iAuthor))),
                            meinZeiger.getString(iPages), meinZeiger.getInt(iId)));
        }
        return ret;
    }
}
