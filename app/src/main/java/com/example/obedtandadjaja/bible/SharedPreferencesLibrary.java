package com.example.obedtandadjaja.bible;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by obedtandadjaja on 8/5/15.
 */
public class SharedPreferencesLibrary {

    Context context;

    final String PREF_NAME = "BIBLE";
    final String BOOK_NAME = "BOOK_NAME";
    final String CHAPTER = "CHAPTER";
    final String MODE = "MODE";
    final String LANGUAGE = "LANGUAGE";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SharedPreferencesLibrary(Context context)
    {
        this.context = context;
    }

    public void setBibleIndex(Chapter chapter)
    {
        editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(BOOK_NAME, chapter.getBook_name());
        editor.putInt(CHAPTER, chapter.getChapter());
        editor.commit();
    }

    public Chapter getBibleIndex()
    {
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String book_name = sp.getString(BOOK_NAME, null);
        int chapter = sp.getInt(CHAPTER, 0);
        return new Chapter(book_name, chapter);
    }

    public void setLanguage(boolean bool)
    {
        editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(LANGUAGE, bool);
        editor.commit();
    }

    public boolean getLanguage()
    {
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean bool = sp.getBoolean(LANGUAGE, false);
        return bool;
    }

    public void setMode(boolean bool)
    {
        editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(MODE, bool);
        editor.commit();
    }

    public boolean getMode()
    {
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean bool = sp.getBoolean(MODE, false);
        return bool;
    }

}
