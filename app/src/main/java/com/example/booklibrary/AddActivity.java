package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText insert_title, insert_author, insert_pages;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        insert_title = findViewById(R.id.insert_title);
        insert_author = findViewById(R.id.insert_author);
        insert_pages = findViewById(R.id.insert_pages);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
            Log.i("HSKL", "AddActivity => AddButton => Title: " + insert_title.getText().toString().trim() + ", Author: " + insert_author.getText().toString().trim() + ", Pages: " + insert_pages.getText().toString().trim());
            myDB.addBook(insert_title.getText().toString().trim(),
                    insert_author.getText().toString().trim(),
                    insert_pages.getText().toString().trim());

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}