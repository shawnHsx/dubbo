package com.jd.invocationHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heshuanxu on 2016/6/6.
 */
public class HelloImpl implements Hello {

    private final static Logger logger = LoggerFactory.getLogger(HelloImpl.class);

    private String infos1;

    private String infos2;

    @Override
    public String getinfos1() {
        return this.infos1;
    }

    @Override
    public String getinfos2() {
        return this.infos2;
    }

    @Override
    public void setInfo(String info1, String info2) {
        this.infos1 = info1;
        this.infos2 = info2;
    }

    @Override
    public void dispaly() {
        logger.info("infos:{},infos2:{}",infos1,infos2);
    }
}
