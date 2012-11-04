package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 10/30/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirstActivity extends FragmentActivity implements Fragment1.onListElementSelectedListener {

    public static String textKey = "FirstActivityTextKey";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        Fragment1 fragment1 = new Fragment1();

        trans.add(R.id.fragment_container, fragment1 );

        trans.commit();
    }

    public void onItemSelected(CharSequence text)
    {
        Intent intent = new Intent(this , SecondActivity.class);

        intent.putExtra(textKey, text);

        startActivity(intent);
    }
}