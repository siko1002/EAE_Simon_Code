package com.example.booklibrary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Book extends AppCompatActivity {

    EditText insert_title, insert_author, insert_pages;
    Button update_button;
    Button delete_button;

    String title, author, pages, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        insert_title = findViewById(R.id.insert_title2);
        insert_author = findViewById(R.id.insert_author2);
        insert_pages = findViewById(R.id.insert_pages2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        getIntentData();
        ActionBar ab = getSupportActionBar();
            ab.setTitle(title);
        update_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Book.this);
            Book book = myDB.findBookByTitle(getIntent().getStringExtra("title"));
            String new_title = insert_title.getText().toString();
            String new_author = insert_author.getText().toString();
            String new_pages = insert_pages.getText().toString();
            book.setTitle(new_title);
            Author author = book.getAuthor();//myDB.findAuthorByName(getIntent().getStringExtra("author"));
            author.setVorname(NameSplitter.SplitVorname(new_author));
            author.setNachname(NameSplitter.SplitNachname(new_author));
            myDB.updateAuthor(author);
            book.setAuthor(author);
            book.setPages(new_pages);

            Log.i("HSKL", "UpdateActivity => onCreate => values -> " + "Titel: " + title + ", Author: " + author + ", Pages: " + pages);
            Log.i("HSKL", "UpdateActivity => onCreate => new_values -> Titel: " + new_title + ", Author: " + new_author + ", Pages: " + new_pages);
            myDB.updateData(book);
            Intent intent = new Intent(Update_Book.this, MainActivity.class);
            startActivity(intent);
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               confirmDialog();
            }
        });

    }

    void getIntentData(){
        if (getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            insert_title.setText(title);
            insert_author.setText(author);
            insert_pages.setText(pages);
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
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Book.this);
                Book book = myDB.findBookByTitle(title);
                myDB.deleteOneBook(book);
                Intent intent = new Intent(Update_Book.this, MainActivity.class);
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
}