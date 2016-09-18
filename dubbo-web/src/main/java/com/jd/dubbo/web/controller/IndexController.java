package com.jd.dubbo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by heshuanxu on 2016/6/3.
 */
@Controller
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/dubbo/demo.html")
    public ModelAndView index(HttpServletRequest request){
        logger.info("请求首页面开始+++++++++++++");
        ModelAndView view = new ModelAndView("index");
        logger.info("请求首页面结束+++++++++++++");
        return view;
    }

    @RequestMapping("/login/auth.html")
    public ModelAndView login(HttpServletRequest request){
        ModelAndView view = new ModelAndView("auth");
        return view;
    }

    @RequestMapping("/login/out.html")
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 返回json字符串
     * @param request
     * @return
     */
    @RequestMapping(value = "/dubbo/head.html",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public @ResponseBody String backStr(HttpServletRequest request){
        logger.info("进入方法：getJson");
        return "{name:\"heshuanxu\",age:40,degree:\"本科\"}";
    }

    /**
     * 返回JSON数据 此处不可以用.html 的url请求 返回的json数据浏览器无法解析，浏览器会当作text/html 处理 报406错误
     * @param request
     * @return
     */
    @RequestMapping(value = "/dubbo/getJson.action",method = RequestMethod.GET)
    @ResponseBody
    public Map backJSON(HttpServletRequest request){
        logger.info("进入方法：backJSON");
        Map map = new HashMap();
        map.put("id",100);
        map.put("title","SpringMVC实战");
        map.put("date",new Date());
        logger.info(map.toString());
        return map;
    }

    /**
     * 返回JSON数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/dubbo/json.action",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getJSON2(HttpServletRequest request){
        logger.info("使用ModelAndView 方式返回json数据");
        Map map = new HashMap();
        map.put("id",100);
        map.put("title","SpringMVC实战");
        map.put("date",new Date());
        logger.info(map.toString());
        ModelAndView view =  new ModelAndView(new MappingJacksonJsonView(),map);
        return view;
    }
}
