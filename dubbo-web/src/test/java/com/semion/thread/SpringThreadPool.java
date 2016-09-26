package com.semion.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by heshuanxu on 2016/6/6.
 * spring 线程池配置
 */
public class SpringThreadPool {


   static ThreadPoolTaskExecutor poolTaskExecutor = null;

    /**
     * spring 中直接调用线程池
     */

    static{
        // 执行任务的线程池
        poolTaskExecutor = new ThreadPoolTaskExecutor();
        // 线程池的缓冲队列
        poolTaskExecutor.setQueueCapacity(2);
        // 线程池维护的核心线程数量
        poolTaskExecutor.setCorePoolSize(5);
        // 线程池维护的最大线程数
        poolTaskExecutor.setMaxPoolSize(5);
        // 线程池维护线程所允许的空闲时间 30秒  当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止
        poolTaskExecutor.setKeepAliveSeconds(3000);
        //对拒绝任务的处理策略 三种策略 1,直接抛出；2,主线程直接执行该任务
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        poolTaskExecutor.initialize();

    }


    public static void main(String[] args){
        for (int i = 0; i < 50; i++) {
            poolTaskExecutor.execute(new PrintThread("t"+i));
        }

        //检查活动的线程，如果活动线程数为0则关闭线程池
        int flag = 0;
        while(flag==0){
            int count = poolTaskExecutor.getActiveCount();
            int keepAliveSeconds = poolTaskExecutor.getKeepAliveSeconds();

            System.out.println("Active Threads : " + count);
            System.out.println("keepAliveSeconds : " + keepAliveSeconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(count==0){
                poolTaskExecutor.shutdown();
                flag=1;
            }
        }

    }

}
