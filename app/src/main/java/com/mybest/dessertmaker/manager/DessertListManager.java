package com.mybest.dessertmaker.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.mybest.dessertmaker.dao.DessertItemCollectionDao;
import com.mybest.dessertmaker.dao.DessertItemDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DessertListManager {

    private static DessertListManager instance;

    public static DessertListManager getInstance() {
        if (instance == null)
            instance = new DessertListManager();

        return instance;
    }

    private Context mContext;

    private DessertItemCollectionDao dao;
    private DessertItemDao selectedItem;

    public DessertItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(DessertItemCollectionDao dao) {
        this.dao = dao;
        saveData();
    }

    public DessertItemDao getSelectedItem(){
        return selectedItem;
    }

    public void setSelectedItem(DessertItemDao selectedItem) {
        this.selectedItem = selectedItem;
    }

    private void saveData() {
        String json = new Gson().toJson(dao);

        SharedPreferences prefe = mContext
                .getSharedPreferences("dessert",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefe.edit();
        editor.putString("json",json);

        editor.apply();
    }

    private void loadData() {

        SharedPreferences prefe = mContext
                .getSharedPreferences("dessert",Context.MODE_PRIVATE);

        String json = prefe.getString("json",null);

        if(json == null)
            return;

        dao = new Gson().fromJson(json,DessertItemCollectionDao.class);

    }


    private DessertListManager() {
        mContext = Contextor.getInstance().getContext();
        loadData();
    }

}
