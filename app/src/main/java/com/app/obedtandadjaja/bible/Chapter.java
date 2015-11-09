package com.app.obedtandadjaja.bible;

/**
 * Created by obedtandadjaja on 8/5/15.
 */
public class Chapter {

    private int chapter;
    private String book_name;

    public Chapter(String book_name, int chapter)
    {
        this.book_name = book_name;
        this.chapter = chapter;
    }

    public String toString()
    {
        return this.book_name+" "+this.chapter;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
