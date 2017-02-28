package com.lbjfan.testdemo.manager;

import android.content.Context;
import android.graphics.Bitmap;

import com.lbjfan.testdemo.R;
import com.lbjfan.testdemo.api.IRequestClient;
import com.lbjfan.testdemo.api.impl.RequestClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import okhttp3.OkHttpClient;

public class AppManager {

    private static AppManager appManager;
    private Context appContext;
    private IRequestClient requestClient;
    private OkHttpClient okHttpClient;

    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    public void appInit(Context context){
        appContext = context;
        initHttp();
        initImageLoader(context);
    }


    private void initHttp() {
        if (requestClient == null) {
            synchronized (IRequestClient.class) {
                if (requestClient == null) {
                    requestClient = new RequestClient(appContext, getOkHttpClientInstance());
                }
            }
        }
    }


    public OkHttpClient getOkHttpClientInstance() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }

        return okHttpClient;
    }

    /**
     * 初始化图片加载组件 ImageLoader
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        BitmapDisplayer bitmapDisplayer = new FadeInBitmapDisplayer(context.getResources().getInteger(android.R.integer.config_shortAnimTime), true, false, false);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .displayer(bitmapDisplayer)
                .resetViewBeforeLoading(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                .showImageOnLoading(R.mipmap.ic_launcher)            //加载图片时的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)         //没有图片资源时的默认图片
                .showImageOnFail(R.mipmap.ic_launcher)
                .build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context.getApplicationContext());
        builder.memoryCacheSize(1024 * 1024);
        builder.defaultDisplayImageOptions(displayImageOptions);
        builder.threadPoolSize(5);
        builder.denyCacheImageMultipleSizesInMemory();
        builder.memoryCacheExtraOptions(2048, 2048);
        builder.memoryCacheSizePercentage(50);
        ImageLoaderConfiguration imageLoaderConfiguration = builder.threadPriority(Thread.MAX_PRIORITY).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public IRequestClient getRequestClient() {
        return requestClient;
    }

    public Context getAppContext(){
        return appContext;
    }
}
