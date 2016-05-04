package com.mybest.dessertmaker.manager.http;

import com.mybest.dessertmaker.dao.DessertItemCollectionDao;


import retrofit.Callback;
import retrofit.http.POST;


/**
 * Created by worapong on 7/9/2015 AD.
 */
public interface APIService {
    @POST("/list")
    void loadDessertList(Callback<DessertItemCollectionDao> cb);


}
