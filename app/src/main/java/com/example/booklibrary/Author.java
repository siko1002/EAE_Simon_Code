package com.example.booklibrary;

import java.util.List;

public class Author {
    private int pId;
    private String Vorname;
    private String Nachname;
    public List<Book> WrittenBooks;

    public Author(String vorname, String nachname) {
        this.Vorname = vorname;
        this.Nachname = nachname;
    }
    public Author(String vorname, String nachname, int id) {
        this.Vorname = vorname;
        this.Nachname = nachname;
        this.pId = id;
    }

    public Author(Author author) {
        this.Vorname = author.getVorname();
        this.Nachname = author.getNachname();
        this.WrittenBooks = author.getBooks();
        this.pId = author.getpId();
    }

    public int getpId() {
        return pId;
    }

    public String getVorname() {
        return Vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setVorname(String vName) {
        this.Vorname = vName;
    }

    public void setNachname(String nName) {
        this.Nachname = nName;
    }

    public void AddBooks(List<Book> books) {
        this.WrittenBooks.addAll(books);
    }

    public void AddBook(Book book) {
        this.WrittenBooks.add(book);
    }

    public List<Book> getBooks() {
        return WrittenBooks;
    }
    @Override
    public String toString(){
        return ("ID: " + this.pId + ", Vorname: " + this.Vorname + ", Nachname: " + this.Nachname);
    }
}
