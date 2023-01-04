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
    private static final String COLUMN_ISBN = "book_isbn";
    private static final String COLUMN_PUBLISH_DATE = "book_publish_date";
    private static final String COLUMN_COVER_ID = "book_cover_id";
    private static final String COLUMN_COVER_IMAGE = "book_cover_image";

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
                COLUMN_READ + " BOOLEAN, " +
                COLUMN_ISBN + " TEXT, " +
                COLUMN_PUBLISH_DATE + " TEXT, " +
                COLUMN_COVER_ID + " TEXT, " +
                COLUMN_COVER_IMAGE + " BLOB );";
        db.execSQL(query);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " INTEGER, " +
                COLUMN_PAGES + " INTEGER, " +
                COLUMN_READ + " BOOLEAN, " +
                COLUMN_ISBN + " TEXT, " +
                COLUMN_PUBLISH_DATE + " TEXT, " +
                COLUMN_COVER_ID + " TEXT, " +
                COLUMN_COVER_IMAGE + " BLOB );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, book.getTitle());
        cv.put(COLUMN_AUTHOR, book.getAuthor().toStringDB());
        cv.put(COLUMN_PAGES, book.getPages());
        cv.put(COLUMN_ISBN, book.getIsbn());
        cv.put(COLUMN_PUBLISH_DATE, book.getPublishDate());
        cv.put(COLUMN_COVER_ID, book.getCoverId());
        cv.put(COLUMN_COVER_IMAGE, book.getCoverImage());
        cv.put(COLUMN_READ, book.getRead());

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllBooks() {
        Log.i("HSKL", "Book_DBHelper => readAllBooks");
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(Book book) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, book.getTitle());
        cv.put(COLUMN_AUTHOR, book.getAuthor().toStringDB());
        cv.put(COLUMN_PAGES, book.getPages());
        cv.put(COLUMN_ISBN, book.getIsbn());
        cv.put(COLUMN_PUBLISH_DATE, book.getPublishDate());
        cv.put(COLUMN_COVER_ID, book.getCoverId());
        cv.put(COLUMN_COVER_IMAGE, book.getCoverImage());
        cv.put(COLUMN_READ, book.getRead());

        String where = COLUMN_ID + "=?";
        String id = String.valueOf(book.getId());

        Log.i("JETZT", "Book_DBHelper -> update Data: ID: " + book.toString());

        String[] whereArg = new String[]{id};

        long result = db.update(TABLE_NAME, cv, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> update Data: Fehler beim Update des Elements");
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> update Data: Element erfolgreich geupdated");
        }
        db.close();
    }


    /*void deleteOne(Book book) {

        SQLiteDatabase db = this.getWritableDatabase();

        String where = COLUMN_TITLE + "=?";
        String[] whereArg = new String[]{book.getTitle()};
        long result = db.delete(TABLE_NAME, where, whereArg);
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Fehler beim Löschen des Elements");
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
            Log.i("HSKL", "Book_DBHelper -> deleteOne: Element erfolgreich gelöscht");
        }
    }*/

    void deleteOne(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = COLUMN_TITLE + "=?";
        String[] whereArg = new String[]{book.getTitle()};
        db.delete(TABLE_NAME, where, whereArg);
    }

    Book findByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_TITLE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{title});
        /*
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_PAGES, COLUMN_READ, COLUMN_ISBN, COLUMN_PUBLISH_DATE, COLUMN_COVER_ID, COLUMN_COVER_IMAGE};
        String selection = COLUMN_TITLE + " = ?";
        String[] selectionArgs = {title};
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        //'"+title+"'"
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE book_title='" + title + "'", null);
         */
        Book ret = null;
        if (cursor.moveToFirst()) {
            ret = getBookThroughCursor(cursor);
        }
        return ret;
    }

    public void logAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Log.i("HSKL", "--------Book_DBHelper -> logAllData--------\n\n");
        while (meinZeiger.moveToNext()) {
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
            int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
            int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);
            Log.i("HSKL", "Book_DBHelper -> logAllData: ID: " + meinZeiger.getString(iId) + ", Buchtitel: " + meinZeiger.getString(iTitle) + ", Author: " + meinZeiger.getString(iAuthor) + ", Seiten: " + meinZeiger.getString(iPages) + "\n");
        }
        Log.i("HSKL", "----------------------------------------------------");
    }

    public List<Book> getAllBooksAsList() {
        List<Book> ret = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        MyDatabaseHelper myDb = new MyDatabaseHelper(context);
        Cursor meinZeiger = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while (meinZeiger.moveToNext()) {
            int iId = meinZeiger.getColumnIndex(COLUMN_ID);
            int iTitle = meinZeiger.getColumnIndex(COLUMN_TITLE);
            int iAuthor = meinZeiger.getColumnIndex(COLUMN_AUTHOR);
            int iPages = meinZeiger.getColumnIndex(COLUMN_PAGES);
            ret.add(new Book(meinZeiger.getString(iTitle),
                    myDb.getAuthorByName(NameSplitter.SplitVorname(meinZeiger.getString(iAuthor)), NameSplitter.SplitNachname(meinZeiger.getString(iAuthor))),
                    meinZeiger.getString(iPages), meinZeiger.getInt(iId)));
        }
        return ret;
    }

    public Book getBookThroughCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
        String pages = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAGES));
        String authorName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
        Author author = new Author(NameSplitter.SplitVorname(authorName), NameSplitter.SplitNachname(authorName));
        String isbn = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ISBN));
        String publishDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH_DATE));
        String coverId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_ID));
        byte[] coverImage = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_COVER_IMAGE));
        Book ret = new Book(title, author, pages, isbn, publishDate, coverId, coverImage, id);
        return ret;
    }

    public void deleteDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        String self_destroy = "DROP TABLE " + TABLE_NAME;
        db.execSQL(self_destroy);
    }
}
