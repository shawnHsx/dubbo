package com.semion.dubbo.web.filter;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * Created by heshuanxu on 2016/8/18.
 * Spring 中定义的web请求拦截器接口
 */
public class SpringInterceptor implements WebRequestInterceptor {

    /**
     * 该拦截器无返回值 处理器执行前调用，不支持在拦截器中结束处理器链的操作
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void preHandle(WebRequest webRequest) throws Exception {

    }
    /**
     处理器执行后调用
     *
     */
    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
