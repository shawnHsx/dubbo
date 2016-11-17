package com.semion.dubbo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by heshuanxu on 2016/11/17.
 */
@Path("/service")
//@Consumes("{application/json}")
//@Produces("{application/json}")
public interface DemoRestService {

    @POST
    @Path("/say")
    public String sayHI(String words);


}
