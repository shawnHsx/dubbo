package com.jd;

/**
 * Created by heshuanxu on 2016/9/9.
 */
public class TestMain {

    public static void main(String[] args){
        //getNums();
        //getSuShu();
    }

    /**
     * 获取素数
     */
    private static void getSuShu(){
        for (int i = 100; i <200 ; i++) {
            int flag = 0;
            for (int j = 2; j <i; j++) {
                if(i%j==0 && i!=j){
                    break;
                }else if (i==j){
                    flag = 1;
                }
            }
            if(flag==1){
                System.out.println(i);
            }
        }
    }

    /**
     * 获取无重复三位数 1，2 ，3 ，4
     */
    private static void getNums(){
        String result =""; // 三位数结果
        int count = 0;// 记录三位数个数
        for (int i = 1; i <5; i++) {
            int n1= i;
            for (int j = 1; j < 5; j++) {
                int n2 = j;
                for (int k = 1; k < 5; k++) {
                    int n3 = k;

                    if (n1!=n2 && n2!=n3 && n1!=n3){
                        result = String.valueOf(n1)+String.valueOf(n2)+String.valueOf(n3);
                        count ++;
                        System.out.println(result);
                    }
                }
            }
        }
        System.out.println("三位数总数："+count);
    }
}
