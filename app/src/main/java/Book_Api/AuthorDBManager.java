package Book_Api;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorDBManager extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "BookDBForAPI";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_KEY = "'key'";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ALTERNATE_NAMES = "alternateNames";
    private static final String COLUMN_BIRTH_DATE = "birthDate";
    private static final String COLUMN_TOP_WORK = "topWork";
    private static final String COLUMN_WORK_COUNT = "workCount";
    private static final String COLUMN_TOP_SUBJECTS = "topSubjects";

    public AuthorDBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_KEY + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_ALTERNATE_NAMES + " TEXT, " + COLUMN_BIRTH_DATE + " TEXT, " + COLUMN_TOP_WORK + " TEXT, " + COLUMN_WORK_COUNT + " INTEGER, " + COLUMN_TOP_SUBJECTS + " TEXT );";
        db.execSQL(query);
    }

    //Test wenn nicht onOpen raus
    @Override
    public void onOpen(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_KEY + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_ALTERNATE_NAMES + " TEXT, " + COLUMN_BIRTH_DATE + " TEXT, " + COLUMN_TOP_WORK + " TEXT, " + COLUMN_WORK_COUNT + " INTEGER, " + COLUMN_TOP_SUBJECTS + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(Api_Author apiAuthor) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("key", apiAuthor.getKey());
        values.put("type", apiAuthor.getType());
        values.put("name", apiAuthor.getName());
        values.put("alternateNames", TextUtils.join(",", apiAuthor.getAlternateNames()));
        values.put("birthDate", apiAuthor.getBirthDate());
        values.put("topWork", apiAuthor.getTopWork());
        values.put("workCount", apiAuthor.getWorkCount());
        values.put("topSubjects", TextUtils.join(",", apiAuthor.getTopSubjects()));
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(Api_Author apiAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", apiAuthor.getType());
        values.put("name", apiAuthor.getName());
        values.put("alternateNames", TextUtils.join(",", apiAuthor.getAlternateNames()));
        values.put("birthDate", apiAuthor.getBirthDate());
        values.put("topWork", apiAuthor.getTopWork());
        values.put("workCount", apiAuthor.getWorkCount());
        values.put("topSubjects", TextUtils.join(",", apiAuthor.getTopSubjects()));
        return db.update(TABLE_NAME, values, "key = ?", new String[]{apiAuthor.getKey()});
    }

    public int delete(Api_Author apiAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "key = ?", new String[]{apiAuthor.getKey()});
    }
    public Api_Author find(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("Author", null, "key = ?", new String[] { key }, null, null, null);
        if (cursor.moveToFirst()) {
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String alternateNamesStr = cursor.getString(cursor.getColumnIndexOrThrow("alternateNames"));
            List<String> alternateNames = Arrays.asList(alternateNamesStr.split(","));
            String birthDate = cursor.getString(cursor.getColumnIndexOrThrow("birthDate"));
            String topWork = cursor.getString(cursor.getColumnIndexOrThrow("topWork"));
            int workCount = cursor.getInt(cursor.getColumnIndexOrThrow("workCount"));
            String topSubjectsStr = cursor.getString(cursor.getColumnIndexOrThrow("topSubjects"));
            List<String> topSubjects = Arrays.asList(topSubjectsStr.split(","));
            return new Api_Author(key, type, name, alternateNames, birthDate, topWork, workCount, topSubjects);
        } else {
            return null;
        }
    }

    public List<Api_Author> getAllAuthors() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Api_Author> apiAuthors = new ArrayList<>();
        Cursor cursor = db.query("Author", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String key = cursor.getString(cursor.getColumnIndexOrThrow("key"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String alternateNamesStr = cursor.getString(cursor.getColumnIndexOrThrow("alternateNames"));
            List<String> alternateNames = Arrays.asList(alternateNamesStr.split(","));
            String birthDate = cursor.getString(cursor.getColumnIndexOrThrow("birthDate"));
            String topWork = cursor.getString(cursor.getColumnIndexOrThrow("topWork"));
            int workCount = cursor.getInt(cursor.getColumnIndexOrThrow("workCount"));
            String topSubjectsStr = cursor.getString(cursor.getColumnIndexOrThrow("topSubjects"));
            List<String> topSubjects = Arrays.asList(topSubjectsStr.split(","));
            apiAuthors.add(new Api_Author(key, type, name, alternateNames, birthDate, topWork, workCount, topSubjects));
        }
        return apiAuthors;
    }


}
