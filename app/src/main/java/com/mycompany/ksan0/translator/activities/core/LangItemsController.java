package com.mycompany.ksan0.translator.activities.core;

import java.util.ArrayList;


public class LangItemsController {

    private static LangItemsController instance;

    private ArrayList<LangItem> langItems;

    private LangItemsController() {

    }

    public static LangItemsController getInstance() {
        if (instance == null) {
            instance = new LangItemsController();
        }

        return instance;
    }

    public void setLangItems(ArrayList<LangItem> langItems) {
        this.langItems = langItems;
    }

    public ArrayList<LangItem> getLangItems() {
        return langItems;
    }

    //public void findAllMatchesToLang()


}
