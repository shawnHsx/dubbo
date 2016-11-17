package com.semion.dubbo.rest.impl;

import com.semion.dubbo.provider.DemoService;
import com.semion.dubbo.rest.DemoRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by heshuanxu on 2016/11/17.
 */
@Controller
public class DemoRestServiceImpl implements DemoRestService {

    private static final Logger logger = LoggerFactory.getLogger(DemoRestServiceImpl.class);

    @Resource(name ="demoService")
    private DemoService demoService;

    @Override
    public String sayHI(String words) {
        logger.info("parms:{}",words);
        return demoService.sayHI(words);
    }
}
