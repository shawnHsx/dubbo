import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by heshuanxu on 2016/6/2.
 */
public class TestAPI {

    private final static Logger logger = LoggerFactory.getLogger(TestAPI.class);

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/dubbo-server.xml");
        context.start();
        logger.trace("服务启动完成-----trace");
        logger.debug("服务启动完成----debug");
        logger.info("服务启动完成-----info");
        logger.warn("服务启动完成------warn");
        logger.error("服务启动完成-----error");
        // hold 本地服务
        synchronized (TestAPI.class){
            while (true){
                try {
                    TestAPI.class.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }
}


