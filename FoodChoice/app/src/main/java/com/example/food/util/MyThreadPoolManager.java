package com.example.food.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolManager {
    //双重效验锁
    private volatile static MyThreadPoolManager threadManager;
    public static MyThreadPoolManager getInstance(){
        if (threadManager==null){
            synchronized (MyThreadPoolManager.class){
                if (threadManager==null){
                    threadManager = new MyThreadPoolManager();
                }
            }
        }
        return threadManager;
    }

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime = 1;
    private TimeUnit unit = TimeUnit.HOURS;
    private ThreadPoolExecutor executor;

    private MyThreadPoolManager(){
        corePoolSize = Runtime.getRuntime().availableProcessors()*2+1;
        maximumPoolSize= corePoolSize;
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new LinkedBlockingDeque<Runnable>(),
                //线程的工厂
                Executors.defaultThreadFactory(),
                //超出任务的处理策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void execute(Runnable runnable){
        if (executor==null){
            executor = new ThreadPoolExecutor(
                    corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>(),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
                    );
        }
        if (runnable!=null){
            executor.execute(runnable);
        }
    }

    public void remove(Runnable runnable){
        if (runnable!=null){
            executor.remove(runnable);
        }
    }

}
