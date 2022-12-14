package com.example.booklibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Toast;

import java.util.ArrayList;

import android.view.MenuInflater;

//import Book_Api.Book;

class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList<Book> books;
    public Book selectedBook;
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
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(context);
        Book book = myDatabaseHelper.findBookByTitle(books.get(position).getTitle());
        if(book.getCoverImage() != null) {

            holder.book_cover.setImageBitmap(BitmapFactory.decodeByteArray(book.getCoverImage(), 0, book.getCoverImage().length));
        }

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, Book_Detail_View.class);
            intent.putExtra("pages", String.valueOf(books.get(position).getPages()));
            intent.putExtra("author", String.valueOf(books.get(position).getAuthorName()));
            intent.putExtra("title", String.valueOf(books.get(position).getTitle()));
            intent.putExtra("isbn", String.valueOf(books.get(position).getIsbn()));
            intent.putExtra("publish_date", String.valueOf(books.get(position).getPublishDate()));
            intent.putExtra("cover_id", books.get(position).getCoverId());
            intent.putExtra("cover_Image", books.get(position).getCoverImage());
            activity.startActivityForResult(intent, 1);
        });

        holder.mainLayout.setOnCreateContextMenuListener(holder);
    }


    public int getItemCount() {
        return books.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements OnCreateContextMenuListener {

        TextView book_title_text, book_author_text, book_pages_text;
        ImageView book_cover;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_cover = itemView.findViewById(R.id.book_imageView);
            book_author_text = itemView.findViewById(R.id.book_author_text);
            book_title_text = itemView.findViewById(R.id.book_title_text);
            book_pages_text = itemView.findViewById(R.id.book_pages_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            position = getAdapterPosition();
            MenuInflater inflater = ((Activity) v.getContext()).getMenuInflater();
            inflater.inflate(R.menu.on_long_click_menue, menu);
            selectedBook = books.get(position);
        }

    }
}
