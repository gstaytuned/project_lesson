package com.inthecheesefactory.thecheeselibrary.manager.http;

/**
 * Created by nuuneoi on 10/20/2014.
 */
public interface HTTPRequestListener {

    public void onMessageReceived(int statusCode, final String message);
    public void onMessageError(int statusCode, final String message);

}
