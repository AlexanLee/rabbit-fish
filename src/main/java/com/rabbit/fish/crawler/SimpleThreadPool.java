package com.rabbit.fish.crawler;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
/**
 * Created by lijianli on 2017/3/2.
 */
public class SimpleThreadPool {
    //51860163920
    private final static long START =51860800000L;//50926800000L//26700000
    private final static long END =51860850000L;//1300000//509270000000//51860176000L
    private final static int STEP =1000;
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
