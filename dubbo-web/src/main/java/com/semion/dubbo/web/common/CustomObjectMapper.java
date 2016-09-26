package com.semion.dubbo.web.common;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heshuanxu on 2016/8/19.
 *  解决SpringMVC使用@ResponseBody返回json时，日期格式默认显示为时间戳的问题
 */
@Component("customObjectMapper")
public class CustomObjectMapper extends ObjectMapper {

    private static final Logger logger = LoggerFactory.getLogger(CustomObjectMapper.class);

    public CustomObjectMapper() {
        logger.info("CustomObjectMapper 构造方法");
        CustomSerializerFactory factory = new CustomSerializerFactory();

        factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
            @Override
            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(date);
                logger.info("自定义方式格式化时间格式：{}",format);
                jsonGenerator.writeString(format);
            }
        });

        this.setSerializerFactory(factory);
    }
}
