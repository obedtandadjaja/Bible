package com.app.obedtandadjaja.bible;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BookDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    private final String DB_NAME;
    private Context context;

    /**
     * CategoryDataSource constructor
     * initialize database
     * @param context
     */
    public BookDataSource(Context context)
    {
        this.DB_NAME = "bible.sqlite";
        this.helper = new DataBaseHelper(context, this.DB_NAME);
        this.database = helper.openDataBase();
        this.context = context;
    }

    /**
     * Get All The Books
     * @param
     * @return
     */
    public ArrayList<Book> getIndonesianBooks()
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 1;", null);
        cursor.moveToFirst();
        ArrayList<Book> books = cursorToBook(cursor);
        return books;
    }

    public ArrayList<Book> getIndonesianBooksAlphabetically()
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 1 order by name asc;", null);
        cursor.moveToFirst();
        ArrayList<Book> books = cursorToBook(cursor);
        return books;
    }

    /**
     * Get All The Books
     * @param
     * @return
     */
    public ArrayList<Book> getEnglishBooks()
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 2;", null);
        cursor.moveToFirst();
        ArrayList<Book> books = cursorToBook(cursor);
        return books;
    }

    public ArrayList<Book> getEnglishBooksAlphabetically()
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 2 order by name asc;", null);
        cursor.moveToFirst();
        ArrayList<Book> books = cursorToBook(cursor);
        return books;
    }

    public ArrayList<Book> getRelatedIndonesianBooks(String name)
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 1 and name like (\"%"+name+"%\");", null);
        cursor.moveToFirst();
        ArrayList<Book> book_array = new ArrayList<Book>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String book_name = cursor.getString(0);
            int num_chapters = cursor.getInt(1);
            book_array.add(new Book(book_name, num_chapters));
            cursor.moveToNext();
        }
        return book_array;
    }

    public ArrayList<Book> getRelatedEnglishBooks(String name)
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 2 and name like (\"%"+name+"%\") order by name asc;", null);
        cursor.moveToFirst();
        ArrayList<Book> book_array = new ArrayList<Book>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String book_name = cursor.getString(0);
            int num_chapters = cursor.getInt(1);
            book_array.add(new Book(book_name, num_chapters));
            cursor.moveToNext();
        }
        return book_array;
    }

    public ArrayList<Book> getRelatedIndonesianBooksAlphabetically(String name)
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 1 and name like (\"%"+name+"%\") order by name asc;", null);
        cursor.moveToFirst();
        ArrayList<Book> book_array = new ArrayList<Book>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String book_name = cursor.getString(0);
            int num_chapters = cursor.getInt(1);
            book_array.add(new Book(book_name, num_chapters));
            cursor.moveToNext();
        }
        return book_array;
    }

    public ArrayList<Book> getRelatedEnglishBooksAlphabetically(String name)
    {
        Cursor cursor = database.rawQuery("select name, num_books from BOOK where language_id = 2 and name like (\"%"+name+"%\");", null);
        cursor.moveToFirst();
        ArrayList<Book> book_array = new ArrayList<Book>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String book_name = cursor.getString(0);
            int num_chapters = cursor.getInt(1);
            book_array.add(new Book(book_name, num_chapters));
            cursor.moveToNext();
        }
        return book_array;
    }

    public ArrayList<Integer> getNumChapters(String name)
    {
        Cursor cursor = database.rawQuery("select num_books from BOOK where name = \""+name+"\";", null);
        cursor.moveToFirst();
        ArrayList<Integer> num_chapters = new ArrayList<Integer>();
        for(int i = 1; i < cursor.getInt(0)+1; i++)
        {
            num_chapters.add(i);
        }
        return num_chapters;
    }

    public ArrayList<String> getIndonesianVerses(Chapter chapter)
    {
        Cursor cursor = database.rawQuery("select id from BOOK where name = \""+chapter.getBook_name()+"\";", null);
        cursor.moveToFirst();
        int book_id = cursor.getInt(0);
        if(book_id > 66)
        {
            book_id -= 66;
        }
        cursor = database.rawQuery("select ind_tb from BIBLE where book_id = "+book_id+" and chapter = "+chapter.getChapter()+";", null);
        cursor.moveToFirst();
//        Cursor cursor = database.rawQuery("select ind_tb from BIBLE where book_id = (select id from BOOK where name = \"KEJADIAN\") and chapter = 1;", null);
        ArrayList<String> verse_array = new ArrayList<String>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String verse = cursor.getString(0);
            verse_array.add(verse);
            cursor.moveToNext();
        }
        return verse_array;
    }

    public ArrayList<String> getEnglishVerses(Chapter chapter)
    {
        Cursor cursor = database.rawQuery("select id from BOOK where name = \""+chapter.getBook_name()+"\";", null);
        cursor.moveToFirst();
        int book_id = cursor.getInt(0);
        if(book_id > 66)
        {
            book_id -= 66;
        }
        cursor = database.rawQuery("select eng_asv from BIBLE where book_id = "+book_id+" and chapter = "+chapter.getChapter()+";", null);
        cursor.moveToFirst();
//        Cursor cursor = database.rawQuery("select ind_tb from BIBLE where book_id = (select id from BOOK where name = \"KEJADIAN\") and chapter = 1;", null);
        ArrayList<String> verse_array = new ArrayList<String>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String verse = cursor.getString(0);
            verse_array.add(verse);
            cursor.moveToNext();
        }
        return verse_array;
    }

    /**
     * private method to get the query data into its format
     * @param cursor
     * @param
     * @return
     */
    private ArrayList<Book> cursorToBook(Cursor cursor)
    {
        ArrayList<Book> def_array = new ArrayList<Book>();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            String name = cursor.getString(0);
            int num_chapters = cursor.getInt(1);
            def_array.add(new Book(name, num_chapters));
            cursor.moveToNext();
        }
        return def_array;
    }
}
