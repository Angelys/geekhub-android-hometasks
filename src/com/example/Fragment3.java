package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/1/12
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fragment3 extends Fragment {

    onSecondButtonClickListener mCallBack;

    CharSequence text;

    interface onSecondButtonClickListener
    {
        public void onSecondButtonClicked(View view);
    }

    public Fragment3(CharSequence string)
    {
        text = string;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment2, container, false);
    }

    public void setText(CharSequence string)
    {
        View view = getView();

        TextView tv = (TextView)getView().findViewById(R.id.fragment2_text_view);

        tv.setText(string);

        TextView title = (TextView)getView().findViewById(R.id.fragment2_title);

        title.setText("This is third fragment");
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallBack = (onSecondButtonClickListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onButtonClickListener interface");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        setText(text);

        Button btn = (Button) getView().findViewById(R.id.fragment2_button);

        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                mCallBack.onSecondButtonClicked(view);
            }
        });

    }
}
