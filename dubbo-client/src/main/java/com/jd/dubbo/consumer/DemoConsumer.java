package com.jd.dubbo.consumer;

import com.jd.dubbo.provider.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import static org.slf4j.LoggerFactory.*;

/**
 * Created by heshuanxu on 2016/6/3.
 */
public class DemoConsumer {
    private final  static Logger logger = getLogger(DemoConsumer.class);

    static {
        logger.info("初始化工作。。。。++++++++++++++++++++++++++++++++++++++++++++++");
    }
    // dubbo 架构中的Proxy，由Proxy 调用对应的Invoker（）
    private DemoService demoService;

    public DemoConsumer(DemoService demoService) {
        this.demoService = demoService;
    }

    public String callRpc(String words){
        logger.info("开始调用方法----------------------------------------");
        String s = demoService.sayHI("你好啊 RPC dubbo 。。。" + words);
        logger.info("PRC 返回结果：{}",s);
        logger.info("结束调用方法----------------------------------------");
        return s;
    }


    public DemoService getDemoService() {
        return demoService;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}
