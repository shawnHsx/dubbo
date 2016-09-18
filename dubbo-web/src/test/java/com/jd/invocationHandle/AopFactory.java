package com.jd.invocationHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by heshuanxu on 2016/6/6.
 */
public class AopFactory implements InvocationHandler {

    private final static Logger logger = LoggerFactory.getLogger(AopFactory.class);

    private Object proxyed;


    public AopFactory(Object proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法名：{}",method.getName());

        logger.info("参数个数："+args.length);
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> typeVariables:parameterTypes){
            logger.info("参数类型名："+typeVariables.getName());
        }
        for (Object obj :args){
            logger.info("参数：{}",obj);
        }
        // 调用目标对象方法
        Object invoke = method.invoke(this.proxyed, args);
        logger.info("返回结果：{}",args);
        return invoke;
    }
}
