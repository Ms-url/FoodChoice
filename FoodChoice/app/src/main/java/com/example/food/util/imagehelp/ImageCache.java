package com.example.food.util.imagehelp;

import android.graphics.Bitmap;

public interface ImageCache {
    //从缓存中取出图片
    Bitmap get(String url);

    //将图片put入缓存中
    void put(String url, Bitmap bitmap);

}
