package com.example.booklibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> book_title, book_author, book_pages, book_id, books;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        //Todo
        books = new ArrayList<>();

        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        book_id = new ArrayList<>();

        displayData();

        //myAdapter = new MyAdapter(MainActivity.this, this, book_title, book_author, book_pages);
        myAdapter = new MyAdapter(MainActivity.this, this, books);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
        displayData();
    }

    void displayData() {
        myDB.logAllData();

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                /*String vorname = NameSplitter.SplitVorname(cursor.getString(2));
                String nachname = NameSplitter.SplitNachname(cursor.getString(2));
                Author author = new Author(vorname, nachname);

                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                String pages = cursor.getString(3);
                Book book = new Book(title, author, pages, id);*/
            }
        }
    }
}