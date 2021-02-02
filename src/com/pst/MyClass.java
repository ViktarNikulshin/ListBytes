package com.pst;

public class MyClass {
    public static void main(String[] args) throws InterruptedException {
        MyBufferClass myClass = new MyBufferClass();

        byte[] bytes = new byte[]{1, 3, 5, 7};
        byte[] bytes1 = new byte[]{4, 3, 5, 7};
        byte[] bytes2 = new byte[]{8, 3, 5, 7};
        byte[] bytes3 = new byte[]{35, 3, 5, 7};
        Thread thread = new Thread(new MyThreadAdd(myClass, bytes));
        Thread thread1 = new Thread(new MyThreadAdd(myClass, bytes1));
        Thread thread2 = new Thread(new MyThreadAdd(myClass, bytes2));
        Thread thread3 = new Thread(new MyThreadAdd(myClass, bytes3));
        Thread thread4 = new Thread(new MyThreadRead(myClass,3));

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
