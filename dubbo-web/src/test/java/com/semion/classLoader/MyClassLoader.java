package com.semion.classLoader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by heshuanxu on 2016/9/12.
 * 类加载器的执行过程 loadClass->findClass->defineClass
 *
 */
public class MyClassLoader extends ClassLoader {

    private String name;// 类加载器的名称

    private String myClassPath;// 类加载路径

    public MyClassLoader(String name,String myClassPath) {
        this.name = name;
        this.myClassPath = myClassPath;
    }

    public MyClassLoader(String name, ClassLoader loader) {
        super(loader); // 通过这个这个构造方法生成的类加载器，该加载器的父加载器是loader,如果为空，则父加载器为根加载器
                       // 子类继承父类，如果不显式写出调用父类的哪个构造方法，那么就默认调用父类的无参构造函数
        this.name = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取要被加载类文件
        byte[] data = getClazzData(name);

        return this.defineClass(data, 0, data.length);
    }

    private byte[] getClazzData(String name) {
        String path = myClassPath+ name +".class";
        System.out.println("类加载的路径：" + path);
        try {
            InputStream inputStream = new FileInputStream(path);

            BufferedInputStream bis = new BufferedInputStream(inputStream);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            int len;
            while((len = bis.read())!=-1){
                baos.write(len);
            }
            // 返回字节数组
            return baos.toByteArray();

        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] ars) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader","e://myclass/");

        Class<?> demo = myClassLoader.loadClass("Demo");

        System.out.println("ClassLoader:"+demo.getClass().getClassLoader().getClass().getName());

        System.out.println(demo.newInstance());

    }


}
