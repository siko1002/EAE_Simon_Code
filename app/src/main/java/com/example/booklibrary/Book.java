package com.example.booklibrary;

import java.util.Arrays;

public class Book {
    public Book(String title, Author author, String pages, String isbn, String publishDate, String coverId, byte[] coverImage){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.coverId = coverId;
        this.coverImage = coverImage;
    }
    Book(String title, Author author, String pages, String isbn, String publishDate, String coverId, byte[] coverImage, int id){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.coverId = coverId;
        this.coverImage = coverImage;
        this.id = id;
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
    String isbn;
    String publishDate;
    String coverId;
    byte[] coverImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages='" + pages + '\'' +
                ", author=" + author +
                ", isbn='" + isbn + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", coverId='" + coverId + '\'' +
                ", coverImage=" + Arrays.toString(coverImage) +
                '}';
    }
    public String getAuthorName(){
        if(this.author != null) {
            return this.author.getVorname() + " " + this.author.getNachname();
        }else{
            return "Author unbekannt";
        }
    }
}
