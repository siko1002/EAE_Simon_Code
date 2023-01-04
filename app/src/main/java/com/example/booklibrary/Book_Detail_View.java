package com.example.booklibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
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
    Boolean myBoolean1;

    String title, author, pages, isbn, first_publish, cover_id;
    byte[] coverImage;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.book_detail_view);
        super.onCreate(savedInstanceState);
        mTitleTextView = findViewById(R.id.detail_title);
        mAuthorsTextView = findViewById(R.id.detail_author);
        mNumberOfPagesMedianTextView = findViewById(R.id.detail_pages);
        mIsbnTextView = findViewById(R.id.detail_isbn);
        mFirstPublishYearTextView = findViewById(R.id.detail_publish_date);
        mBookCoverImageView = findViewById(R.id.detail_cover_image);
        new_delete_button = findViewById(R.id.new_delete_button);
        read = (CheckBox) findViewById(R.id.checkBox);
        getIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        new_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        if (savedInstanceState!=null) {
            myBoolean1 = savedInstanceState.getBoolean("read");
            read.setChecked(myBoolean1);
        }

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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?" );
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

    public void onCheckBoxClicked(View view){
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        Book book = myDatabaseHelper.findBookByTitle(title);
        if(read.isChecked()){
            book.setRead(true);
            myDatabaseHelper.updateData(book);
        } else {
            book.setRead(false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("read", read.isChecked());
    }


}
