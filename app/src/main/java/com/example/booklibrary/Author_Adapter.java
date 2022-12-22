package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Book_Api.Author;

class Author_Adapter extends RecyclerView.Adapter<Author_Adapter.MyViewHolder> {

    private Context context;
    Activity activity;

    private ArrayList<Author> authors;

    int position;

    Author_Adapter(Activity activity, Context context, ArrayList authors) {
        this.activity = activity;
        this.context = context;
        this.authors = authors;
        MyDatabaseHelper myDB = new MyDatabaseHelper(context);
        authors.addAll(myDB.getAllAuthorsAsList());

    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_author, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.author_vorname.setText(String.valueOf(authors.get(position).getName()));
        holder.author_nachname.setText(String.valueOf(authors.get(position).getName()));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, Update_Author.class);
            intent.putExtra("vorname", String.valueOf(authors.get(position).getName()));
            intent.putExtra("nachname", String.valueOf(authors.get(position).getName()));
            activity.startActivityForResult(intent, 1);
        });


    }

    public int getItemCount() {
        return authors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mainLayout;
        TextView author_vorname, author_nachname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            author_vorname = itemView.findViewById(R.id.author_vorname);
            author_nachname = itemView.findViewById(R.id.author_nachname);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
