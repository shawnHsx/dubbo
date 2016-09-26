import com.semion.dubbo.consumer.DemoConsumer;
import com.semion.dubbo.provider.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by heshuanxu on 2016/6/2.
 */
public class ClientMain {
    private final static Logger logger = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "dubbo-client.xml" });
        context.start();

        DemoService   demoService = (DemoService) context.getBean("demoService");

        DemoConsumer demoConsumer = new DemoConsumer(demoService);


        while (true){
            try {
                long time = System.currentTimeMillis();
                Thread.sleep(5000L);
                String s = demoConsumer.callRpc("我是参数。。");
                logger.info("测试结果返回:{}",s);
                long time2 = System.currentTimeMillis();
                logger.info("总耗时：" + (time2-time-5000)+"毫秒");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
