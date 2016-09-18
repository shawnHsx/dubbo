package com.jd.classLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by heshuanxu on 2016/6/9.
 */
public class ClassLoadTest {

        public static void main(String args[]) {
            try {
                showClassloader();
                //printLoader();

            } catch (Exception ex) {
                Logger.getLogger(ClassLoadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    /**
     * 演示类加载器的 可见性  子类加载器可以看到父类加载器加载的类，反之不行。
     * @throws ClassNotFoundException
     */
    private static void showClassloader() throws ClassNotFoundException {
        //打印当前类的类加载器
        System.out.println("ClassLoadTest.getClass().getClassLoader() : " + ClassLoadTest.class.getClassLoader());// sun.misc.Launcher$AppClassLoader@20cf2c80

        //尝试使用类Extension类加载器加载当前类  运行结果报错
        Class.forName("com.jd.classLoader.ClassLoadTest", true,ClassLoadTest.class.getClassLoader().getParent());


    }

    /**
     * 打印类加载器
     */
    public static void printLoader(){
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        /**
         *
         *  sun.misc.Launcher$AppClassLoader@20cf2c80
         *  sun.misc.Launcher$ExtClassLoader@1729854
         *  null
         *
         *  我们可以判定系统类加载器的父加载器是标准扩展类加载器，
         *  但是我们试图获取标准扩展类加载器的父类加载器时确得到了null，
         *  就是说标准扩展类加载器本身强制设定父类加载器为null。
         *
         */
    }

}


