package com.app.obedtandadjaja.bible;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import com.example.obedtandadjaja.bible.R;

import java.util.ArrayList;

/**
 * Created by obedtandadjaja on 11/23/15.
 */
public class Comparison extends Fragment {

    private final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private SharedPreferencesLibrary spl;
    private Chapter chapter;
    private BookDataSource book_data_source;
    private ListView verse_list;
    private VerseListComparisonAdapter verse_list_adapter;
    private Button prev, next, index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.putInt(STATE_SELECTED_POSITION, 3);
        }
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
                if (chapter.getChapter() != book_data_source.getNumChapters(chapter.getBook_name()).size()) {
                    spl.setBibleIndex(new Chapter(chapter.getBook_name(), (chapter.getChapter() + 1)));
                    Fragment fragment = new Bible();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        ArrayList<String> verse_array1 = book_data_source.getIndonesianVerses(chapter);
        for(int i = 0; i < verse_array1.size(); i++)
        {
            if(verse_array1.get(i) == null || verse_array1.get(i).equals(""))
            {
                verse_array1.remove(i);
            }
        }
        ArrayList<String> verse_array2 = book_data_source.getEnglishVersesESV(chapter);
        for(int i = 0; i < verse_array2.size(); i++)
        {
            if(verse_array2.get(i) == null || verse_array2.get(i).equals(""))
            {
                verse_array2.remove(i);
            }
        }
        verse_list_adapter = new VerseListComparisonAdapter(verse_array1, verse_array2, getActivity());
        verse_list.setAdapter(verse_list_adapter);

//        final ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();

//        verse_list.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            private int mLastFirstVisibleItem;
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//            }
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
//                if (mLastFirstVisibleItem < firstVisibleItem)
//                {
//                    if (actionBar.isShowing())
//                    {
//                        actionBar.hide();
//                    }
//                }
//                if (mLastFirstVisibleItem > firstVisibleItem)
//                {
//                    if (!actionBar.isShowing())
//                    {
//                        actionBar.show();
//                    }
//                }
//                mLastFirstVisibleItem = firstVisibleItem;
//            }
//        });

        return rootView;
    }
}
