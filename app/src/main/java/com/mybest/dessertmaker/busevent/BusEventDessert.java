package com.mybest.dessertmaker.busevent;

import com.mybest.dessertmaker.dao.DessertItemDao;

/**
 * Created by worapong on 7/10/2015 AD.
 */
public class BusEventDessert {

    DessertItemDao dao;

    public BusEventDessert(DessertItemDao dao) {
        this.dao = dao;
    }

    public DessertItemDao getDao() {
        return dao;
    }

    public void setDao(DessertItemDao dao) {
        this.dao = dao;
    }
}
