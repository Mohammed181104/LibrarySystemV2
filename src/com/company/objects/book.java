package com.company.objects;

public class book {
    private String title;
    private int isbn;
    private String author;
    private String genre;

    public book(String title, int isbn, String author, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString(){
        return title + ", " + isbn + ", " + author + ", " + genre;
    }
}
