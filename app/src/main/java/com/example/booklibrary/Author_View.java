package com.example.booklibrary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//Not Relevant => Could maybe Implemented in a Version 2.0
public class Author_View extends AppCompatActivity {

    RecyclerView authorRecyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> authors;
    Author_Adapter authorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_view);

        authorRecyclerView = findViewById(R.id.author_view_recyclerView);
        add_button = findViewById(R.id.author_view_add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Add_Book.class soll zu Add_Author.class
                Intent intent = new Intent(Author_View.this, Add_Author.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(Author_View.this);
        //Todo
        authors = new ArrayList<>();

        //myAdapter = new MyAdapter(MainActivity.this, this, book_title, book_author, book_pages);
        authorAdapter = new Author_Adapter(Author_View.this, this, authors);
        authorRecyclerView.setAdapter(authorAdapter);
        authorRecyclerView.setLayoutManager(new LinearLayoutManager(Author_View.this));

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
                Intent intent = new Intent(Author_View.this, MainActivity.class);
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