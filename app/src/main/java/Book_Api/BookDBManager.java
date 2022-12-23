package Book_Api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookDBManager extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "BookDBForAPI";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_KEY = "'key'";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_SEED = "seed";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_TITLE_SUGGEST = "title_suggest";
    private static final String COLUMN_EDITION_COUNT = "edition_count";
    private static final String COLUMN_EDITION_KEY = "edition_key";
    private static final String COLUMN_PUBLISH_DATE = "publish_date";
    private static final String COLUMN_PUBLISH_YEAR = "publish_year";
    private static final String COLUMN_FIRST_PUBLISH_YEAR = "first_publish_year";
    private static final String COLUMN_NUMBER_OF_PAGES_MEDIAN = "number_of_pages_median";
    private static final String COLUMN_LCCN = "lccn";
    private static final String COLUMN_PUBLISH_PLACE = "publish_place";
    private static final String COLUMN_OCLC = "oclc";
    private static final String COLUMN_CONTRIBUTOR = "contributor";
    private static final String COLUMN_LCC = "lcc";
    private static final String COLUMN_DDC = "ddc";
    private static final String COLUMN_ISBN = "isbn";
    private static final String COLUMN_AUTHORS = "authors";
    private static final String COLUMN_SUBJECTS = "subjects";
    private static final String COLUMN_COVER_ID = "cover_id";
    private static final String COLUMN_COVER_IMAGE = "cover_img";


    public BookDBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_KEY + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_SEED + " TEXT, " + COLUMN_TITLE + " TEXT, " + COLUMN_TITLE_SUGGEST + " TEXT, " + COLUMN_EDITION_COUNT + " INTEGER, " + COLUMN_EDITION_KEY + " TEXT, " + COLUMN_PUBLISH_DATE + " TEXT, " + COLUMN_PUBLISH_YEAR + " INTEGER, " + COLUMN_FIRST_PUBLISH_YEAR + " INTEGER, " + COLUMN_NUMBER_OF_PAGES_MEDIAN + " INTEGER, " + COLUMN_LCCN + " TEXT, " + COLUMN_PUBLISH_PLACE + " TEXT, " + COLUMN_OCLC + " TEXT, " + COLUMN_CONTRIBUTOR + " TEXT, " + COLUMN_LCC + " TEXT, " + COLUMN_DDC + " Text, " + COLUMN_ISBN + " TEXT, " + COLUMN_AUTHORS + " TEXT, " + COLUMN_SUBJECTS + " Text, " + COLUMN_COVER_ID + " TEXT, " + COLUMN_COVER_IMAGE + " BLOB );";
        db.execSQL(query);
    }

    //Test wenn nicht onOpen raus
    @Override
    public void onOpen(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_KEY + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_SEED + " TEXT, " + COLUMN_TITLE + " TEXT, " + COLUMN_TITLE_SUGGEST + " TEXT, " + COLUMN_EDITION_COUNT + " INTEGER, " + COLUMN_EDITION_KEY + " TEXT, " + COLUMN_PUBLISH_DATE + " TEXT, " + COLUMN_PUBLISH_YEAR + " INTEGER, " + COLUMN_FIRST_PUBLISH_YEAR + " INTEGER, " + COLUMN_NUMBER_OF_PAGES_MEDIAN + " INTEGER, " + COLUMN_LCCN + " TEXT, " + COLUMN_PUBLISH_PLACE + " TEXT, " + COLUMN_OCLC + " TEXT, " + COLUMN_CONTRIBUTOR + " TEXT, " + COLUMN_LCC + " TEXT, " + COLUMN_DDC + " Text, " + COLUMN_ISBN + " TEXT, " + COLUMN_AUTHORS + " TEXT, " + COLUMN_SUBJECTS + " Text, " + COLUMN_COVER_ID + " TEXT, " + COLUMN_COVER_IMAGE + " BLOB );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KEY, book.getKey());
        values.put(COLUMN_TYPE, book.getType());
        values.put(COLUMN_SEED, book.getSeed().toString());
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_TITLE_SUGGEST, book.getTitleSuggest());
        values.put(COLUMN_EDITION_COUNT, book.getEditionCount());
        values.put(COLUMN_EDITION_KEY, book.getEditionKey().toString());
        values.put(COLUMN_PUBLISH_DATE, book.getPublishDate().toString());
        values.put(COLUMN_PUBLISH_YEAR, book.getPublishYear().toString());
        values.put(COLUMN_FIRST_PUBLISH_YEAR, book.getFirstPublishYear());
        values.put(COLUMN_NUMBER_OF_PAGES_MEDIAN, book.getNumberOfPagesMedian());
        values.put(COLUMN_LCCN, book.getLccn().toString());
        values.put(COLUMN_PUBLISH_PLACE, book.getPublishPlace().toString());
        values.put(COLUMN_OCLC, book.getOclc().toString());
        values.put(COLUMN_CONTRIBUTOR, book.getContributor().toString());
        values.put(COLUMN_LCC, book.getLcc().toString());
        values.put(COLUMN_DDC, book.getDdc().toString());
        values.put(COLUMN_ISBN, book.getIsbn().toString());
        values.put(COLUMN_AUTHORS, book.getAuthors().toString());
        values.put(COLUMN_SUBJECTS, book.getSubjects().toString());
        values.put(COLUMN_COVER_ID, book.getCoverId());
        values.put(COLUMN_COVER_IMAGE, book.getCoverImage());

        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KEY, book.getKey());
        values.put(COLUMN_TYPE, book.getType());
        values.put(COLUMN_SEED, book.getSeed().toString());
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_TITLE_SUGGEST, book.getTitleSuggest());
        values.put(COLUMN_EDITION_COUNT, book.getEditionCount());
        values.put(COLUMN_EDITION_KEY, book.getEditionKey().toString());
        values.put(COLUMN_PUBLISH_DATE, book.getPublishDate().toString());
        values.put(COLUMN_PUBLISH_YEAR, book.getPublishYear().toString());
        values.put(COLUMN_FIRST_PUBLISH_YEAR, book.getFirstPublishYear());
        values.put(COLUMN_NUMBER_OF_PAGES_MEDIAN, book.getNumberOfPagesMedian());
        values.put(COLUMN_LCCN, book.getLccn().toString());
        values.put(COLUMN_PUBLISH_PLACE, book.getPublishPlace().toString());
        values.put(COLUMN_OCLC, book.getOclc().toString());
        values.put(COLUMN_CONTRIBUTOR, book.getContributor().toString());
        values.put(COLUMN_LCC, book.getLcc().toString());
        values.put(COLUMN_DDC, book.getDdc().toString());
        values.put(COLUMN_ISBN, book.getIsbn().toString());
        values.put(COLUMN_AUTHORS, book.getAuthors().toString());
        values.put(COLUMN_SUBJECTS, book.getSubjects().toString());
        values.put(COLUMN_COVER_ID, book.getCoverId());
        values.put(COLUMN_COVER_IMAGE, book.getCoverImage());

        String selection = "key = ?";
        String[] selectionArgs = {book.getKey()};

        return db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public int delete(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "key = ?";
        String[] selectionArgs = {book.getKey()};

        return db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public Book find(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_KEY, COLUMN_TYPE, COLUMN_SEED, COLUMN_TITLE, COLUMN_TITLE_SUGGEST, COLUMN_EDITION_COUNT, COLUMN_EDITION_KEY, COLUMN_PUBLISH_DATE, COLUMN_PUBLISH_YEAR, COLUMN_FIRST_PUBLISH_YEAR, COLUMN_NUMBER_OF_PAGES_MEDIAN, COLUMN_LCCN, COLUMN_PUBLISH_PLACE, COLUMN_OCLC, COLUMN_CONTRIBUTOR, COLUMN_LCC, COLUMN_DDC, COLUMN_ISBN, COLUMN_AUTHORS, COLUMN_SUBJECTS, COLUMN_COVER_ID, COLUMN_COVER_IMAGE};

        String selection = "key = ?";
        String[] selectionArgs = {key};

        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Book book = null;
        if (cursor.moveToFirst()) {
            book = getBookThroughCursor(cursor);
        }
        return book;
    }

    public List<Book> getAllBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_KEY, COLUMN_TYPE, COLUMN_SEED, COLUMN_TITLE, COLUMN_TITLE_SUGGEST, COLUMN_EDITION_COUNT, COLUMN_EDITION_KEY, COLUMN_PUBLISH_DATE, COLUMN_PUBLISH_YEAR, COLUMN_FIRST_PUBLISH_YEAR, COLUMN_NUMBER_OF_PAGES_MEDIAN, COLUMN_LCCN, COLUMN_PUBLISH_PLACE, COLUMN_OCLC, COLUMN_CONTRIBUTOR, COLUMN_LCC, COLUMN_DDC, COLUMN_ISBN, COLUMN_AUTHORS, COLUMN_SUBJECTS, COLUMN_COVER_ID, COLUMN_COVER_IMAGE};

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);

        List<Book> books = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = getBookThroughCursor(cursor);
            books.add(book);
        }
        return books;
    }
    public Book getBookThroughCursor(Cursor cursor){
        String bookKey = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KEY));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE));
        String seedString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SEED));
        List<String> seed = Arrays.asList(seedString.substring(1, seedString.length() - 1).split(", "));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
        String titleSuggest = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE_SUGGEST));
        int editionCount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EDITION_COUNT));
        String editionKeyString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EDITION_KEY));
        List<String> editionKey = Arrays.asList(editionKeyString.substring(1, editionKeyString.length() - 1).split(", "));
        String publishDateString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH_DATE));
        List<String> publishDate = Arrays.asList(publishDateString.substring(1, publishDateString.length() - 1).split(", "));
        String publishYearString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH_YEAR));
        List<Integer> publishYear = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            publishYear = Arrays.asList(publishYearString.substring(1, publishYearString.length() - 1).split(", ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        }
        int firstPublishYear = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FIRST_PUBLISH_YEAR));
        int numberOfPagesMedian = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMBER_OF_PAGES_MEDIAN));
        String lccnString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LCCN));
        List<String> lccn = Arrays.asList(lccnString.substring(1, lccnString.length() - 1).split(", "));
        String publishPlaceString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH_PLACE));
        List<String> publishPlace = Arrays.asList(publishPlaceString.substring(1, publishPlaceString.length() - 1).split(", "));
        String oclcString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OCLC));
        List<String> oclc = Arrays.asList(oclcString.substring(1, oclcString.length() - 1).split(", "));
        String contributorString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTRIBUTOR));
        List<String> contributor = Arrays.asList(contributorString.substring(1, contributorString.length() - 1).split(", "));
        String lccString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LCC));
        List<String> lcc = Arrays.asList(lccString.substring(1, lccString.length() - 1).split(", "));
        String ddcString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DDC));
        List<String> ddc = Arrays.asList(ddcString.substring(1, ddcString.length() - 1).split(", "));
        String isbnString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ISBN));
        List<String> isbn = Arrays.asList(isbnString.substring(1, isbnString.length() - 1).split(", "));
        String authorsString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHORS));
        List<String> authors = Arrays.asList(authorsString.substring(1, authorsString.length() - 1).split(", "));
        String subjectsString = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUBJECTS));
        List<String> subjects = Arrays.asList(subjectsString.substring(1, subjectsString.length() - 1).split(", "));
        String cover_id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_ID));
        byte[] cover_image = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_COVER_IMAGE));

        Book book = new Book(bookKey, type, seed, title, titleSuggest, editionCount, editionKey, publishDate, publishYear, firstPublishYear, numberOfPagesMedian, lccn, publishPlace, oclc, contributor, lcc, ddc, isbn, authors, subjects, cover_id, cover_image);
        return book;
    }
}
