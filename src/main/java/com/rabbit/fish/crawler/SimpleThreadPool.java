package com.rabbit.fish.crawler;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/**
 * Created by lijianli on 2017/3/2.
 */
public class SimpleThreadPool {
    private final static long START =2660000;//50926800000L
    private final static long END =2670000;//1300000//509270000000
    private final static int STEP =2500;
    private final static int THREAD_NUM=(int)(END-START)/STEP;
    public static void main(String[] args) {
        String[] url = new String[]{"https://it.snssdk.com/dongtai/list/v10/?" +
                "user_id=%s" +
                "&max_cursor=%s",
                "https://is.snssdk.com/dongtai/list/v10/?" +
                        "user_id=%s" +
                        "&max_cursor=%s",
        };
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);
        long low= START;
        long high;
        int k=0;
        for (long i = START; i< END; i+= STEP){
            high= STEP *(i/ STEP +1);
            System.out.println(low+":"+high);
            Runnable worker = new WorkerThread(low,high,url[(k++)%url.length]);
            executor.execute(worker);
            low= STEP *(i/ STEP +1);
        }
        System.out.println("Finish");
        executor.shutdown();
    }
}
