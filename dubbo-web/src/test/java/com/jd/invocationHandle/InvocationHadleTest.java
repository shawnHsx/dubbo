package com.jd.invocationHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by heshuanxu on 2016/6/6.
 * 通过jdk自带的InvocationHadle 实现AOP代理切面织入
 * 属于非侵入式编程本身就是侵入式
 *
 * 缺点：性能不高，只能代理实现了接口的类  推荐使用 cglib
 *
 */
public class InvocationHadleTest {

    private final static Logger logger = LoggerFactory.getLogger(InvocationHadleTest.class);

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, FileNotFoundException {
        // 通过反射获取目标对象实例
        Hello hello= (Hello) Class.forName("com.jd.invocationHandle.HelloImpl").newInstance();

        // 获取aop切面类（在目标方法前后需要做的动作）
        InvocationHandler handler = new AopFactory(hello);// 目标对象通过构造方法传入代理类

        // 生成代理对象
        /**
         * ClassLoader loader, 类加载器 主要有三种，具体见类加载器内容
         * Class<?>[] interfaces, 所有接口
         * InvocationHandler h 实现InvocationHandler的子类
         */
        Hello proxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(),handler);

        // 通过代理对象调用方法
        proxy.setInfo("hello", "world");
    }


}
