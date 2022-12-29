package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import Book_Api.Api_Book_Adapter;
import Book_Api.BookRequestApi;

public class Add_Book extends AppCompatActivity {

    EditText insert_title;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);


        insert_title = findViewById(R.id.insert_title);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(Add_Book.this);
            Log.i("HSKL", "AddActivity => AddButton => Title: " + insert_title.getText().toString().trim());

            BookRequestApi bookRequestApi = new BookRequestApi(this);
            bookRequestApi.execute(insert_title.getText().toString().trim());
            
            try {
                bookRequestApi.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Intent intent = new Intent(Add_Book.this, MainActivity.class);
            insert_title.setText("");
            startActivity(intent);
        });
    }
}