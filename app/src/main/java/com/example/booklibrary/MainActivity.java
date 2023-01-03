package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Book_Api.Api_Book;
import Book_Api.Api_Book_Adapter;
import Book_Api.BookDBManager;
import Book_Api.BookRequestApi;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<Book> books;
    Book_Adapter bookAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.book_recyclerView);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_Book.class);
                startActivity(intent);
            }
        });

        books = new ArrayList<Book>();
        myDB = new MyDatabaseHelper(MainActivity.this);
        //myDB.deleteAll();

        //myAdapter = new MyAdapter(MainActivity.this, this, book_title, book_author, book_pages);
        bookAdapter = new Book_Adapter(MainActivity.this, this, books);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.on_long_click_menue, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.on_long_click_Edit_Book:
                Toast.makeText(this, "Edit Selected: " + getIntent().hasExtra("title") , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Update_Book.class);
                startActivity(intent);
                return true;
            case  R.id.on_long_cloick_Delete_Book:
                Intent intent2 = new Intent(MainActivity.this, Update_Book.class);
                startActivity(intent2);
                Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //Action Bar Menü
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Menü Items -> Case = Auswahl -> Was soll gemacht werden
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Author_view:
                Intent intent = new Intent(MainActivity.this, Author_View.class);
                startActivity(intent);
                return true;
            case R.id.Edit_view:
                BookDBManager myDB = new BookDBManager(this);
                //Testaufruf
                Intent intent1 = new Intent(MainActivity.this, Api_Book_View.class);
                startActivity(intent1);
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