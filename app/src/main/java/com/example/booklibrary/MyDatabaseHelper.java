package com.example.booklibrary;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

public class MyDatabaseHelper {
    Context context;

    MyDatabaseHelper() {
    }

    public void addBook(String title, String authorName, String pages) {

        Log.i("HSKL", "MyDBHelper => addBook => Title: " + title + ", Author: " + authorName + ", Pages: " + pages);
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        //Name
        String vorname = NameSplitter.SplitVorname(authorName);
        String nachname = NameSplitter.SplitNachname(authorName);
        Author author = new Author(vorname, nachname);
        //Autor und Buch hinzufügen
        Log.i("HSKL", "MyDBHelper -> AddData Author: " + author.toString());
        if (!author_dbHelper.AuthorExists(authorName)) {
            Log.i("HSKL", "MyDBHelper -> AddData -> Author Existiert bereits?: " + author_dbHelper.AuthorExists(authorName));
            author_dbHelper.addAuthor(vorname, nachname);
        }else{
            Log.i("HSKL", "MyDBHelper -> AddData -> Author Existiert bereits?: " + author_dbHelper.AuthorExists(authorName));
        }
        Book book = new Book(title, author, pages);
        book_dbHelper.addBook(title, authorName, Integer.parseInt(pages));


    }
    Author getAuthorByName(String vorname, String nachname){
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        return author_dbHelper.getAuthorByName(vorname, nachname);
    }
    Cursor readAllData() {
        /*Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Cursor ret = book_dbHelper.readAllBooks();
        if(ret.moveToFirst()) {
            return ret;
        }else {
            return null;
        }*/
        Book_DBHelper ret = new Book_DBHelper(context);
        return ret.readAllBooks();
    }

    void updateData(String title, String new_author, String new_pages, String new_title) {
        Log.i("JETZT", "MyDatabaseHelper => updateData aufgerufen: Title_Old: " + title + ", Title_New: " + new_title + ", Pages_New: " + new_pages + ", Author_new: " + new_author);
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);

        String vorname = NameSplitter.SplitVorname(new_author);
        String nachname = NameSplitter.SplitNachname(new_author);
        if(!author_dbHelper.AuthorExists(new_author)){
            author_dbHelper.addAuthor(vorname, nachname);
        }

        book_dbHelper.updateData(title, author_dbHelper.getAuthorByName(vorname, nachname), new_pages, new_title);
    }
    void updateAuthor(String vorname, String nachname, String new_vorname, String new_nachname){
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        author_dbHelper.updateData(vorname,nachname, new_vorname, new_nachname);

    }

    //Todo
    void deleteOneBook(String title) {
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        book_dbHelper.deleteOne(title);
    }
    void deleteOneAuthor(String vorname, String nachname){
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        author_dbHelper.deleteAuthor(vorname, nachname);
    }

    MyDatabaseHelper(Context context) {
        this.context = context;
    }

    public void logAllData() {
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        book_dbHelper.logAllData();
        author_dbHelper.logAllData();
    }
    List<Book> getAllBooksAsList(){
        Book_DBHelper book_dbHelper = new Book_DBHelper(context);
        return book_dbHelper.getAllBooksAsList();
    }
    List<Author> getAllAuthorsAsList(){
        Author_DBHelper author_dbHelper = new Author_DBHelper(context);
        return author_dbHelper.getAllAuthorsAsList();
    }
}
