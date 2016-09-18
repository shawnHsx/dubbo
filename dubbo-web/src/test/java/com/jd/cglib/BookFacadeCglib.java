package com.jd.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by heshuanxu on 2016/6/7.
 *
 * 使用cglib动态代理
 * 代理类：BookFacadeImpl
 */
public class BookFacadeCglib implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(BookFacadeCglib.class);

    private Object target;// 被代理对象

    /**
     * 创建代理对象 -- 实现被代理对象的子类
     *
     *         原理就是用Enhancer生成一个原有类的子类，并且设置好callback ，
     *         则原有类的每个方法调用都会转成调用实现了MethodInterceptor接口的proxy的intercept()；
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法 织入逻辑（原有方法的增强）
        // this:代表BookFacadeCglib，BookFacadeCglib-->MethodInterceptor-->Callback
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 回调方法 拦击所有被调用父类的方法
     * @param o
     * @param method
     * @param args  // 方法参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logger.info("开启事务");
        Object o1 = methodProxy.invokeSuper(o, args);
        logger.info("提交事务");
        return o1;
    }
}
