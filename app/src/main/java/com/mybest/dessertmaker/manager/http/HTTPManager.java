package com.mybest.dessertmaker.manager.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HTTPManager {

    private static HTTPManager instance;

    public static HTTPManager getInstance() {
        if (instance == null)
            instance = new HTTPManager();
        return instance;
    }

    private Context mContext;
    private APIService mService;

    private HTTPManager() {
        mContext = Contextor.getInstance().getContext();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://nuuneoi.com/courses/dessert_maker")
                .setConverter(new GsonConverter(gson))
                .build();
        mService = restAdapter.create(APIService.class);
    }

    public APIService getAPIService() {
        return mService;
    }
}
