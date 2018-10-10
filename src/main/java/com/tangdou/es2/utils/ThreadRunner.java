package com.tangdou.es2.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadRunner {

    private static ExecutorService exec = new ThreadPoolExecutor(8, 15, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(500000), MyThreadFactoryBuilder.buildThreadFactory
            ("batch"),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    public static void run(Runnable command){
    	exec.execute(command);
    }
}
