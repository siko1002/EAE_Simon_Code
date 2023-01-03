package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Add_Author extends AppCompatActivity {

    EditText add_author_vorname, add_author_nachname;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_author);


        add_author_vorname = findViewById(R.id.add_author_vorname);
        add_author_nachname = findViewById(R.id.add_author_nachname);

        add_button = findViewById(R.id.author_add_button);
        add_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(Add_Author.this);
            if(myDB.findAuthorByName(add_author_vorname.getText().toString().trim(), add_author_nachname.getText().toString().trim()) == null){
                Author add = myDB.findAuthorByName(add_author_vorname.getText().toString().trim(), add_author_nachname.getText().toString().trim());
                myDB.addAuthor(add);
            }

            myDB.addAuthor(add_author_vorname.getText().toString().trim(), add_author_nachname.toString().trim());


            Intent intent = new Intent(Add_Author.this, MainActivity.class);
            startActivity(intent);
        });
    }
}