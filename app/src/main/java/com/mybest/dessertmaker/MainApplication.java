package com.mybest.dessertmaker;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by worapong on 7/9/2015 AD.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Contextor.getInstance().init(getApplicationContext());
    }

}
