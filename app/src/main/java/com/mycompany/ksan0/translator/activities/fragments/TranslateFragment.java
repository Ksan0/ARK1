package com.mycompany.ksan0.translator.activities.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.mycompany.ksan0.translator.R;
import com.mycompany.ksan0.translator.activities.activities.FragmentsController;
import com.mycompany.ksan0.translator.activities.core.LangItem;
import com.mycompany.ksan0.translator.activities.core.LangItemsController;

import java.util.ArrayList;


public class TranslateFragment extends Fragment {
    public static final String SELECTED_LANG_ITEM = "SELECTED_LANG_ITEM";

    private FragmentsController fragmentsController;
    private LangItem currentLangItem;

    private ArrayAdapter<String> spinnerAdapterFromLang;
    private ArrayAdapter<String> spinnerAdapterToLang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentLangItem = getArguments().getParcelable(SELECTED_LANG_ITEM);

            spinnerAdapterFromLang = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    LangItemsController.getInstance().findAllMatchesToLangTitle(currentLangItem.getToTitle())
            );
            Log.d("qqq", new Integer(spinnerAdapterFromLang.getPosition(currentLangItem.getFromTitle())).toString());
            spinnerAdapterFromLang.remove(currentLangItem.getFromTitle());
            Log.d("qqq", new Integer(spinnerAdapterFromLang.getPosition(currentLangItem.getFromTitle())).toString());
            spinnerAdapterFromLang.insert(currentLangItem.getFromTitle(), 0);
            Log.d("qqq", new Integer(spinnerAdapterFromLang.getPosition(currentLangItem.getFromTitle())).toString());

            spinnerAdapterToLang = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    LangItemsController.getInstance().findAllMatchesFromLangTitle(currentLangItem.getFromTitle())
            );
            spinnerAdapterToLang.remove(currentLangItem.getToTitle());
            spinnerAdapterToLang.insert(currentLangItem.getToTitle(), 0);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        final Spinner spinnerFromLang = (Spinner) view.findViewById(R.id.spinnerFromLang);
        spinnerFromLang.setAdapter(spinnerAdapterFromLang);
        spinnerFromLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String title = (String) parent.getAdapter().getItem(position);
                LangItem newCurrentLangItem = LangItemsController.getInstance().findBy(title, currentLangItem.getToTitle());
                if (newCurrentLangItem != null) {
                    updateLangs(newCurrentLangItem);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerToLang   = (Spinner) view.findViewById(R.id.spinnerToLang);
        spinnerToLang.setAdapter(spinnerAdapterToLang);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentsController = (FragmentsController) activity;
        } catch (ClassCastException e) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentsController = null;
    }

    private void updateLangs(LangItem newCurrentLangItem) {
        currentLangItem = newCurrentLangItem;

        ArrayAdapter<String> spinnerArrayAdapterFromLang = (ArrayAdapter<String>) spinnerAdapterFromLang;
        spinnerArrayAdapterFromLang.clear();
        spinnerArrayAdapterFromLang.addAll(LangItemsController.getInstance().findAllMatchesToLangTitle(currentLangItem.getToTitle()));

        ArrayAdapter<String> spinnerArrayAdapterToLang = (ArrayAdapter<String>) spinnerAdapterToLang;
        spinnerArrayAdapterToLang.clear();
        spinnerArrayAdapterToLang.addAll(LangItemsController.getInstance().findAllMatchesFromLangTitle(currentLangItem.getFromTitle()));
    }
}
