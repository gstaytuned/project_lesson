package com.inthecheesefactory.thecheeselibrary.manager.http;

/**
 * Created by nuuneoi on 10/20/2014.
 */

import com.squareup.okhttp.Call;

import java.util.Map;

public class HTTPRequestData {

    public String url;
    public HTTPEngine.RequestMethod method;
    public Map<String, String> postData;
    public Call call;

}
