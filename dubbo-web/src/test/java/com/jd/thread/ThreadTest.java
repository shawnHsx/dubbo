package com.jd.thread;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java自带线程池 ---创建5个线程打印List集合
 * Created by heshuanxu on 2016/6/6.
 */
public class ThreadTest {


    public static void main(String[] args){
        List<String> strList = new ArrayList<String>();

        for (int i = 0; i <100; i++) {
            strList.add("String"+i);
        }
        // 线程个数
        int threadNum = strList.size()<5?strList.size():5;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,threadNum,300, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(3));

        for (int i = 0; i < threadNum; i++) {
            executor.execute(new PrintStrThread(strList,i,threadNum));
        }
        //shutdown只是给队列的线程发interrupt 不能接受新任务 但等待老任务结束
          executor.shutdown();
        // 立即结束线程
        executor.shutdownNow();
    }


}


class PrintStrThread implements Runnable{

    /**
     * 目标list
     */
    private List<String> strList;

    /**
     * 线程编号
     */
    private int num;
    /**
     * 线程数
     */
    private int threadNum;

    public PrintStrThread(List<String> strList, int num, int threadNum) {
        this.strList = strList;
        this.num = num;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        int len = 0;
        if(!CollectionUtils.isEmpty(this.strList)){
            for (int i = 0; i < strList.size(); i++) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String s = strList.get(i);
                // 线程满足该规则时打印集合中的数据
                if (len % this.threadNum == this.num){
                    System.out.println("线程编号："+this.num+",字符串："+s);
                }
                len++;
            }
        }
    }
}