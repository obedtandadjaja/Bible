package com.app.obedtandadjaja.bible;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.obedtandadjaja.bible.R;

import java.util.ArrayList;

public class VerseListAdapter extends BaseAdapter {

    ArrayList<String> verse_array;
    Context context;

    public VerseListAdapter(ArrayList<String> verse_array, Context context){
        this.context = context;
        this.verse_array = verse_array;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return verse_array.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return verse_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        if (v == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.verse_list_item, parent, false);
        }
        else
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.verse_list_item, parent, false);
        }

        if(position == 0)
        {
            TextView verseNumber = (TextView) v.findViewById(R.id.verseNumber);
            verseNumber.append(Html.fromHtml("<br/><small><font color='grey'>" + (position + 1) + "</font></small>"));

            TextView text = (TextView) v.findViewById(R.id.textView);
            text.append(Html.fromHtml("<br/><big>" + verse_array.get(position) + " </big><br/>"));
        }
        else
        {
            TextView verseNumber = (TextView) v.findViewById(R.id.verseNumber);
            verseNumber.append(Html.fromHtml("<small><font color='grey'>" + (position + 1) + "</font></small>"));

            TextView text = (TextView) v.findViewById(R.id.textView);
            text.append(Html.fromHtml("<big>" + verse_array.get(position) + " </big><br/>"));
        }

        return v;
    }

}
