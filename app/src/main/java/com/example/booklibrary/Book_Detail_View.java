package com.example.booklibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Book_Detail_View<Checkbox> extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mAuthorsTextView;
    private ImageView mBookCoverImageView;
    private TextView mNumberOfPagesMedianTextView;
    private TextView mFirstPublishYearTextView;
    private TextView mIsbnTextView;
    Button new_delete_button;
    CheckBox read;
    RatingBar bar;

    String title, author, pages, isbn, first_publish, cover_id;
    byte[] coverImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_view);
        read = (CheckBox) findViewById(R.id.checkBox);
        bar = (RatingBar) findViewById(R.id.ratingBar);
        mTitleTextView = findViewById(R.id.detail_title);
        mAuthorsTextView = findViewById(R.id.detail_author);
        mNumberOfPagesMedianTextView = findViewById(R.id.detail_pages);
        mIsbnTextView = findViewById(R.id.detail_isbn);
        mFirstPublishYearTextView = findViewById(R.id.detail_publish_date);
        mBookCoverImageView = findViewById(R.id.detail_cover_image);
        new_delete_button = findViewById(R.id.new_delete_button);

        getIntentData();


        SharedPreferences sharedPreferences = getSharedPreferences(title + "_MY_PREFS", MODE_PRIVATE);
        float rating = sharedPreferences.getFloat("rating", 0.0f);
        bar.setRating(rating);


        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        new_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }


    void getIntentData() {
        if (getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages") && getIntent().hasExtra("isbn") && getIntent().hasExtra("publish_date") && getIntent().hasExtra("cover_id") && getIntent().hasExtra("cover_Image")) {
            //Getting Data from Intent

            title = getIntent().getStringExtra("title");
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
            Book book = myDatabaseHelper.findBookByTitle(title);
            author = book.getAuthorName();
            pages = book.getPages();
            isbn = book.getIsbn();
            first_publish = book.getPublishDate();
            cover_id = book.getCoverId();
            coverImage = book.getCoverImage();
            Log.i("HSKL_TEST", "read: " + book.getRead());
            //Setting Intent Data
            mTitleTextView.setText("Title: " + title);
            mAuthorsTextView.setText("Author: " + author);
            mNumberOfPagesMedianTextView.setText("Pages: " + pages);
            mIsbnTextView.setText("ISBN: " + isbn);
            mFirstPublishYearTextView.setText("First published Year: " + first_publish);
            read.setChecked(book.getRead());

            Bitmap coverImageBitmap = BitmapFactory.decodeByteArray(coverImage, 0, coverImage.length);
            mBookCoverImageView.setImageBitmap(coverImageBitmap);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Book_Detail_View.this);
                Book book = myDB.findBookByTitle(title);
                myDB.deleteOneBook(book);
                Intent intent = new Intent(Book_Detail_View.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void onCheckBoxClicked(View view) {
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        Book book = myDatabaseHelper.findBookByTitle(title);
        if (read.isChecked()) {
            book.setRead(true);
            myDatabaseHelper.updateData(book);
        } else if (!read.isChecked()) {
            book.setRead(false);
            myDatabaseHelper.updateData(book);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(title + "_MY_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
// Save the current values
        editor.putFloat("rating", bar.getRating());

// Commit the changes
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences(title + "_MY_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
// Save the current values
        editor.putFloat("rating", bar.getRating());

// Commit the changes
        editor.apply();
    }
}