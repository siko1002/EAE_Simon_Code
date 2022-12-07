package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mylibrary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";
    private static final String COLUMN_READ = "book_read";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER, " +
                COLUMN_READ + " BOOLEAN);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

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

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String title, String author, String pages, String new_title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, new_title);
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_PAGES, pages);

        String where = COLUMN_ID + "=?";
        String id = findByTitle(title);

        Log.i("HSKL", "updateData -> ID: " + id + ", Buchtitel: " + title + ", Author: " + author + ", Seiten: " + pages+ "\n");

        String[] whereArg = new String[]{id};

        long result = db.update(TABLE_NAME, values, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Fehler beim Update des Elements");
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Element erfolgreich geupdated");
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
            Log.i("HSKL", "Fehler beim Löschen des Elements");
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Element erfolgreich gelöscht");
        }
    }

    String findByTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        //'"+title+"'"
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE book_title='" + title + "'", null);
        meinZeiger.moveToFirst();

        int iId = meinZeiger.getColumnIndex(COLUMN_ID);
        int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
        int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
        int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);

        Log.i("HSKL", "findByTitle -> ID: " + meinZeiger.getString(iId) + ", Buchtitel: " + meinZeiger.getString(iTitle) + ", Author: " + meinZeiger.getString(iAuthor) + ", Seiten: " + meinZeiger.getString(iPages) + "\n");

        String ret = meinZeiger.getString(iId);
        meinZeiger.close();
        return ret;
    }
}
