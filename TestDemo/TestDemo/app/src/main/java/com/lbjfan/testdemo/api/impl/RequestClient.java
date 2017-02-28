package com.lbjfan.testdemo.api.impl;

import android.content.Context;

import com.lbjfan.testdemo.api.IRequestClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestClient implements IRequestClient {

    public RequestHandler requestHandler;
    private Context context;
    private OkHttpClient okHttpClient;

    @Override
    public void getTestListInfo(RequestListener listener) {
        getRequest(0, URLConstants.SERVER_URL, listener);
    }

    public RequestClient(Context context, OkHttpClient okHttpClient) {
        requestHandler = new RequestHandler();
        this.context = context;
        this.okHttpClient = okHttpClient;
    }

    private void getRequest(final int urlType, String url, final RequestListener listener) {
        Call callBack = okHttpClient.newCall(new Request.Builder().url(url).build());
        callBack.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onRequestFailed(0, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
//                    MyLog.i("IRequestClient:" + response.body().string());
                    try {
                        Object result = requestHandler.combineObject(urlType, new JSONObject(response.body().string()));
                        listener.onRequestSucceed(result);
                    } catch (JSONException e) {
                        listener.onRequestFailed(response.code(), response.message());
                        e.printStackTrace();
                    } catch (IOException e) {
                        listener.onRequestFailed(response.code(), response.message());
                        e.printStackTrace();
                    }
                } else {
                    listener.onRequestFailed(response.code(), response.message());
                }
            }
        });
    }

    private void postRequest(final int urlType, String url, FormBody formBody, final RequestListener listener) {
        Call callBack = okHttpClient.newCall(new Request.Builder().url(url).post(formBody).build());
        callBack.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onRequestFailed(0, e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        Object result = requestHandler.combineObject(urlType, new JSONObject(response.body().string()));
                        listener.onRequestSucceed(result);
                    } catch (JSONException e) {
                        listener.onRequestFailed(response.code(), response.message());
                        e.printStackTrace();
                    } catch (IOException e) {
                        listener.onRequestFailed(response.code(), response.message());
                        e.printStackTrace();
                    }
                } else {
                    listener.onRequestFailed(response.code(), response.message());
                }
            }
        });
    }

}
