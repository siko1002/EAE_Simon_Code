package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import Book_Api.Book;

class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.MyViewHolder> {

    private Context context;
    Activity activity;
    // private ArrayList book_title, book_author, book_pages;
    private ArrayList<Book> books;

    int position;

    Book_Adapter(Activity activity, Context context, ArrayList books) {
        this.activity = activity;
        this.context = context;
        this.books = books;

        MyDatabaseHelper myDB = new MyDatabaseHelper(context);
        books.addAll(myDB.getAllBooksAsList());

        for (int i = 0; i < books.size(); i++) {
            Log.i("BTest", books.get(i).toString());
        }
        Log.i("HSKL", "books.toString() -> " + books.toString());

    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_book, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.book_pages_text.setText(String.valueOf(books.get(position).getPages()));
        holder.book_author_text.setText(String.valueOf(books.get(position).getAuthorName()));
        holder.book_title_text.setText(String.valueOf(books.get(position).getTitle()));
        // Set the cover image
        if(books.get(position).getCoverImage() != null) {
            Bitmap coverImageBitmap = BitmapFactory.decodeByteArray(books.get(position).getCoverImage(), 0, books.get(position).getCoverImage().length);
            holder.book_cover.setImageBitmap(coverImageBitmap);
        }


        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, Update_Book.class);
            intent.putExtra("pages", String.valueOf(books.get(position).getPages()));
            intent.putExtra("author", String.valueOf(books.get(position).getAuthorName()));
            intent.putExtra("title", String.valueOf(books.get(position).getTitle()));
            intent.putExtra("isbn", String.valueOf(books.get(position).getIsbn()));
            intent.putExtra("publish_date", String.valueOf(books.get(position).getPublishDate()));
            intent.putExtra("cover_id", books.get(position).getCoverId());
            intent.putExtra("cover_Image", books.get(position).getCoverImage());
            activity.startActivityForResult(intent, 1);
        });


    }

    public int getItemCount() {
        return books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_title_text, book_author_text, book_pages_text;
        ImageView book_cover;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_cover = itemView.findViewById(R.id.book_cover_image_view);
            book_author_text = itemView.findViewById(R.id.book_author_text);
            book_title_text = itemView.findViewById(R.id.book_title_text);
            book_pages_text = itemView.findViewById(R.id.book_pages_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
