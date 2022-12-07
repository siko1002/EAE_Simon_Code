package com.example.booklibrary;

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

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList book_title, book_author, book_pages;

    int position;

    MyAdapter(Activity activity, Context context, ArrayList book_title, ArrayList book_author, ArrayList book_pages){
        this.activity = activity;
        this.context = context;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        this.position=position;
        holder.book_pages_text.setText(String.valueOf(book_pages.get(position)));
        holder.book_author_text.setText(String.valueOf(book_author.get(position)));
        holder.book_title_text.setText(String.valueOf(book_title.get(position)));
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("pages", String.valueOf(book_pages.get(position)));
            intent.putExtra("author", String.valueOf(book_author.get(position)));
            intent.putExtra("title", String.valueOf(book_title.get(position)));
            activity.startActivityForResult(intent, 1);
        });


    }

    public int getItemCount(){
        return book_title.size();
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
