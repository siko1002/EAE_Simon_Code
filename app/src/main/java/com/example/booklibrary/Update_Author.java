package com.example.booklibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Update_Author extends AppCompatActivity{

    EditText insertVorname, insertNachname;
    Button update_button;
    Button delete_button;

    String vorname, nachname, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_author);

        insertVorname = findViewById(R.id.author_insert_vorname);
        insertNachname = findViewById(R.id.author_insert_nachname);
        update_button = findViewById(R.id.author_update_button);
        delete_button = findViewById(R.id.author_delete_button);
        getIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(vorname + " " + nachname);
        update_button.setOnClickListener(view -> {
            String new_vorname = insertVorname.getText().toString();
            String new_nachname = insertNachname.getText().toString();

            MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Author.this);
            myDB.updateAuthor(vorname, nachname, new_vorname, new_nachname);
            Intent intent = new Intent(Update_Author.this, Author_View.class);
            startActivity(intent);
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getIntentData() {
        if (getIntent().hasExtra("vorname") && getIntent().hasExtra("nachname")) {
            vorname = getIntent().getStringExtra("vorname");
            nachname = getIntent().getStringExtra("nachname");

            insertVorname.setText(vorname);
            insertNachname.setText(nachname);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + vorname + " " + nachname + " ?");
        builder.setMessage("Are you sure you want to delete " +vorname + " " + nachname + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_Author.this);
                myDB.deleteOneAuthor(vorname, nachname);
                Intent intent = new Intent(Update_Author.this, Author_View.class);
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