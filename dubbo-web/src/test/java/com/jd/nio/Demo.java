package com.jd.nio;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by heshuanxu on 2016/9/14.
 */
public class Demo {
    /**
     *   IO                NIO
        面向流            面向缓冲
        阻塞IO            非阻塞IO
        无                选择器
     *
     */

    public static void main(String args[]) throws Exception {
        //文件705字节
        FileInputStream fis = new FileInputStream("E://myclass/Demo.java");
        // 获取通道
        FileChannel channel = fis.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(512);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        StringBuffer sb = new StringBuffer();
        int len =0;
        while (true){
            buffer.clear();
            len =  channel.read(buffer);
            if(len==-1){
                break;
            }
            buffer.flip();
            byte[] array = buffer.array();// 此处的数组会缓存上一次的数据，若第二次的数据不能读满数组，则数组中会有上一次的数据

            baos.write(array);
        }

        byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes));
        // System.out.println("position:"+buffer.position());
       // System.out.println("limit:"+buffer.limit());
       // System.out.println("capacity:"+buffer.capacity());
    }

}
