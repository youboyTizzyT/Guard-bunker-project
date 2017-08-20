package com.company.suanfa;

/**
 * Created by wangshengxue on 17-8-8.
 */

public class suanfa2 {


    public suanfa2(int a) {
    }

    public static void countDown(int seconds) {
        System.err.println("倒计时" + seconds + "秒,倒计时开始:");
        int i = seconds;
        while (i > 0) {
            System.err.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            i--;
        }
        System.err.println(i);
        System.err.println("倒计时结束");
    }




    public static void main(String[] args) {

        countDown(5);
    }




//    public int judge() {
//        return 0;
//    }
}
