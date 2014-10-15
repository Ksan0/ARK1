package com.mycompany.ksan0.translator.activities.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.mycompany.ksan0.translator.activities.activities.FragmentsController;
import com.mycompany.ksan0.translator.activities.core.LangItem;
import com.mycompany.ksan0.translator.activities.core.LangItemsController;
import com.mycompany.ksan0.translator.activities.fragments.LanguagesListFragment;
import com.mycompany.ksan0.translator.activities.network.helpers.RequestHelper;
import com.mycompany.ksan0.translator.activities.network.helpers.ResponseHelper;
import com.mycompany.ksan0.translator.activities.network.helpers.URLHelper;

import java.util.ArrayList;


public class AsyncTaskInitDownload extends AsyncTask<Void, Void, Void> {
    FragmentsController fragmentsController;
    LangItemsController langItemsController;
    TextView outputProgress;

    public AsyncTaskInitDownload(FragmentsController fragmentsController, LangItemsController langItemsController, TextView outputProgress) {
        this.fragmentsController = fragmentsController;
        this.langItemsController = langItemsController;
        this.outputProgress = outputProgress;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String response = RequestHelper.makeEmptyGetRequest(URLHelper.getLangs());
        ArrayList<LangItem> langItems = new ArrayList<LangItem>();
        ResponseHelper.parseResponse(response, langItems);
        langItemsController.setLangItems(langItems);
        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {

    }


    @Override
    protected void onPostExecute(Void result) {
        if (fragmentsController != null) {
            LanguagesListFragment fragment = new LanguagesListFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(LanguagesListFragment.LANG_ARRAY_KEY, langItemsController.getLangItems());
            fragment.setArguments(args);
            fragmentsController.setFragment(fragment);
        }
    }
}
