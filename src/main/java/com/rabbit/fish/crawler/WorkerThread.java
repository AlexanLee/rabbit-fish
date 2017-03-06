package com.rabbit.fish.crawler;

/**
 * Created by lijianli on 2017/3/2.
 */
public class WorkerThread implements Runnable {
    private long low=1;
    private long high=1;
    private String url="";
    WorkerThread(long low,long high,String url){
        this.low=low;
        this.high=high;
        this.url=url;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start.");
        System.out.println("low:"+low+" high"+high);
        Toutiao toutiao=new Toutiao();
        toutiao.pull(low,high,url);
    }
}
