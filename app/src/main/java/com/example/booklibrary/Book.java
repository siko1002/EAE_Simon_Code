package com.example.booklibrary;

public class Book {
    private int bookID;
    private String title;
    private String pages;
    final private Author author;
    boolean read = false;

    public Book(String title, Author author, String pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    public Book(String title, Author author, String pages, int bookId) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.bookID = bookId;
    }

    public Book(Book book) {
        this.bookID = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.pages = book.getPages();
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean getRead() {
        return read;
    }

    public Author getAuthor() {
        return author;
    }
    public String getAuthorName() {
        return  this.author.getVorname() + " " + this.author.getNachname();
    }

   /* public void setAuthor(Author author) {
        this.author = new Author(author);
    }*/

    public int getBookId() {
        return bookID;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
