package com.lbjfan.testdemo.util;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

/**
 * Created by ${lbjfan} on 17-2-28.
 */
public class ImageLoadUtils {

    public static void showImage(String url, final ImageView imageView, final int width) {
        ImageLoader.getInstance().displayImage(url, imageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                if (loadedImage != null) {
                    int height = loadedImage.getHeight() * width / loadedImage.getWidth();
                    Bitmap bitmap = Bitmap.createScaledBitmap(loadedImage, width, height, false);
                    imageView.setImageBitmap(bitmap);
//                    recyclerBitmap(loadedImage);
                }
            }
        });
    }

    public static void recyclerBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        System.gc();
    }
}
