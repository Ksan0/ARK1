package com.mycompany.ksan0.translator.activities.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.mycompany.ksan0.translator.R;

import com.mycompany.ksan0.translator.activities.activities.FragmentsController;
import com.mycompany.ksan0.translator.activities.core.LangItem;
import com.mycompany.ksan0.translator.activities.core.LangItemsController;
import com.mycompany.ksan0.translator.activities.fragments.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import static com.mycompany.ksan0.translator.R.*;


public class LanguagesListFragment extends Fragment implements AbsListView.OnItemClickListener {
    public static final String LANG_ARRAY_KEY = "LANG_ARRAY_KEY";

    private FragmentsController controller;

    private AbsListView mListView;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            ArrayList<LangItem> items = (ArrayList<LangItem>) args.get(LANG_ARRAY_KEY);
            mAdapter = new ArrayAdapter<LangItem>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, items);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_languageslistfragment, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            controller = (FragmentsController) activity;
        } catch (ClassCastException e) {
            controller = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        controller = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (controller != null) {
            Bundle b = new Bundle();
            b.putInt("Pos", position);
            Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_translate);
            if (fragment == null) {
                fragment = new TranslateFragment();
            }
            fragment.setArguments(b);
            controller.setFragment(fragment);
        }
    }

}
