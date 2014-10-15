package com.mycompany.ksan0.translator.activities.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.mycompany.ksan0.translator.R;
import com.mycompany.ksan0.translator.activities.activities.FragmentsController;
import com.mycompany.ksan0.translator.activities.core.LangItem;


public class TranslateFragment extends Fragment {
    public static final String SELECTED_LANG_ITEM = "SELECTED_LANG_ITEM";

    private FragmentsController fragmentsController;
    private LangItem currentLangItem;

    private SpinnerAdapter spinnerAdapterFromLang;
    private SpinnerAdapter spinnerAdapterToLang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentLangItem = getArguments().getParcelable(SELECTED_LANG_ITEM);
        }

        //spinnerAdapterFromLang = new ArrayAdapter<String>()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        Spinner spinnerFromLang = (Spinner) view.findViewById(R.id.spinnerFromLang);
        Spinner spinnerToLang   = (Spinner) view.findViewById(R.id.spinnerToLang);

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


}
