package com.company.objects;

public class book {
    private String title;
    private int isbn;
    private String author;
    private String genre;
    private String owner;

    public book(String title, int isbn, String author, String genre, String owner) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.owner = owner;
    }

    @Override
    public String toString(){
        return title + ", " + isbn + ", " + author + ", " + genre + ", " + owner;
    }
}
