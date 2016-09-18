package com.jd.nio;


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
        FileInputStream fis = new FileInputStream("E://myclass/Demo.java");
        // 获取通道
        FileChannel channel = fis.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(fis.available());

        StringBuffer sb = new StringBuffer();

        channel.read(buffer);

        channel.close();

        buffer.flip();

        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        byte[] array = buffer.array();

        sb.append( new String(array));

        System.out.println(sb.toString());
    }

}
