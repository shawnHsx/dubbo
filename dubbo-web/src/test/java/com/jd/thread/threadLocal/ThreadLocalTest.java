package com.jd.thread.threadLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heshuanxu on 2016/6/12.
 */
public class ThreadLocalTest {

    private final static Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] args) throws InterruptedException {

        Sequence sequence = new SequenceImpl2();

        Thread t1 = new Thread(new ThreadA(sequence));
        Thread t2 = new Thread(new ThreadA(sequence));
        Thread t3 = new Thread(new ThreadA(sequence));

        

        t1.start();

        t2.start();

        t3.start();

        /**
         * 以上三个线程共享同一个static变量--出现非线程安全问题
         */
    }


}


interface Sequence{
    int getNum();
}

class SequenceImpl implements Sequence{

    private static int number = 0;

    @Override
    public int getNum() {
        number= number+1;
        return number;
    }
}


class SequenceImpl2 implements Sequence{

    private final static Logger logger = LoggerFactory.getLogger(SequenceImpl2.class);

    /**
     * 此处重写initialValue(属于ThreadLocal类)方法，默认返回为null；实现了共享static变量 但是每个线程都是独立的，不会发生非线程安全问题
     *
     * numContainer 维护了线程局部变量的Map类型，initialValue 方法是需要开发者来实现的，获取变量的初始值。
     */
    private static ThreadLocal<Integer> numContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    @Override
    public int getNum() {
        numContainer.set(numContainer.get()+1);
        return numContainer.get();
    }
}



class ThreadA implements Runnable{


    private final static Logger logger = LoggerFactory.getLogger(ThreadA.class);

    private Sequence sequence;


    public ThreadA(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            logger.info("thread Name:"+Thread.currentThread().getName()+"===>"+sequence.getNum());
        }
    }
}
