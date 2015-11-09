package com.app.obedtandadjaja.bible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.obedtandadjaja.bible.R;

import java.util.ArrayList;

public class BookListAdapter extends BaseAdapter {

    ArrayList<Book> book_array;
    Context context;

    public BookListAdapter(ArrayList<Book> book_array, Context context){
        this.context = context;
        this.book_array = book_array;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return book_array.size();
    }

    @Override
    public Book getItem(int position) {
        // TODO Auto-generated method stub
        return book_array.get(position);
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
            v = mInflater.inflate(R.layout.book_list_item, parent, false);
        }

        TextView text = (TextView) v.findViewById(R.id.textView);
        text.setText(book_array.get(position).getName());
        return v;
    }

}
