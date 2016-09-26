package com.semion.zk;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by heshuanxu on 2016/7/15.
 */
public class ZKAPITest {


    private final static Logger logger = LoggerFactory.getLogger(ZKAPITest.class);

    private static final int SESSION_TIMEOUT = 10000;

    private static final String ZK_PATH = "/djNode";

    private ZooKeeper zk = null;

    private CountDownLatch latch = new CountDownLatch(1);

    /**
     * 创建连接
     * @param connectString
     * @param sessionTimeout
     */
    public void createConnection(String connectString, int sessionTimeout) {
        try {
            zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    logger.info("回调通知路径："+ event.toString());
                    // 连接建立时, 打开latch, 唤醒wait在该latch上的线程
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        logger.info("zk连接已创建 state:{}",event.getState());
                        latch.countDown();
                    }
                }
            });
            //等待连接建立
            latch.await();
        } catch (Exception e ) {
            logger.error( "连接创建失败:{}",e);
        }
    }

    /**
     * 关闭ZK连接
     */
    public void releaseConnection() {
        try {
            this.zk.close();
        } catch (InterruptedException e) {
        }
    }

    /**
     * 创建数据节点
     * @param path
     * @param data
     * @return
     */
    public boolean createPath( String path, String data ) {
        try {

            String s = this.zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            logger.info("=====创建结果："+s);

        } catch (Exception e ) {
            logger.error("err{}",e);
        }
        return true;
    }

    /**
     * 读取指定节点数据内容
     * @param path 节点path
     * @return
     */
    public String readData(String path ) {
        try {
            return new String( this.zk.getData( path, true, null ) );
        } catch (Exception e ) {
            return "";
        }
    }

    public boolean deleteData(String path ) {
        try {
             this.zk.delete(path,0);
        } catch (Exception e ) {
           logger.error("删除异常");
            return false;
        }
        return true;
    }

    public boolean existsData(String path ) {
        try {
             this.zk.exists(path, new Watcher() {
                 @Override
                 public void process(WatchedEvent watchedEvent) {
                     logger.info("exists=========="+watchedEvent.toString());
                 }
             });
        } catch (Exception e ) {
           logger.error("existsData method 异常");
            return false;
        }
        return true;
    }



    public static void main(String[] args) throws Exception {

        ZKAPITest obj = new ZKAPITest();

        obj.createConnection("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",SESSION_TIMEOUT);

        boolean path = obj.createPath(ZK_PATH, "init node Data");


        if (path){
            boolean b = obj.existsData(ZK_PATH);
            if(b){
                logger.info("当前node 存在数据");
            }

            String data = obj.readData(ZK_PATH);
            logger.info("读取数据内容："+ data);


            // 删除
            Boolean bool =  obj.deleteData(ZK_PATH);
            logger.info("删除数据结果：{}",bool);

            String data2 = obj.readData(ZK_PATH);
            logger.info("读取数据内容2："+ data2);
        }
       /* synchronized (ZKAPITest.class){
                while (true){
                    ZKAPITest.class.wait();
                }
        }*/
    }


}