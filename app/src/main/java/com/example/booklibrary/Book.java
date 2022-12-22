package com.example.booklibrary;

public class Book {
    Book(String title, Author author, String pages){
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
    Book(String title, Author author, String pages, int id){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.id = id;
    }
    int id;
    String title;
    String pages;
    Author author;
    //String author;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPages() {
        return pages;
    }
    public void setPages(String pages) {
        this.pages = pages;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public void setAuthor(String vorname, String nachname){
        this.author = new Author(vorname, nachname);
    }
    @Override
    public String toString() {
        return "Book [title=" + title + ", pages=" + pages + ", author=" + author + "]";
    }

    public String getAuthorName() {
        return this.author.getVorname() + " " + this.author.getNachname();
    }
}
