package com.example.obedtandadjaja.bible;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by obedtandadjaja on 8/4/15.
 */
public class Bible extends Fragment {

    SharedPreferencesLibrary spl;
    Chapter chapter;
    Menu menu;
    TextView text;
    BookDataSource book_data_source;
    ListView verse_list;
    VerseListAdapter verse_list_adapter;
    Button prev, next, index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.bible, container, false);

        spl = new SharedPreferencesLibrary(getActivity());
        chapter = spl.getBibleIndex();

        if(chapter.getChapter() == 0)
        {
            chapter = new Chapter("KEJADIAN", 1);
            spl.setBibleIndex(chapter);
            spl.setLanguage(false);
        }

        book_data_source = new BookDataSource(getActivity());

        verse_list = (ListView) rootView.findViewById(R.id.listView);
        prev = (Button) rootView.findViewById(R.id.button1);
        next = (Button) rootView.findViewById(R.id.button3);
        index = (Button) rootView.findViewById(R.id.button2);

        index.setText(chapter.toString());

        Typeface font_awesome = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        prev.setTypeface(font_awesome);
        next.setTypeface(font_awesome);

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Home();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chapter.getChapter() > 1)
                {
                    spl.setBibleIndex(new Chapter(chapter.getBook_name(), (chapter.getChapter()-1)));
                    Fragment fragment = new Bible();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chapter.getChapter() != book_data_source.getNumChapters(chapter.getBook_name()).size())
                {
                    spl.setBibleIndex(new Chapter(chapter.getBook_name(), (chapter.getChapter()+1)));
                    Fragment fragment = new Bible();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

//        if(spl.getMode())
//        {
//            verse_list.setBackgroundColor(Color.parseColor("#000000"));
//        }
//        else
//        {
//            verse_list.setBackgroundColor(Color.parseColor("#ffffff"));
//        }

        if(spl.getLanguage())
        {
            ArrayList<String> verse_array = book_data_source.getEnglishVerses(chapter);
            verse_list_adapter = new VerseListAdapter(verse_array, getActivity());
            verse_list.setAdapter(verse_list_adapter);
        }
        else
        {
            ArrayList<String> verse_array = book_data_source.getIndonesianVerses(chapter);
            verse_list_adapter = new VerseListAdapter(verse_array, getActivity());
            verse_list.setAdapter(verse_list_adapter);
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId())
//        {
//            case R.id.mode:
//                if(!spl.getMode())
//                {
//                    text.setBackgroundColor(Color.parseColor("#000000"));
//                    text.setTextColor(Color.parseColor("#ffffff"));
//                    spl.setMode(true);
//                }
//                else
//                {
//                    text.setBackgroundColor(Color.parseColor("#ffffff"));
//                    text.setTextColor(Color.parseColor("#000000"));
//                    spl.setMode(false);
//                }
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }
}
