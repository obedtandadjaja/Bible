package com.app.obedtandadjaja.bible;

/**
 * Created by obedtandadjaja on 8/4/15.
 */
public class Book {

    private int bookID;
    private String name;
    private int num_chapters;

    public Book(int bookID, String name, int num_chapters)
    {
        this.bookID = bookID;
        this.name = name;
        this.num_chapters = num_chapters;
    }

    public Book(String name, int num_chapters)
    {
        this.name = name;
        this.num_chapters = num_chapters;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_chapters() {
        return num_chapters;
    }

    public void setNum_chapters(int num_books) {
        this.num_chapters = num_books;
    }
}
