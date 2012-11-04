package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: angelys
 * Date: 11/1/12
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecondActivity extends FragmentActivity implements Fragment2.onButtonClickListener, Fragment3.onSecondButtonClickListener {

    private static Random rand = new Random();
    public static int rand_value ;
    private FragmentManager manager = getSupportFragmentManager();
    private String text;

    private Fragment2 frag2;
    private Fragment3 frag3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second);

        FragmentTransaction trans = manager.beginTransaction();

        text =  getIntent().getExtras().getString(FirstActivity.textKey);

        frag2 = new Fragment2(text);

        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        trans.add(R.id.second_frame, frag2);

        trans.commit();
    }

    public void onButtonClicked(View view)
    {
        FragmentTransaction trans = manager.beginTransaction();

        frag3 = new Fragment3(text);

        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        trans.replace(R.id.second_frame, frag3);

        trans.addToBackStack("replace");

        trans.commit();
    }

    public void onSecondButtonClicked(View view)
    {
        rand_value = rand.nextInt();

        frag2.text = frag2.text.toString() +rand_value;

        Toast.makeText(this, String.valueOf(rand_value), Toast.LENGTH_SHORT).show();
    }
}