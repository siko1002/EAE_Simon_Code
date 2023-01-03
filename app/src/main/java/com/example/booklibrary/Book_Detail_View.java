package com.example.booklibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Book_Detail_View extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mAuthorsTextView;
    private ImageView mBookCoverImageView;
    private TextView mNumberOfPagesMedianTextView;
    private TextView mFirstPublishYearTextView;
    private TextView mIsbnTextView;

    String title, author, pages, isbn, first_publish, cover_id;
    byte[] coverImage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_view);
        mTitleTextView = findViewById(R.id.detail_title);
        mAuthorsTextView = findViewById(R.id.detail_author);
        mNumberOfPagesMedianTextView = findViewById(R.id.detail_pages);
        mIsbnTextView = findViewById(R.id.detail_isbn);
        mFirstPublishYearTextView = findViewById(R.id.detail_publish_date);
        mBookCoverImageView = findViewById(R.id.detail_cover_image);
        getIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);

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

            //Setting Intent Data
            mTitleTextView.setText("Title: " + title);
            mAuthorsTextView.setText("Author: " + author);
            mNumberOfPagesMedianTextView.setText("Pages: " + pages);
            mIsbnTextView.setText("ISBN: " + isbn);
            mFirstPublishYearTextView.setText("First published Year: " + first_publish);

            Bitmap coverImageBitmap = BitmapFactory.decodeByteArray(coverImage, 0, coverImage.length);
            mBookCoverImageView.setImageBitmap(coverImageBitmap);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
