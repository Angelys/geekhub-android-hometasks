package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.R;
import com.example.objects.ArticleCollection;
import com.example.objects.Article;
import org.json.simple.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/11/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayAdapter extends ArrayAdapter {

    private Context context;
    private ArticleCollection values;

    public MyArrayAdapter(Context context,ArticleCollection values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    public void setData(ArticleCollection data)
    {
        this.values = data;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	// TODO use recycling here to avoid inflating view every time
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView published = (TextView) rowView.findViewById(R.id.published);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        title.setText(values.get(position).getTitle());

        String p = values.get(position).getPublished();

        published.setText(p.substring(0,10) + " " + p.substring(11,16));

        return rowView;
    }

}
