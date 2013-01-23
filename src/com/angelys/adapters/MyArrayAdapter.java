package com.angelys.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.angelys.R;
import com.angelys.objects.ArticleCollection;

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

        ViewHolder viewHolder = null;

	    if(convertView == null)
        {
            convertView =  inflater.inflate(R.layout.rowlayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.published = (TextView) convertView.findViewById(R.id.published);

            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(values.get(position).getTitle());

        String p = values.get(position).getPublished();

        viewHolder.published.setText(p.substring(0,10) + " " + p.substring(11,16));

        return convertView;
    }

    public static class ViewHolder
    {
        TextView title;
        TextView published;
    }

}
