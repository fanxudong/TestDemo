package com.lbjfan.testdemo.api;

public interface IRequestClient {

    void getTestListInfo(RequestListener listener);

    interface RequestListener {

        void onRequestSucceed(Object object);

        void onRequestFailed(int strType, String strMsg);
    }

    class URLConstants {
        //存放请求服务器的URL地址
        public static final String SERVER_URL = "http://thoughtworks-ios.herokuapp.com/facts.json";
    }
}
