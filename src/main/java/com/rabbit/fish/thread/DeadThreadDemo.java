package com.rabbit.fish.thread;

/**
 *
 * @author lijianli
 * @date 2017/12/24
 */
public class DeadThreadDemo {
    private static String A="A";
    private static String B="B";

    public static void main(String []args){
        new DeadThreadDemo().deadLock();
    }

    private void deadLock(){
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("starting thread 1...");
                synchronized (A){
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("starting thread 2...");
                synchronized (B){
                    synchronized (A){
                        System.out.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
