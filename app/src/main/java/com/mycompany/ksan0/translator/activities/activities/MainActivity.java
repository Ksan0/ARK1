package com.mycompany.ksan0.translator.activities.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.mycompany.ksan0.translator.R;
import com.mycompany.ksan0.translator.activities.core.LangItem;
import com.mycompany.ksan0.translator.activities.core.LangItemsController;
import com.mycompany.ksan0.translator.activities.fragments.SplashFragment;

import java.util.ArrayList;


public class MainActivity extends Activity implements FragmentsController, LangItemsController {

    private ArrayList<LangItem> langItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateFragment();
    }

    @Override
    public void setLangItems(ArrayList<LangItem> items) {
        langItems = items;
    }

    @Override
    public ArrayList<LangItem> getLangItems() {
        return langItems;
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main, fragment)
                .commitAllowingStateLoss();
    }

    public void updateFragment() {
        Fragment fragment = findLastFragment();
        if (fragment == null) {
            fragment = new SplashFragment();
        }

        setFragment(fragment);
    }

    private Fragment findLastFragment() {
        Fragment fragment;

        if ((fragment = getFragmentManager().findFragmentById(R.id.fragment_splash)) != null) {
            return fragment;
        }

        if ((fragment = getFragmentManager().findFragmentById(R.id.fragment_languageslist)) != null) {
            return fragment;
        }

        if ((fragment = getFragmentManager().findFragmentById(R.id.fragment_translate)) != null) {
            return fragment;
        }

        return null;
    }
}
