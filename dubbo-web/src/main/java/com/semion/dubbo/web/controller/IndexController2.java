package com.semion.dubbo.web.controller;

import com.semion.dubbo.web.beanScope.PrototypeObj;
import com.semion.dubbo.web.beanScope.SingletonObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by heshuanxu on 2016/9/26.
 * 显示当IndexController2单例(默认单例) prototypeObj本身为多例，
 * 但是由于IndexController2为单例，prototypeObj只能初始化一次，为了显示多例
 * 需要IndexController2 实现ApplicationContextAware接口，每次从applicationContext中获取prototypeObj对象
 * 每次获取的对象都不一样
 *
 *
 */
@Controller
public class IndexController2 implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(IndexController2.class);

    private ApplicationContext applicationContext;

    private PrototypeObj prototypeObj;

    @Autowired
    private SingletonObj singletonObj;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/test/demo.html")
    public ModelAndView index(HttpServletRequest request){
        logger.info("请求首页面开始+++++++++++++");
        print();
        ModelAndView view = new ModelAndView("index");
        logger.info("请求首页面结束+++++++++++++");
        return view;
    }


    public void print() {
        // 无论什么时候都一样
        logger.info("first  time singleton is :" + singletonObj);
        logger.info("second time singleton is :" + singletonObj);
        // prototypeObj 对象每次都从application中获取，每次获取的都不一样，为多例模型。第一次和第二次获取的不一样
        logger.info("first  time prototype is :" + getPrototypeObj());
        logger.info("second time prototype is :" + getPrototypeObj());
    }

    public PrototypeObj getPrototypeObj() {
        return applicationContext.getBean(PrototypeObj.class);
    }

    public void setPrototypeObj(PrototypeObj prototypeObj) {
        this.prototypeObj = prototypeObj;
    }
}
