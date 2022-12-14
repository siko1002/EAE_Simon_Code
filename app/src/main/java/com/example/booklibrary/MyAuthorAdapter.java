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

import java.lang.reflect.Array;
import java.util.ArrayList;

class MyAuthorAdapter extends RecyclerView.Adapter<MyAuthorAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    // private ArrayList book_title, book_author, book_pages;
    private ArrayList<Author> authors;

    int position;

    MyAuthorAdapter(Activity activity, Context context, ArrayList authors){
        this.activity = activity;
        this.context = context;
        this.authors = authors;
        MyDatabaseHelper myDB = new MyDatabaseHelper(context);
        authors.addAll(myDB.getAllAuthorsAsList());

    }
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_author, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position){
        this.position=position;
        holder.mainLayout.setOnClickListener(view -> {
            //Das wird aufgerufen wenn ich auf einen Author Tippe
        });


    }

    public int getItemCount(){
        return authors.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_text, book_author_text, book_pages_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            book_author_text = itemView.findViewById(R.id.book_author_text);
            book_title_text = itemView.findViewById(R.id.book_title_text);
            book_pages_text = itemView.findViewById(R.id.book_pages_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
