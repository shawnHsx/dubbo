package com.semion.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by heshuanxu on 2016/9/18.
 */
public class FileCopy {

    public static void main(String args[]) throws Exception {

        fileCopy();
        //fileMapping();

    }

    /**
     * 文件读写的过程
     * @throws IOException
     */
    private static void fileCopy() throws IOException {
        FileInputStream fis = new FileInputStream("E://myclass/Demo.java");
        // 获取读取通道
        FileChannel readChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("E://myclass/demo_cpay.java");
        // 写通道
        FileChannel writeChannel = fos.getChannel();

        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(512);

        while(true){
            buffer.clear();//clear方法并不将缓冲区的数据清空，而是设置position，mark，limit这三个变量的值

            System.out.println("初始值：position:"+buffer.position());
            System.out.println("初始值：limit:"+buffer.limit());
            System.out.println("初始值：capacity:"+buffer.capacity());

            int len = readChannel.read(buffer);// 读取文件

            System.out.println("读取后：position:"+buffer.position());
            System.out.println("读取后：limit:"+buffer.limit());
            System.out.println("读取后：capacity:"+buffer.capacity());

            if(len==-1){// 读到结束
                break;
            }

            buffer.flip();//重值缓冲区

            System.out.println("flip之后：position:"+buffer.position());
            System.out.println("flip之后：limit:"+buffer.limit());
            System.out.println("flip之后：capacity:"+buffer.capacity());

            writeChannel.write(buffer);// 写入文件

            System.out.println("写入后：position:"+buffer.position());
            System.out.println("写入后：limit:"+buffer.limit());
            System.out.println("写入后：capacity:"+buffer.capacity());
        }
        readChannel.close();
        writeChannel.close();
        fis.close();
        fos.close();
    }

    /**
     * 文件内存映射
     * @throws IOException
     */
    private static void fileMapping() throws IOException {

        RandomAccessFile raf = new RandomAccessFile("E://myclass/Demo.java", "rw");

        FileChannel fc = raf.getChannel();
        // 将文件映射到内存中
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0,raf.length());
        while (mbb.hasRemaining()) {
            System.out.print((char) mbb.get());
        }
        mbb.put(0, (byte) 98); // 修改文件  对MappedByteBuffer的修改就相当于修改文件本身，这样操作的速度是很快的。
        raf.close();
    }
}
