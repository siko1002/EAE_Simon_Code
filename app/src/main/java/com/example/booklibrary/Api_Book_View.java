package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Book_Api.Api_Book_Adapter;
import Book_Api.AuthorDBManager;
import Book_Api.Book;
import Book_Api.BookDBManager;


public class Api_Book_View extends AppCompatActivity {
    RecyclerView Api_Book_RecyclerView;

    BookDBManager myDB;
    ArrayList<Book_Api.Book> books;
    Api_Book_Adapter book_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_book_view);

        Api_Book_RecyclerView = findViewById(R.id.api_book_view_RecyclerView);
        myDB = new BookDBManager(Api_Book_View.this);
        books = new ArrayList<Book>();
        books.addAll(myDB.getAllBooks());

        book_adapter = new Api_Book_Adapter(Api_Book_View.this, null, books);
        Api_Book_RecyclerView.setAdapter(book_adapter);
        Api_Book_RecyclerView.setLayoutManager(new LinearLayoutManager(Api_Book_View.this));

    }

    //Action Bar Menü
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.author_view_menu, menu);
        return true;
    }

    //Menü Items -> Case = Auswahl -> Was soll gemacht werden
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.book_view:
                Intent intent = new Intent(Api_Book_View.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.author_edit_view:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
}
