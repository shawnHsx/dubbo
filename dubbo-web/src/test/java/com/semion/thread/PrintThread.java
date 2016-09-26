package com.semion.thread;

/**
 * Created by heshuanxu on 2016/9/13.
 */
public class PrintThread implements Runnable {

    String threadName;

    public PrintThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {

        System.out.println(threadName +" is running.......");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName +" is again.......");
    }
}
