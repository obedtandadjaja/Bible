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

public class VerseListComparisonAdapter extends BaseAdapter {

    ArrayList<String> verse_array1;
    ArrayList<String> verse_array2;
    Context context;

    public VerseListComparisonAdapter(ArrayList<String> verse_array1, ArrayList<String> verse_array2, Context context){
        this.context = context;
        this.verse_array1 = verse_array1;
        this.verse_array2 = verse_array2;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return verse_array1.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return verse_array1.get(position);
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
            v = mInflater.inflate(R.layout.verse_list_comparison_item, parent, false);
        }
        else
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.verse_list_comparison_item, parent, false);
        }

        if(position == 0)
        {
            TextView verseNumber = (TextView) v.findViewById(R.id.verseNumber);
            verseNumber.append(Html.fromHtml("<br/><small><font color='grey'>" + (position + 1) + "</font></small>"));

            TextView text1 = (TextView) v.findViewById(R.id.textView1);
            text1.append(Html.fromHtml("<br/><big>" + verse_array1.get(position) + " </big><br/>"));
            TextView text2 = (TextView) v.findViewById(R.id.textView2);
            text2.append(Html.fromHtml("<br/><big>" + verse_array2.get(position) + " </big><br/>"));
        }
        else
        {
            TextView verseNumber = (TextView) v.findViewById(R.id.verseNumber);
            verseNumber.append(Html.fromHtml("<small><font color='grey'>" + (position + 1) + "</font></small>"));

            TextView text1 = (TextView) v.findViewById(R.id.textView1);
            text1.append(Html.fromHtml("<big>" + verse_array1.get(position) + " </big><br/>"));
            TextView text2 = (TextView) v.findViewById(R.id.textView2);
            text2.append(Html.fromHtml("<big>" + verse_array2.get(position) + " </big><br/>"));
        }

        return v;
    }

}
