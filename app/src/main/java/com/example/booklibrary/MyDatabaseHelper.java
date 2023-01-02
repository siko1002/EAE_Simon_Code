package com.example.booklibrary;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

//import Book_Api.Author;
//import Book_Api.Book;

public class MyDatabaseHelper {
    Context context;

    MyDatabaseHelper() {
    }

    public void addBook(Book book) {


        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        Log.i("HSKL", "MyDBHelper -> AddData Book: " + book.toString() );
        Author author = book.getAuthor();
        Log.i("HSKL", "MyDBHelper -> AddData Author: " + author.toString() );
        //Name
        String authorName = author.toStringDB();
        //Autor und Buch hinzufügen
        Log.i("HSKL", "MyDBHelper -> AddData Author: " + author.toString());
        if (!author_dbHelper.AuthorExists(authorName)) {
            Log.i("HSKL", "MyDBHelper -> AddData -> Author Existiert bereits?: " + author_dbHelper.AuthorExists(authorName));
            author_dbHelper.addAuthor(author);
        } else {
            Log.i("HSKL", "MyDBHelper -> AddData -> Author Existiert bereits?: " + author_dbHelper.AuthorExists(authorName));
        }
        if(book.getPages().contains(": ")){
            String temp = book.getPages().substring(book.getPages().indexOf('.') + 1);
            Log.i("HSKL", "MyDBHelper => addBook => Title: " + book.getTitle() + ", Author: " + authorName + ", Pages: " + book.getPages());
            book_dbHelper.addBook(book);
        }else {
            Log.i("HSKL", "MyDBHelper => addBook => Title: " + book.getTitle() + ", Author: " + authorName + ", Pages: " + book.getPages());
            book_dbHelper.addBook(book);
        }
    }

    public void addAuthor(String vorname, String nachname) {
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        if (author_dbHelper.findAuthorByName(vorname, nachname) != "") {
            author_dbHelper.addAuthor(vorname, nachname);
        }
    }

    Author getAuthorByName(String vorname, String nachname) {
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        return author_dbHelper.getAuthorByName(vorname, nachname);
    }

    Cursor readAllData() {
        Book_DBHelper ret = new Book_DBHelper(context);
        return ret.readAllBooks();
    }

    void updateData(Book book) {
        Log.i("JETZT", "MyDatabaseHelper => updateData aufgerufen: Title_Old: " + book.toString());
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        book_dbHelper.updateData(book);
    }

    void updateAuthor(String vorname, String nachname, String new_vorname, String new_nachname) {
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        author_dbHelper.updateData(vorname, nachname, new_vorname, new_nachname);

    }

    //Todo
    void deleteOneBook(Book book) {
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        book_dbHelper.deleteOne(book);
    }

    void deleteOneAuthor(String vorname, String nachname) {
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        author_dbHelper.deleteAuthor(vorname, nachname);
    }

    public MyDatabaseHelper(Context context) {
        this.context = context;
    }

    public void logAllData() {
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        book_dbHelper.logAllData();
        author_dbHelper.logAllData();
    }

    public List<Book> getAllBooksAsList() {
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        return book_dbHelper.getAllBooksAsList();
    }

    public List<Author> getAllAuthorsAsList() {
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        return author_dbHelper.getAllAuthorsAsList();
    }
    public Book findBookByTitle(String title){
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Book ret = book_dbHelper.findByTitle(title);
        return ret;
    }
    public void deleteAll(){
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        author_dbHelper.deleteDB();
        book_dbHelper.deleteDB();
    }
    Book getBook(String title){
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        return book_dbHelper.findByTitle(title);
    }
}
