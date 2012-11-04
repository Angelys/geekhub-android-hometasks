package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/1/12
 * Time: 3:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fragment2 extends Fragment {

    onButtonClickListener mCallBack;

    CharSequence text;

    interface onButtonClickListener
    {
        public void onButtonClicked(View view);
    }

    public Fragment2(CharSequence string)
    {
        text = string;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
        {
            Activity activity = getActivity();
        }

        return inflater.inflate(R.layout.fragment2, container, false);
    }

    public void setText(CharSequence string)
    {
        TextView tv = (TextView)getView().findViewById(R.id.fragment2_text_view);

        tv.setText(string);

        TextView title = (TextView)getView().findViewById(R.id.fragment2_title);

        title.setText("This is second fragment");
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallBack = (onButtonClickListener) activity;
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
                mCallBack.onButtonClicked(view);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Bundle asd = outState;
    }

}
