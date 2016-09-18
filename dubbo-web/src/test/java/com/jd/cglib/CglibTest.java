package com.jd.cglib;

/**
 * Created by heshuanxu on 2016/6/7.
 * asm包已实现动态修改字节码信息，动态在内存中创建class类以及修改class信息
 * cglib 包装了asm的复杂操作。
 * 实现步骤：1.创建一个类的子类，子类方法覆盖父类方法。
 *         2.子类完成动作后，super回调
 *          CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 *         3.但因为采用的是继承，所以不能对final修饰的类进行代理。
 *
 *         原理就是用Enhancer生成一个原有类的子类，并且设置好callback ，
 *         则原有类的每个方法调用都会转成调用实现了MethodInterceptor接口的proxy的intercept() 函数：
 */
public class CglibTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        // 创建cglib动态代理类
        BookFacadeCglib cglib = new BookFacadeCglib();
        // 获取代理对象  instance 指向 BookFacadeImpl的子类对象
        BookFacadeImpl instance = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());

        instance.addBook("thinking in java");

    }

}
