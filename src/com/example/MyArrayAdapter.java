package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final String[] values;

    public MyArrayAdapter(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView published = (TextView) rowView.findViewById(R.id.published);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        title.setText(values[position]);

        return rowView;
    }

}
