package com.example.food.util.imagehelp;

import android.graphics.Bitmap;
import android.util.LruCache;

public class MemoryCache implements ImageCache{
    private LruCache<String, Bitmap> mMemoryCache;

    //初始化LRU缓存
    public MemoryCache(){
        initImageCache();
    }


    private void initImageCache(){
        //计算可用的最大内存
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一可用内存作缓存
        int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }


    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}



