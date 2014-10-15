package com.mycompany.ksan0.translator.activities.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.ksan0.translator.R;
import com.mycompany.ksan0.translator.activities.activities.FragmentsController;


public class TranslateFragment extends Fragment {
    private FragmentsController controller;

    int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pos = getArguments().getInt("Pos");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        TextView textView = (TextView) view.findViewById(R.id.hello_blank_id);
        textView.setText(new Integer(pos).toString());
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
