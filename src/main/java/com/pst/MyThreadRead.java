package com.pst;

public class MyThreadRead implements Runnable {
    private MyBufferClass myBufferClass;
    private Integer id;

    public MyThreadRead(MyBufferClass myBufferClass, Integer id) {
        this.myBufferClass = myBufferClass;
        this.id = id;
    }

    @Override
    public void run() {
        myBufferClass.getBytes(id);
    }
}